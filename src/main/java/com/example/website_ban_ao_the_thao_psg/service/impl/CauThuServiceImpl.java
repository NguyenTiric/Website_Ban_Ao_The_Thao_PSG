package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.CauThu;
import com.example.website_ban_ao_the_thao_psg.model.mapper.CauThuMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CauThuResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.CauThuResponse;
import com.example.website_ban_ao_the_thao_psg.repository.CauThuRepository;
import com.example.website_ban_ao_the_thao_psg.service.CauThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class CauThuServiceImpl implements CauThuService {

    @Autowired
    CauThuRepository cauThuRepository;

    @Autowired
    CauThuMapper cauThuMapper;


    @Override
    public Page<CauThuResponse> pageCauThuActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CauThu> cauThuPage = cauThuRepository.pageACTIVE(pageable);
        return cauThuPage.map(cauThuMapper::cauThuEntityToCauThuResponse);
    }

    @Override
    public List<CauThuResponse> getAll() {
        List<CauThu> cauThuList = cauThuRepository.getAll();
        return cauThuMapper.listCauThuEntityToCauThuResponse(cauThuList);
    }

    @Override
    public Page<CauThuResponse> pageCauThuInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CauThu> cauThuPage = cauThuRepository.pageINACTIVE(pageable);
        return cauThuPage.map(cauThuMapper::cauThuEntityToCauThuResponse);

    }

    @Override
    public CauThuResponse add(CreateCauThuRequest createCauThuRequest) {
        CauThu cauThu = cauThuMapper.createCauThuRequestToCauThuEntity(createCauThuRequest);
        cauThu.setMa(GenCode.generateCauThuCode());
        cauThu.setNgayTao(LocalDate.now());
        cauThu.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return cauThuMapper.cauThuEntityToCauThuResponse(cauThuRepository.save(cauThu));
    }

    @Override
    public CauThuResponse update(UpdateCauThuRequest updateCauThuRequest) {
        CauThu cauThu = cauThuMapper.updateCauThuRequestToCauThuEntity(updateCauThuRequest);
        cauThu.setNgayCapNhat(LocalDate.now());
        cauThu.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return cauThuMapper.cauThuEntityToCauThuResponse(cauThuRepository.save(cauThu));
    }

    @Override
    public CauThuResponse getOne(Integer id) {
        Optional<CauThu> cauThuOptional = cauThuRepository.findById(id);
        return cauThuMapper.cauThuEntityToCauThuResponse(cauThuOptional.get());
    }

    @Override
    public Page<CauThuResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CauThu> cauThuPage = cauThuRepository.pageSearchActive(searchName, pageable);
        return cauThuPage.map(cauThuMapper::cauThuEntityToCauThuResponse);
    }

    @Override
    public Page<CauThuResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CauThu> cauThuPage = cauThuRepository.pageSearchIvActive(searchName, pageable);
        return cauThuPage.map(cauThuMapper::cauThuEntityToCauThuResponse);
    }


    @Override
    public void deleteCauThu(Integer id, LocalDate now) {
        cauThuRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertCauThu(Integer id, LocalDate now) {
        cauThuRepository.revert(id, LocalDate.now());
    }
}
