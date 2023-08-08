package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.mapper.KhachHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
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

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class KhachHangServiceImpl implements KhachHangService {
//    @Autowired
//    private KhachHangRepository khachHangRepository;
//
//    @Autowired
//    private KhachHangMapper khachHangMapper;
//
//    @Override
//    public Page<KhachHangResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
//        Pageable pageable = PageRequest.of(pageNo, size);
//        Page<KhachHang> taiKhoanPage = khachHangRepository.pageACTIVEKhachHang(pageable);
//        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public Page<KhachHangResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
//        Pageable pageable = PageRequest.of(pageNo, size);
//        Page<KhachHang> taiKhoanPage = khachHangRepository.pageINACTIVEKhachHang(pageable);
//        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public void add(CreateKhachHangRequest createKhachHangRequest, MultipartFile file) throws IOException, SQLException {
//        KhachHang khachHang = khachHangMapper.createKhachHangRequestToTaiKhoanEntity(createKhachHangRequest);
//        byte[] bytes = file.getBytes();
//        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//        khachHang.setNgayTao(LocalDate.now());
//        khachHang.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
//        khachHang.setAnh(blob);
//        VaiTro vt = null;
//        for (VaiTro x : vaiTroRepository.findAll()) {
//            if (x.getTen().equalsIgnoreCase("Khách Hàng")) {
//                vt = x;
//                break;
//            }
//        }
//        khachHang.setVaiTro(vt);
//        khachHangRepository.save(khachHang);
//
//    }
//
//    @Override
//    public void update(Integer id, MultipartFile file, UpdateKhachHangRequest updateKhachHangRequest) throws IOException, SQLException {
//        KhachHang tk = khachHangRepository.findById(id).orElse(null);
//        if (tk != null) {
//            if (!file.isEmpty()) {
//                byte[] bytes = file.getBytes();
//                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//                tk.setAnh(blob);
//            }
//            tk.setSdt(updateKhachHangRequest.getSdt());
//            tk.setTen(updateKhachHangRequest.getTen());
//            tk.setNgaySinh(updateKhachHangRequest.getNgaySinh());
//            tk.setGioiTinh(updateKhachHangRequest.getGioiTinh());
//            tk.setDiaChi(updateKhachHangRequest.getDiaChi());
//            tk.setEmail(updateKhachHangRequest.getEmail());
//            khachHangRepository.save(tk);
//        }
//    }
//
//
//    @Override
//    public KhachHangResponse getOne(Integer id) {
//        Optional<KhachHang> optionalTaiKhoan = khachHangRepository.findById(id);
//        return khachHangMapper.khachHangEntityToTaiKhoanResponse(optionalTaiKhoan.get());
//    }
//
//    @Override
//    public void delete(Integer id, LocalDate now) {
//        khachHangRepository.deleteKhachHang(id, now);
//    }
//
//    @Override
//    public void revertTaiKhoan(Integer id, LocalDate now) {
//        khachHangRepository.revertKhachHang(id, now);
//    }
//
//    @Override
//    public Page<KhachHangResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
//        Pageable pageable = PageRequest.of(pageNo, size);
//        Page<KhachHang> taiKhoanPage = khachHangRepository.pageSearchACTIVEKhachHang(search, pageable);
//        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public Page<KhachHangResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo, Integer size) {
//        Pageable pageable = PageRequest.of(pageNo, size);
//        Page<KhachHang> taiKhoanPage = khachHangRepository.pageSearchTuoiMinMaxKhachHang(min, max, pageable);
//        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public KhachHang viewById(Integer id) {
//        return khachHangRepository.findById(id).get();
//    }
}
