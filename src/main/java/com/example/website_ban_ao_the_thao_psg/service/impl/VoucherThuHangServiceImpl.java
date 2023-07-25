package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.DiaChi;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.VoucherThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.VoucherThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.VoucherThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.VoucherThuHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherThuHangServiceImpl implements VoucherThuHangService {
    @Autowired
    private VoucherThuHangRepository voucherThuHangRepository;

    @Autowired
    VoucherThuHangMapper voucherThuHangMapper;




    @Override
    public Page<VoucherThuHangResponse> pageVouCherThuHangActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VoucherThuHang> voucherThuHangs = voucherThuHangRepository.pageACTIVE(pageable);
        return voucherThuHangs.map(voucherThuHangMapper::voucherThuHangEntityToVoucherThuHangResponse);
    }

    @Override
    public Page<VoucherThuHangResponse> pageVouCherThuHangInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VoucherThuHang> voucherThuHangs = voucherThuHangRepository.pageINACTIVE(pageable);
        return voucherThuHangs.map(voucherThuHangMapper::voucherThuHangEntityToVoucherThuHangResponse);
    }

    @Override
    public VoucherThuHangResponse add(CreateVoucherThuHangRequest createVoucherThuHangRequest) {
        VoucherThuHang voucherThuHang = voucherThuHangMapper.createVoucherThuHangRequestToVoucherThuHangEntity(createVoucherThuHangRequest);
        voucherThuHang.setNgayTao(LocalDateTime.now());
        voucherThuHang.setTrangThai(ApplicationConstant.TrangThaiVoucher.ACTIVE);
        return voucherThuHangMapper.voucherThuHangEntityToVoucherThuHangResponse(voucherThuHangRepository.save(voucherThuHang));
    }

    @Override
    public VoucherThuHangResponse update(UpdateVoucherThuHangRequest updateVouCherThuHangRequest) {
        VoucherThuHang voucherThuHang = voucherThuHangMapper.updateVoucherThuHangRequestToVoucherThuHangEntity(updateVouCherThuHangRequest);
        voucherThuHang.setNgayCapNhat(LocalDateTime.now());
        voucherThuHang.setTrangThai(ApplicationConstant.TrangThaiVoucher.ACTIVE);
        return voucherThuHangMapper.voucherThuHangEntityToVoucherThuHangResponse(voucherThuHangRepository.save(voucherThuHang));
    }

    @Override
    public VoucherThuHangResponse getOne(Integer id) {
        Optional<VoucherThuHang> voucherThuHangOptional = voucherThuHangRepository.findById(id);
        return voucherThuHangMapper.voucherThuHangEntityToVoucherThuHangResponse(voucherThuHangOptional.get());
    }

    @Override
    public Page<VoucherThuHangResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VoucherThuHang> voucherThuHangPage = voucherThuHangRepository.pageSearchActive(searchName, pageable);
        return voucherThuHangPage.map(voucherThuHangMapper::voucherThuHangEntityToVoucherThuHangResponse);
    }

    @Override
    public Page<VoucherThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VoucherThuHang> voucherThuHangPage = voucherThuHangRepository.pageSearchIvActive(searchName, pageable);
        return voucherThuHangPage.map(voucherThuHangMapper::voucherThuHangEntityToVoucherThuHangResponse);
    }

    @Override
    public void deleteVoucherThuHang(Integer id, LocalDateTime now) {
        voucherThuHangRepository.delete(id, LocalDateTime.now());
    }

    @Override
    public void revertVoucherThuHang(Integer id, LocalDateTime now) {
        voucherThuHangRepository.revert(id, LocalDateTime.now());
    }
}
