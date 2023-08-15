package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.AnhSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.ChatLieu;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.entity.SanPham;
import com.example.website_ban_ao_the_thao_psg.model.mapper.AnhSanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ChiTietSanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.SanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.repository.AnhSanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ChiTietSanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.repository.SanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    AnhSanPhamRepository anhSanPhamRepository;

    @Autowired
    AnhSanPhamMapper anhSanPhamMapper;

    @Autowired
    ChiTietSanPhamMapper chiTietSanPhamMapper;

    @Autowired
    SanPhamMapper sanPhamMapper;

    @Override
    public List<ChiTietSanPhamResponse> listChiTietSanPhamBySanPham(Integer idSp) {
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.getChiTietSanPhamBySanPham(sanPhamRepository.findById(idSp).get());
        return chiTietSanPhamMapper.listchiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamList);
    }

    @Override
    public List<ChiTietSanPhamResponse> getAllChiTietSanPham() {
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.getAll();
        return chiTietSanPhamMapper.listchiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamList);
    }

    @Override
    public Page<ChiTietSanPhamResponse> pageChiTietSanPhamActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamRepository.pageACTIVE(pageable);
        return chiTietSanPhamPage.map(chiTietSanPhamMapper::chiTietSanPhamEntityTochiTietSanPhamResponse);
    }

    @Override
    public Page<ChiTietSanPhamResponse> pageChiTietSanPhamInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamRepository.pageINACTIVE(pageable);
        return chiTietSanPhamPage.map(chiTietSanPhamMapper::chiTietSanPhamEntityTochiTietSanPhamResponse);
    }

    @Override
    public List<ChiTietSanPhamResponse> getAllPending() {
        return chiTietSanPhamMapper.listchiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamRepository.getAllPending());
    }



    @Override
    public void addCtsp(CreateSanPhamRequest createSanPhamRequest, List<KichThuoc> kichThuocList, MultipartFile[] files) throws IOException, SQLException {
        SanPham sanPham = sanPhamMapper.createSanPhamRequestToSanPhamEntity(createSanPhamRequest);
        sanPham.setMa(GenCode.generateSanPhamCode());
        sanPham.setNgayTao(LocalDate.now());
        sanPham.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        SanPham sp = sanPhamRepository.save(sanPham);

            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                AnhSanPham anhSanPham = anhSanPhamMapper.createAnhSanPhamRequestToAnhSanPhamEntity(new CreateAnhSanPhamRequest());
                anhSanPham.setSanPham(sp);
                anhSanPham.setTen(blob);
                anhSanPham.setNgayTao(LocalDate.now());
                anhSanPham.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
                anhSanPhamRepository.save(anhSanPham);
        }


            if(kichThuocList == null){
                System.out.println("bbbb");
                return;
            }

        for (KichThuoc ktId : kichThuocList) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamMapper.createChiTietSanPhamRequestToChiTietSanPhamEntity(new CreateChiTietSanPhamRequest());
            chiTietSanPham.setSanPham(sp);
            chiTietSanPham.setKichThuoc(ktId);
            chiTietSanPham.setSoLuong(1);
            chiTietSanPham.setNgayTao(LocalDate.now());
            chiTietSanPham.setTrangThai(ApplicationConstant.TrangThaiChiTietSanPham.PENDING);
            chiTietSanPhamMapper.chiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamRepository.save(chiTietSanPham));
        }
    }

    @Override
    public void updateSoLuongPending(List<Integer> id, List<Integer> soLuong) {
        for (int i = 0; i < id.size(); i++) {
            Integer ids = id.get(i);
            Integer soLuongs = soLuong.get(i);
            Optional<ChiTietSanPham> optionalChiTietSanPham = chiTietSanPhamRepository.findById(ids);
            if (optionalChiTietSanPham.isPresent()) {
                ChiTietSanPham ctsp = optionalChiTietSanPham.get();
                ctsp.setSoLuong(soLuongs);
                ctsp.setNgayCapNhat(LocalDate.now());
                ctsp.setTrangThai(ApplicationConstant.TrangThaiChiTietSanPham.ACTIVE);
                chiTietSanPhamRepository.save(ctsp);
            }
        }
    }

    @Override
    public void updateSoLuongActive(List<Integer> id, List<Integer> soLuong) {
        for (int i = 0; i < id.size(); i++) {
            Integer ids = id.get(i);
            Integer soLuongs = soLuong.get(i);
            Optional<ChiTietSanPham> optionalChiTietSanPham = chiTietSanPhamRepository.findById(ids);
            if (optionalChiTietSanPham.isPresent()) {
                ChiTietSanPham ctsp = optionalChiTietSanPham.get();
                ctsp.setSoLuong(soLuongs);
                ctsp.setNgayCapNhat(LocalDate.now());
                ctsp.setTrangThai(ApplicationConstant.TrangThaiChiTietSanPham.ACTIVE);
                chiTietSanPhamRepository.save(ctsp);
            }
        }
    }


    @Override
    public void updateTrangThai() {
        chiTietSanPhamRepository.updatePendingToActive();
    }

    @Override
    public void insertCtsp(List<KichThuoc> kichThuocList, Integer idSP) {
        for (KichThuoc ktId : kichThuocList) {
            // Lấy danh sách chi tiết sản phẩm có trong sản phẩm
            List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.getChiTietSanPhamBySanPham(sanPhamRepository.findById(idSP).get());

            // Kiểm tra xem kích thước đã tồn tại trong danh sách chi tiết sản phẩm hay chưa
            boolean kichThuocExists = false;
            for (ChiTietSanPham ctsp : chiTietSanPhamList) {
                if (ktId.getId().equals(ctsp.getKichThuoc().getId())) {
                    kichThuocExists = true;
                    break;
                }
            }

            // Nếu kích thước đã tồn tại, không thêm mới nữa
            if (kichThuocExists) {
                continue;
            }

            // Tạo mới chi tiết sản phẩm với kích thước mới
            ChiTietSanPham chiTietSanPham = chiTietSanPhamMapper.createChiTietSanPhamRequestToChiTietSanPhamEntity(new CreateChiTietSanPhamRequest());
            SanPham sanPham = sanPhamRepository.findById(idSP).get();
            chiTietSanPham.setSanPham(sanPham);
            chiTietSanPham.setKichThuoc(ktId);
            chiTietSanPham.setSoLuong(1);
            chiTietSanPham.setNgayTao(LocalDate.now());
            chiTietSanPham.setTrangThai(ApplicationConstant.TrangThaiChiTietSanPham.ACTIVE);
            chiTietSanPhamMapper.chiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamRepository.save(chiTietSanPham));
        }
    }

    @Override
    public ChiTietSanPhamResponse getOneCtsp(Integer id) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(id).get();
        return chiTietSanPhamMapper.chiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPham);
    }

//    @Override
//    public ChiTietSanPhamResponse getOneCtsp(Integer id) {
//        Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findById(id);
//        return chiTietSanPhamMapper.chiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamOptional.get());
//    }

//    @Override
//    public Page<ChiTietSanPhamResponse> searchNameOrMaActiveCtsp(String searchName, Integer pageNo, Integer size) {
//        Pageable pageable = PageRequest.of(pageNo, size);
//        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamRepository.pageSearchActive(searchName, pageable);
//        return chiTietSanPhamPage.map(chiTietSanPhamMapper::chiTietSanPhamEntityTochiTietSanPhamResponse);
//    }

//    @Override
//    public Page<ChiTietSanPhamResponse> searchNameOrMaInActiveCtsp(String searchName, Integer pageNo, Integer size) {
//
//    }

//c

    @Override
    public void deleteChiTietSanPham(Integer id) {
        chiTietSanPhamRepository.deleteById(id);
    }

    @Override
    public void revertChiTietSanPham(Integer id, LocalDate now) {
        chiTietSanPhamRepository.revert(id, LocalDate.now());
    }

    @Override
    public Page<SanPhamResponse> pageSanPhamActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<SanPham> sanPhamPage = sanPhamRepository.pageACTIVE(pageable);
        return sanPhamPage.map(sanPhamMapper::sanPhamEntityToSanPhamResponse);
    }

    @Override
    public Page<SanPhamResponse> pageSanPhamInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<SanPham> sanPhamPage = sanPhamRepository.pageINACTIVE(pageable);
        return sanPhamPage.map(sanPhamMapper::sanPhamEntityToSanPhamResponse);
    }

    @Override
    public List<SanPhamResponse> getAllSanPhamActive() {
        return sanPhamMapper.listSanPhamEntityToSanPhamResponse(sanPhamRepository.getAll());
    }

    @Override
    public SanPhamResponse addSp(CreateSanPhamRequest createSanPhamRequest) {
        return null;
    }

    @Override
    public void updateSp(UpdateSanPhamRequest updateSanPhamRequest, MultipartFile[] files) throws IOException, SQLException {
        SanPham sanPham = sanPhamMapper.updateSanPhamRequestToSanPhamEntity(updateSanPhamRequest);
        sanPham.setNgayCapNhat(LocalDate.now());
        sanPham.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        SanPham sp = sanPhamRepository.save(sanPham);

        if (files != null && files.length > 0) {
            anhSanPhamRepository.deleteAllBySanPham(sp);
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                AnhSanPham anhSanPham = anhSanPhamMapper.createAnhSanPhamRequestToAnhSanPhamEntity(new CreateAnhSanPhamRequest());
                anhSanPham.setSanPham(sp);
                anhSanPham.setTen(blob);
                anhSanPham.setNgayTao(LocalDate.now());
                anhSanPham.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
                anhSanPhamRepository.save(anhSanPham);
            }
        }
    }

    @Override
    public SanPhamResponse getOneSp(Integer id) {
        return sanPhamMapper.sanPhamEntityToSanPhamResponse(sanPhamRepository.findById(id).get());
    }

    @Override
    public List<SanPhamResponse> getAllSP() {
        return sanPhamMapper.listSanPhamEntityToSanPhamResponse(sanPhamRepository.getAll());
    }

    @Override
    public Page<SanPhamResponse> searchNameOrMaActiveSp(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<SanPham> sanPhamPage = sanPhamRepository.pageSearchActive(searchName, pageable);
        return sanPhamPage.map(sanPhamMapper::sanPhamEntityToSanPhamResponse);
    }

    @Override
    public Page<SanPhamResponse> searchNameOrMaInActiveSp(String searchName, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public void deleteSanPham(Integer id, LocalDate now) {
        sanPhamRepository.delete(id, now);
    }

    @Override
    public void revertSanPham(Integer id, LocalDate now) {
        sanPhamRepository.revert(id, now);
    }

    @Override
    public void deleteChiTietSanPhamUpdate(Integer id, LocalDate now) {
        chiTietSanPhamRepository.deleteChiTietSanPhamUpdate(id,now);
    }
}
