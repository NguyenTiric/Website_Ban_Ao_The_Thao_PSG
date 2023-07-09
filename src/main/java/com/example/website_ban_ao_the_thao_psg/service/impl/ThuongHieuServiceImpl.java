package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.ThuongHieu;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuongHieuMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuongHieuResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ThuongHieuRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Autowired
    ThuongHieuMapper thuongHieuMapper;


    @Override
    public Page<ThuongHieuResponse> pageThuongHieuActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuongHieu> thuongHieuPage = thuongHieuRepository.pageACTIVE(pageable);
        return thuongHieuPage.map(thuongHieuMapper::thuongHieuEntityToThuongHieuResponse);
    }

    @Override
    public Page<ThuongHieuResponse> pageThuongHieuInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuongHieu> thuongHieuPage = thuongHieuRepository.pageINACTIVE(pageable);
        return thuongHieuPage.map(thuongHieuMapper::thuongHieuEntityToThuongHieuResponse);

    }

    @Override
    public ThuongHieuResponse add(CreateThuongHieuRequest createThuongHieuRequest) {
        ThuongHieu thuongHieu = thuongHieuMapper.createThuongHieuRequestToThuongHieuEntity(createThuongHieuRequest);
        thuongHieu.setMa(GenCode.generateThuongHieuCode());
        thuongHieu.setNgayTao(LocalDate.now());
        thuongHieu.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return thuongHieuMapper.thuongHieuEntityToThuongHieuResponse(thuongHieuRepository.save(thuongHieu));
    }

    @Override
    public ThuongHieuResponse update(UpdateThuongHieuRequest updateThuongHieuRequest) {
        ThuongHieu thuongHieu = thuongHieuMapper.updateThuongHieuRequestToThuongHieuEntity(updateThuongHieuRequest);
        thuongHieu.setNgayCapNhat(LocalDate.now());
        thuongHieu.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return thuongHieuMapper.thuongHieuEntityToThuongHieuResponse(thuongHieuRepository.save(thuongHieu));
    }

    @Override
    public ThuongHieuResponse getOne(Integer id) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepository.findById(id);
        return thuongHieuMapper.thuongHieuEntityToThuongHieuResponse(thuongHieuOptional.get());
    }

    @Override
    public Page<ThuongHieuResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuongHieu> thuongHieuPage = thuongHieuRepository.pageSearchActive(searchName, pageable);
        return thuongHieuPage.map(thuongHieuMapper::thuongHieuEntityToThuongHieuResponse);
    }

    @Override
    public Page<ThuongHieuResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ThuongHieu> thuongHieuPage = thuongHieuRepository.pageSearchIvActive(searchName, pageable);
        return thuongHieuPage.map(thuongHieuMapper::thuongHieuEntityToThuongHieuResponse);
    }

    @Override
    public void deleteThuongHieu(Integer id, LocalDate now) {
        thuongHieuRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertThuongHieu(Integer id, LocalDate now) {
        thuongHieuRepository.revert(id, LocalDate.now());
    }
}
