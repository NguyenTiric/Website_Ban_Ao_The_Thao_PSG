package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.mapper.KhachHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.VaiTroRepository;
import com.example.website_ban_ao_the_thao_psg.service.KhachHangService;
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
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private KhachHangMapper khachHangMapper;



    @Override
    public Page<KhachHangResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<KhachHang> taiKhoanPage = khachHangRepository.pageACTIVEKhachHang(pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }

    @Override
    public Page<KhachHangResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<KhachHang> taiKhoanPage = khachHangRepository.pageINACTIVEKhachHang(pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }

    @Override
    public void add(CreateKhachHangRequest createKhachHangRequest, MultipartFile file) throws IOException, SQLException {
        KhachHang khachHang = khachHangMapper.createKhachHangRequestToTaiKhoanEntity(createKhachHangRequest);
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        khachHang.setMa(GenCode.generateKhachHangCode());
        khachHang.setNgayTao(LocalDate.now());
        khachHang.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        khachHang.setAnh(blob);

        khachHangRepository.save(khachHang);

    }

    @Override
    public void update(Integer id, MultipartFile file, UpdateKhachHangRequest updateKhachHangRequest) throws IOException, SQLException {
        KhachHang kh = khachHangRepository.findById(id).orElse(null);
        if (kh != null) {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                kh.setAnh(blob);

            }
            kh.setSdt(updateKhachHangRequest.getSdt());
            kh.setTen(updateKhachHangRequest.getTen());
            kh.setNgaySinh(updateKhachHangRequest.getNgaySinh());
            kh.setGioiTinh(updateKhachHangRequest.getGioiTinh());
            kh.setDiaChi(updateKhachHangRequest.getDiaChi());
            kh.setEmail(updateKhachHangRequest.getEmail());
            khachHangRepository.save(kh);
        }
    }


    @Override
    public KhachHangResponse getOne(Integer id) {
        Optional<KhachHang> optionalTaiKhoan = khachHangRepository.findById(id);
        return khachHangMapper.khachHangEntityToTaiKhoanResponse(optionalTaiKhoan.get());
    }

    @Override
    public void delete(Integer id, LocalDate now) {
        khachHangRepository.deleteKhachHang(id, now);
    }

    @Override
    public void revertTaiKhoan(Integer id, LocalDate now) {
        khachHangRepository.revertKhachHang(id, now);
    }

    @Override
    public Page<KhachHangResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<KhachHang> taiKhoanPage = khachHangRepository.pageSearchACTIVEKhachHang(search, pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }

    @Override
    public Page<KhachHangResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<KhachHang> taiKhoanPage = khachHangRepository.pageSearchTuoiMinMaxKhachHang(min, max, pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }
//
    @Override
    public KhachHang viewById(Integer id) {
        return khachHangRepository.findById(id).get();
    }
//
    @Override
    public Boolean existsBySdtKhachHang(String sdt) {
        return khachHangRepository.existsBySdtKhachHang(sdt);
    }

    @Override
    public Boolean existsByEmailKhachHang(String email) {
        return khachHangRepository.existsByEmailKhachHang(email);
    }

    @Override
    public Boolean existsBySdtKhachHangWithDifferentId(String sdt, Integer id) {
        return khachHangRepository.existsBySdtKhachHangWithDifferentId(sdt,id);
    }

    @Override
    public Boolean existsByEmailKhachHangWithDifferentId(String sdt, Integer id) {
        return khachHangRepository.existsByEmailKhachHangWithDifferentId(sdt,id);

    }

}
