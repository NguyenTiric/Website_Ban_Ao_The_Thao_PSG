package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.mapper.KhachHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.TaiKhoanRepository;
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
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private KhachHangMapper khachHangMapper;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public Page<KhachHangResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<TaiKhoan> taiKhoanPage = taiKhoanRepository.pageACTIVEKhachHang(pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }

    @Override
    public Page<KhachHangResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<TaiKhoan> taiKhoanPage = taiKhoanRepository.pageINACTIVEKhachHang(pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }

    @Override
    public void add(CreateKhachHangRequest createKhachHangRequest, MultipartFile file) throws IOException, SQLException {
        TaiKhoan taiKhoan = khachHangMapper.createKhachHangRequestToTaiKhoanEntity(createKhachHangRequest);
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        taiKhoan.setNgayTao(LocalDate.now());
        taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        taiKhoan.setAnh(blob);
        VaiTro vt = null;
        for (VaiTro x : vaiTroRepository.findAll()) {
            if (x.getTen().equalsIgnoreCase("Khách Hàng")) {
                vt = x;
                break;
            }
        }
        taiKhoan.setVaiTro(vt);
        taiKhoanRepository.save(taiKhoan);

    }

    @Override
    public void update(Integer id, MultipartFile file, UpdateKhachHangRequest updateKhachHangRequest) throws IOException, SQLException {
        TaiKhoan tk = taiKhoanRepository.findById(id).orElse(null);
        if (tk != null) {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                tk.setAnh(blob);

            }
            tk.setSdt(updateKhachHangRequest.getSdt());
            tk.setTen(updateKhachHangRequest.getTen());
            tk.setNgaySinh(updateKhachHangRequest.getNgaySinh());
            tk.setGioiTinh(updateKhachHangRequest.getGioiTinh());
            tk.setDiaChi(updateKhachHangRequest.getDiaChi());
            tk.setEmail(updateKhachHangRequest.getEmail());
            taiKhoanRepository.save(tk);
        }
    }


    @Override
    public KhachHangResponse getOne(Integer id) {
        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanRepository.findById(id);
        return khachHangMapper.khachHangEntityToTaiKhoanResponse(optionalTaiKhoan.get());
    }

    @Override
    public void delete(Integer id, LocalDate now) {
        taiKhoanRepository.deleteKhachHang(id, now);
    }

    @Override
    public void revertTaiKhoan(Integer id, LocalDate now) {
        taiKhoanRepository.revertKhachHang(id, now);
    }

    @Override
    public Page<KhachHangResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<TaiKhoan> taiKhoanPage = taiKhoanRepository.pageSearchACTIVEKhachHang(search, pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }

    @Override
    public Page<KhachHangResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<TaiKhoan> taiKhoanPage = taiKhoanRepository.pageSearchTuoiMinMaxKhachHang(min, max, pageable);
        return taiKhoanPage.map(khachHangMapper::khachHangEntityToTaiKhoanResponse);
    }

    @Override
    public TaiKhoan viewById(Integer id) {
        return taiKhoanRepository.findById(id).get();
    }
}
