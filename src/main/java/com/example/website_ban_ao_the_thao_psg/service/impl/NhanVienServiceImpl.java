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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class NhanVienServiceImpl implements NhanVienService {
//    @Autowired
//    private TaiKhoanRepository taiKhoanRepository;
//
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private NhanVienMapper nhanVienMapper;

    @Autowired
    private VaiTroRepository vaiTroRepository;
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
////        if (file == null) {
////            throw new IllegalArgumentException("File is null. Please upload a file.");
////        }
        NhanVien nhanVien = nhanVienMapper.createNhanVienRequestToNhanVienEntity(createNhanVienRequest);
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        nhanVien.setMa(GenCode.generateNhanVienCode());
        nhanVien.setNgayTao(LocalDate.now());
        nhanVien.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        nhanVien.setAnhNV(blob);
        nhanVienRepository.save(nhanVien);
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
