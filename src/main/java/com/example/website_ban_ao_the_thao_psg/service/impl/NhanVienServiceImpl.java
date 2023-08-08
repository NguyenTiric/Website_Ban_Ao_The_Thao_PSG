package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.NhanVienMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
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
//    private NhanVienRepository nhanVienRepository;
//
//    @Autowired
//    private NhanVienMapper nhanVienMapper;
//
//    @Autowired
//    private VaiTroRepository vaiTroRepository;
//    @Override
//    public Page<KhachHangResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
//        Pageable pageable= PageRequest.of(pageNo,size);
//        Page<KhachHang>taiKhoanPage= nhanVienRepository.pageACTIVENhanVien(pageable);
//        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public Page<KhachHangResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
//        Pageable pageable= PageRequest.of(pageNo,size);
//        Page<KhachHang>taiKhoanPage= nhanVienRepository.pageINACTIVENhanVien(pageable);
//        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public void add(CreateNhanVienRequest createNhanVienRequest, MultipartFile file) throws IOException, SQLException {
//        KhachHang khachHang = nhanVienMapper.createNhanVienRequestToTaiKhoanEntity(createNhanVienRequest);
//        byte[] bytes = file.getBytes();
//        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//        khachHang.setNgayTao(LocalDate.now());
//        khachHang.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
//        khachHang.setAnh(blob);
//
//
//        nhanVienRepository.save(khachHang);
//    }
//
//    @Override
//    public void update(Integer id, MultipartFile file, UpdateNhanVienRequest updateNhanVienRequest) throws IOException, SQLException {
//        KhachHang tk = nhanVienRepository.findById(id).orElse(null);
//        if (tk != null) {
//            if (!file.isEmpty()) {
//                byte[] bytes = file.getBytes();
//                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//                tk.setAnh(blob);
//            }
//            tk.setSdt(updateNhanVienRequest.getSdt());
//            tk.setTen(updateNhanVienRequest.getTen());
//            tk.setNgaySinh(updateNhanVienRequest.getNgaySinh());
//            tk.setGioiTinh(updateNhanVienRequest.getGioiTinh());
//            tk.setDiaChi(updateNhanVienRequest.getDiaChi());
//            tk.setEmail(updateNhanVienRequest.getEmail());
//            nhanVienRepository.save(tk);
//        }
//    }
//
////
//
//    @Override
//    public KhachHangResponse getOne(Integer id) {
//        Optional<KhachHang>optionalTaiKhoan= nhanVienRepository.findById(id);
//        return nhanVienMapper.nhanVienEntityToTaiKhoanResponse(optionalTaiKhoan.get());
//    }
//
//    @Override
//    public void delete(Integer id, LocalDate now) {
//       nhanVienRepository.deleteNhanVien(id,now);
//    }
//
//    @Override
//    public void revertTaiKhoan(Integer id, LocalDate now) {
//    nhanVienRepository.revertNhanVien(id, now);
//    }
//
//    @Override
//    public Page<KhachHangResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
//        Pageable pageable=PageRequest.of(pageNo,size);
//        Page<KhachHang> taiKhoanPage= nhanVienRepository.pageSearchACTIVENhanVien(search,pageable);
//        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public Page<KhachHangResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo, Integer size) {
//        Pageable pageable=PageRequest.of(pageNo,size);
//        Page<KhachHang>taiKhoanPage= nhanVienRepository.pageSearchTuoiMinMaxNhanVien(min,max,pageable);
//        return taiKhoanPage.map(nhanVienMapper::nhanVienEntityToTaiKhoanResponse);
//    }
//
//    @Override
//    public KhachHang viewById(Integer id) {
//        return nhanVienRepository.findById(id).get();
//    }
}
