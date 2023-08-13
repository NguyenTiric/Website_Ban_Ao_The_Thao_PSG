package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.NhanVien;
import com.example.website_ban_ao_the_thao_psg.model.mapper.NhanVienMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NhanVienResponse;
import com.example.website_ban_ao_the_thao_psg.repository.NhanVienRepository;
import com.example.website_ban_ao_the_thao_psg.repository.VaiTroRepository;
import com.example.website_ban_ao_the_thao_psg.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private NhanVienMapper nhanVienMapper;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Page<NhanVienResponse> pageNhanVienActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<NhanVien>taiKhoanPage= nhanVienRepository.pageACTIVENhanVien(pageable);
        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToNhanVienResponse);
    }
//
    @Override
    public Page<NhanVienResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<NhanVien> taiKhoanPage= nhanVienRepository.pageINACTIVENhanVien(pageable);
        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToNhanVienResponse);
    }

    @Override
    public void add(CreateNhanVienRequest createNhanVienRequest, MultipartFile file) throws IOException, SQLException {

        SimpleMailMessage message = new SimpleMailMessage();
        NhanVien nhanVien = nhanVienMapper.createNhanVienRequestToNhanVienEntity(createNhanVienRequest);
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        nhanVien.setMa(GenCode.generateNhanVienCode());
        nhanVien.setMatKhau(GenCode.generatePassWordNhanVien());
        nhanVien.setNgayTao(LocalDate.now());
        nhanVien.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        nhanVien.setAnhNV(blob);
        nhanVienRepository.save(nhanVien);

        String subject = "Đăng ký tài khoản thành công";
        String content = "Xin chào" +nhanVien.getTen()+",\n" +
                "\n" +
                "Chúng tôi rất vui mừng chào đón bạn gia nhập đội ngũ của cửa hàng bán áo PSG! Dưới đây là thông tin đăng nhập cần thiết để bạn có thể truy cập vào hệ thống của chúng tôi:\n" +
                "\n" +
                "Tài khoản (email): "+nhanVien.getEmail()+"\n" +
                "Mật khẩu: "+nhanVien.getMatKhau()+"\n" +
                "Chức vụ: "+nhanVien.getVaiTro().getTen()+"\n" +
                "\n" +
                "Vui lòng lưu ý rằng đây là mật khẩu tạm thời, và chúng tôi khuyến nghị bạn nên thay đổi mật khẩu ngay sau khi đăng nhập lần đầu. Hãy đảm bảo rằng bạn giữ thông tin đăng nhập này một cách cẩn thận để tránh rủi ro bất kỳ việc lạm dụng tài khoản nào.\n" +
                "\n" +
                "Nếu bạn gặp bất kỳ vấn đề gì trong quá trình đăng nhập hoặc sử dụng hệ thống, vui lòng liên hệ với bộ phận hỗ trợ của chúng tôi qua địa chỉ [địa chỉ email hỗ trợ] hoặc số điện thoại [số điện thoại hỗ trợ]. Chúng tôi luôn sẵn sàng hỗ trợ bạn.\n" +
                "\n" +
                "Chúc bạn một ngày làm việc hiệu quả và thú vị tại cửa hàng bán áo PSG! Chúng tôi hy vọng bạn sẽ đóng góp mạnh mẽ vào sự phát triển của đội ngũ và sự thành công của cửa hàng.\n" +
                "\n" +
                "Trân trọng,\n" +
                "Nguyễn Trọng Tùng Anh\n" +
                "Giám đốc Cửa hàng bán áo PSG";
        message.setTo(nhanVien.getEmail());
        message.setSubject(subject);
        message.setText(content);
        this.emailSender.send(message);
    }

    @Override
    public void update(Integer id, MultipartFile file, UpdateNhanVienRequest updateNhanVienRequest) throws IOException, SQLException {
        NhanVien nv = nhanVienRepository.findById(id).orElse(null);
        if (nv != null) {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                nv.setAnhNV(blob);
            }
            nv.setSdt(updateNhanVienRequest.getSdt());
            nv.setTen(updateNhanVienRequest.getTen());
            nv.setNgaySinh(updateNhanVienRequest.getNgaySinh());
            nv.setGioiTinh(updateNhanVienRequest.getGioiTinh());
            nv.setDiaChi(updateNhanVienRequest.getDiaChi());
            nv.setEmail(updateNhanVienRequest.getEmail());
            nhanVienRepository.save(nv);
        }
    }

////
//
    @Override
    public NhanVienResponse getOne(Integer id) {
        Optional<NhanVien>optionalTaiKhoan= nhanVienRepository.findById(id);
        return nhanVienMapper.nhanVienEntityToNhanVienResponse(optionalTaiKhoan.get());
    }
//
    @Override
    public void delete(Integer id, LocalDate now) {
       nhanVienRepository.deleteNhanVien(id,now);
    }

    @Override
    public void revertTaiKhoan(Integer id, LocalDate now) {
    nhanVienRepository.revertNhanVien(id, now);
    }

    @Override
    public Page<NhanVienResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<NhanVien> taiKhoanPage= nhanVienRepository.pageSearchACTIVENhanVien(search,pageable);
        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToNhanVienResponse);
    }

    @Override
    public Page<NhanVienResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo, Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<NhanVien>taiKhoanPage= nhanVienRepository.pageSearchTuoiMinMaxNhanVien(min,max,pageable);
        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToNhanVienResponse);
    }

    @Override
    public NhanVien viewById(Integer id) {
        return nhanVienRepository.findById(id).get();
    }

    @Override
    public Boolean existsBySdtNhanVien(String sdt) {
        return nhanVienRepository.existsBySdtNhanVien(sdt);
    }

    @Override
    public Boolean existsByEmailNhanVien(String email) {
        return nhanVienRepository.existsByEmailNhanVien(email);
    }

    @Override
    public Boolean existsBySdtNhanVienWithDifferentId(String sdt, Integer id) {
        return nhanVienRepository.existsBySdtNhanVienWithDifferentId(sdt,id);
    }

    @Override
    public Boolean existsByEmailNhanVienWithDifferentId(String sdt, Integer id) {
        return nhanVienRepository.existsByEmailNhanVienWithDifferentId(sdt,id);
    }
}
