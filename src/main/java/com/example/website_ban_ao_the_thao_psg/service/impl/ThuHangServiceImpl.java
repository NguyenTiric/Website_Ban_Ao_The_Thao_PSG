package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.TaiKhoanRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import groovyjarjarpicocli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Component
public class ThuHangServiceImpl implements ThuHangService {

    @Autowired
    ThuHangRepository thuHangRepository;

    @Autowired
    ThuHangMapper thuHangMapper;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Override
    public Page<ThuHangResponse> pageThuHangActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = thuHangRepository.pageACTIVE(pageable);
        return thuHangPage.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> pageThuHangInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = thuHangRepository.pageINACTIVE(pageable);
        return thuHangPage.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public ThuHangResponse add(CreateThuHangRequest createThuHangRequest) {
        ApplicationConstant.TenThuHang tenThuHang = createThuHangRequest.getTen();
        if (this.thuHangRepository.existsByTenAndTrangThai(tenThuHang, ApplicationConstant.TrangThaiThuHang.ACTIVE)){
            throw new CommandLine.DuplicateNameException("Thứ hạng đã tồn tại, vui lòng chọn thứ hạng khác!");
        }

        List<TaiKhoan> allTaiKhoan = taiKhoanRepository.findAll();
        for (TaiKhoan taiKhoan : allTaiKhoan) {
            if (!taiKhoan.getVaiTro().getTen().equals(ApplicationConstant.TenThuHang.MEMBER)) {
                throw new RuntimeException("Vẫn còn tài khoản chưa về mặc định, hãy kiểm tra lại!");
            }
        }

        ThuHang thuHang = thuHangMapper.createThuHangRequestToThuHangEntity(createThuHangRequest);
        thuHang.setMa(GenCode.generateThuHangCode());
        thuHang.setNgayTao(LocalDate.now());
        thuHang.setTrangThai(ApplicationConstant.TrangThaiThuHang.ACTIVE);
        return thuHangMapper.thuHangEntiyToThuHangResponse(thuHangRepository.save(thuHang));
    }

    @Override
    public ThuHangResponse update(UpdateThuHangRequest updateThuHangRequest) {
        List<TaiKhoan> allTaiKhoan = taiKhoanRepository.findAll();
        for (TaiKhoan taiKhoan : allTaiKhoan) {
            if (!taiKhoan.getVaiTro().getTen().equals(ApplicationConstant.TenThuHang.MEMBER)) {
                throw new RuntimeException("Vẫn còn tài khoản chưa về mặc định, hãy kiểm tra lại!");
            }
        }
        ThuHang thuHang = thuHangMapper.updateThuHangRequestToThuHangEntity(updateThuHangRequest);
        thuHang.setNgayCapNhat(LocalDate.now());
        thuHang.setTrangThai(ApplicationConstant.TrangThaiThuHang.ACTIVE);
        return thuHangMapper.thuHangEntiyToThuHangResponse(thuHangRepository.save(thuHang));
    }

    @Override
    public ThuHangResponse getOne(Integer id) {
        Optional<ThuHang> thuHangOptional = thuHangRepository.findById(id);
        return thuHangMapper.thuHangEntiyToThuHangResponse(thuHangOptional.get());
    }

    @Override
    public Page<ThuHangResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = thuHangRepository.pageSearchActive(searchName, pageable);
        return thuHangPage.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = thuHangRepository.pageSearchIvActive(searchName, pageable);
        return thuHangPage.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public void deleteThuHang(Integer id, LocalDate now) {
        List<TaiKhoan> allTaiKhoan = taiKhoanRepository.findAll();
        for (TaiKhoan taiKhoan : allTaiKhoan) {
            if (!taiKhoan.getVaiTro().getTen().equals(ApplicationConstant.TenThuHang.MEMBER)) {
                throw new RuntimeException("Vẫn còn tài khoản chưa về mặc định, hãy kiểm tra lại!");
            }
        }
        thuHangRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertThuHang(Integer id, LocalDate now) {
        thuHangRepository.revert(id, LocalDate.now());
    }

    @Override
    public void checkDuplicateName(Integer id) {
        ThuHang thuHang = thuHangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy Thứ hạng với id = " + id));

        ApplicationConstant.TenThuHang tenThuHang = thuHang.getTen();
        if (thuHangRepository.existsByTenAndTrangThai(tenThuHang, ApplicationConstant.TrangThaiThuHang.ACTIVE)) {
            throw new CommandLine.DuplicateNameException("Thứ hạng đã tồn tại, không thể khôi phục!");
        }
    }

}
