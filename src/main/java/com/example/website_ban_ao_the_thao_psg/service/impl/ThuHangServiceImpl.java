package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import groovyjarjarpicocli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Component
public class ThuHangServiceImpl implements ThuHangService {

    @Autowired
    ThuHangRepository thuHangRepository;

    @Autowired
    ThuHangMapper thuHangMapper;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public Integer getMaxId() {
        return this.thuHangRepository.findMaxId();
    }

    @Override
    public List<ThuHang> getAll() {
        return this.thuHangRepository.findAll();
    }

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
        String tenThuHang = createThuHangRequest.getTen();

        if (this.thuHangRepository.existsByTenAndTrangThai(tenThuHang, ApplicationConstant.TrangThaiThuHang.ACTIVE)) {
            throw new CommandLine.DuplicateNameException("Thứ hạng đã tồn tại, vui lòng đặt thứ hạng khác!");
        }

        List<KhachHang> allKhachHang = khachHangRepository.findAll();
        for (KhachHang khachHang : allKhachHang) {
            if (!khachHang.getThuHang().getTen().equalsIgnoreCase("Thành viên")) {
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
        List<KhachHang> allKhachHang = khachHangRepository.findAll();
        for (KhachHang khachHang : allKhachHang) {
            if (!khachHang.getThuHang().getTen().equalsIgnoreCase("Thành viên")) {
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
    public Page<ThuHangResponse> searchSoLuongDonHangToiThieuActive(Integer search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = this.thuHangRepository.pageSoLuongDonHangToiThieuPageActive(search, pageable);
        return thuHangPage.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> searchSoLuongDonHangToiThieuInActive(Integer search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = this.thuHangRepository.pageSoLuongDonHangToiThieuPageInActive(search, pageable);
        return thuHangPage.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = thuHangRepository.pageSearchInActive(searchName, pageable);
        return thuHangPage.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> searchSoLuongDonHangOrSoTienInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuHang> thuHangPage = thuHangRepository.pageSearchSoLuongDonHangOrSoTienInActive(searchName, pageable);
        return thuHangPage.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public void deleteThuHang(Integer id, LocalDate now) {
        List<KhachHang> allKhachHang = khachHangRepository.findAll();
        for (KhachHang khachHang : allKhachHang) {
            if (!khachHang.getThuHang().getTen().equalsIgnoreCase("Thành viên")) {
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

        String tenThuHang = thuHang.getTen();
        if (thuHangRepository.existsByTenAndTrangThai(tenThuHang, ApplicationConstant.TrangThaiThuHang.ACTIVE)) {
            throw new CommandLine.DuplicateNameException("Thứ hạng đã tồn tại, không thể khôi phục!");
        }
    }

    @Override
    public Page<ThuHangResponse> searchMinMaxSoTien(BigDecimal min, BigDecimal max, Integer pageNo, Integer size) {

        if (min == null && max == null) {
            throw new IllegalArgumentException("Không được bỏ trống");
        }

        Pageable pageable = PageRequest.of(pageNo, size);

        if (min == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoTienKhachChiToiThieuInRange(BigDecimal.ZERO, max, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        if (max == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoTienKhachChiToiThieuInRange(min, BigDecimal.ZERO, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        Page<ThuHang> page = this.thuHangRepository.findBySoTienKhachChiToiThieuInRange(min, max, pageable);
        return page.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> searchMinMaxDonHang(Integer min, Integer max, Integer pageNo, Integer size) {
        if (min == null && max == null) {
            throw new IllegalArgumentException("Không được bỏ trống");
        }

        Pageable pageable = PageRequest.of(pageNo, size);

        if (min == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoLuongDonHangToiThieuInRange(0, max, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        if (max == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoLuongDonHangToiThieuInRange(min, 0, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        Page<ThuHang> page = this.thuHangRepository.findBySoLuongDonHangToiThieuInRange(min, max, pageable);
        return page.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> searchMinMaxSoTienInActive(BigDecimal min, BigDecimal max, Integer pageNo, Integer size) {
        if (min == null && max == null) {
            throw new IllegalArgumentException("Không được bỏ trống");
        }

        Pageable pageable = PageRequest.of(pageNo, size);

        if (min == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoTienKhachChiToiThieuInRangeInActive(BigDecimal.ZERO, max, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        if (max == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoTienKhachChiToiThieuInRangeInActive(min, BigDecimal.ZERO, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        Page<ThuHang> page = this.thuHangRepository.findBySoTienKhachChiToiThieuInRangeInActive(min, max, pageable);
        return page.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public Page<ThuHangResponse> searchMinMaxDonHangInActive(Integer min, Integer max, Integer pageNo, Integer size) {
        if (min == null && max == null) {
            throw new IllegalArgumentException("Không được bỏ trống");
        }

        Pageable pageable = PageRequest.of(pageNo, size);

        if (min == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoLuongDonHangToiThieuInRangeInActive(0, max, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        if (max == null) {
            Page<ThuHang> page = this.thuHangRepository.findBySoLuongDonHangToiThieuInRangeInActive(min, 0, pageable);
            return page.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
        }

        Page<ThuHang> page = this.thuHangRepository.findBySoLuongDonHangToiThieuInRangeInActive(min, max, pageable);
        return page.map(thuHangMapper::thuHangEntiyToThuHangResponse);
    }

}
