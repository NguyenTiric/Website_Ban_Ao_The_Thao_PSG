package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;


@Component
public class ThuHangServiceImpl implements ThuHangService {

    @Autowired
    ThuHangRepository thuHangRepository;

    @Autowired
    ThuHangMapper thuHangMapper;


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
        ThuHang thuHang = thuHangMapper.createThuHangRequestToThuHangEntity(createThuHangRequest);
        thuHang.setMa(GenCode.generateThuHangCode());
        thuHang.setNgayTao(LocalDate.now());
        thuHang.setTrangThai(ApplicationConstant.TrangThaiThuHang.ACTIVE);
        return thuHangMapper.thuHangEntiyToThuHangResponse(thuHangRepository.save(thuHang));
    }

    @Override
    public ThuHangResponse update(UpdateThuHangRequest updateThuHangRequest) {
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
        thuHangRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertThuHang(Integer id, LocalDate now) {
        thuHangRepository.revert(id, LocalDate.now());

    }
}
