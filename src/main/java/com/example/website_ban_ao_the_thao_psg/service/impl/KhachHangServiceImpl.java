package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.mapper.TaiKhoanMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.NhanVienRepository;
import com.example.website_ban_ao_the_thao_psg.repository.VaiTroRepository;
import com.example.website_ban_ao_the_thao_psg.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private TaiKhoanMapper taiKhoanMapper;

    @Autowired
    private VaiTroRepository vaiTroRepository;
    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage= khachHangRepository.pageACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage= khachHangRepository.pageINACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public TaiKhoanResponse add(CreateKhachHangRequest createKhachHangRequest) {
        try {
            Path path = Paths.get("src/main/resources/static/image");
            String originalFilename = createKhachHangRequest.getAnh().getOriginalFilename().toLowerCase();
            Path imagePath = path.resolve(originalFilename);
            Files.copy(createKhachHangRequest.getAnh().getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println(originalFilename);
            VaiTro vt=new VaiTro();
            for (VaiTro x:vaiTroRepository.getAll()){
               if(x.getTen().equalsIgnoreCase("Khách Hàng")){
                   vt=x;
               }
            }
            // upload
            TaiKhoan taiKhoan=taiKhoanMapper.createTaiKhoanRequestToTaiKhoanEntity(createKhachHangRequest);
            taiKhoan.setNgayTao(LocalDate.now());
            taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
            taiKhoan.setAnh(originalFilename);
            taiKhoan.setVaiTro(vt);
            return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(khachHangRepository.save(taiKhoan));
//
        }catch (Exception x){
            x.printStackTrace();
            return null;
        }
    }

    @Override
    public TaiKhoanResponse update(UpdateKhachHangRequest updateKhachHangRequest) {

        try {
            MultipartFile newImageFile = updateKhachHangRequest.getAnh();
            TaiKhoan tk = khachHangRepository.findById(updateKhachHangRequest.getId()).orElse(null);

            if (tk == null) {
                // Xử lý trường hợp không tìm thấy tài khoản với ID đã cung cấp
                // Trả về thông báo lỗi hoặc giá trị đại diện cho lỗi
                return null; // Hoặc thực hiện xử lý khác tùy vào yêu cầu của ứng dụng
            }

            if (newImageFile != null && !newImageFile.isEmpty()) {
                // Có hình ảnh mới, tiếp tục xử lý hình ảnh mới
                Path imageDirectory = Paths.get("src/main/resources/static/image");
                String originalFilename = newImageFile.getOriginalFilename().toLowerCase();
                Path imagePath = imageDirectory.resolve(originalFilename);

                // Lưu hình ảnh mới vào thư mục
                Files.copy(newImageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

                // Xóa hình ảnh cũ nếu tồn tại
                String oldImageFilename = tk.getAnh();
                if (oldImageFilename != null) {
                    Path oldImagePath = imageDirectory.resolve(oldImageFilename);
                    Files.deleteIfExists(oldImagePath);
                }

                // Cập nhật trường hình ảnh (anh) trong đối tượng taiKhoan
                tk.setAnh(originalFilename);
            }

            // Cập nhật các trường khác của tài khoản nếu cần
              tk.setTen(updateKhachHangRequest.getTen());
                   tk.setEmail(updateKhachHangRequest.getEmail());
           tk.setDiaChi(updateKhachHangRequest.getDiaChi());
           tk.setNgaySinh(updateKhachHangRequest.getNgaySinh());
           tk.setGioiTinh(updateKhachHangRequest.getGioiTinh());
           tk.setSdt(updateKhachHangRequest.getSdt());
            tk.setNgayCapNhat(LocalDate.now());
            tk.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);

            // Lưu thông tin vào cơ sở dữ liệu
            TaiKhoan savedTaiKhoan = khachHangRepository.save(tk);

            // Chuyển đổi và trả về kết quả
            return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(savedTaiKhoan);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public TaiKhoanResponse getOne(Integer id) {
        Optional<TaiKhoan> optionalTaiKhoan= khachHangRepository.findById(id);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(optionalTaiKhoan.get());
    }

    @Override
    public void delete(Integer id, LocalDate now) {
        khachHangRepository.delete(id,now);
    }

    @Override
    public void revertTaiKhoan(Integer id, LocalDate now) {
        khachHangRepository.revert(id, now);
    }

    @Override
    public Page<TaiKhoanResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<TaiKhoan> taiKhoanPage= khachHangRepository.pageSearchACTIVE(search,pageable);
        return taiKhoanPage.map(taiKhoanMapper ::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public Page<TaiKhoanResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo,Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage=khachHangRepository.pageSearchTuoiMinMax(min,max,pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }
}
