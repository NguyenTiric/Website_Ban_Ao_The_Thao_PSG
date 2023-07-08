package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.controller.MauSacController;
import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
import com.example.website_ban_ao_the_thao_psg.model.mapper.MauSacMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import com.example.website_ban_ao_the_thao_psg.repository.MauSacRepository;
import com.example.website_ban_ao_the_thao_psg.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    MauSacMapper mauSacMapper;

    @Override
    public Page<MauSacResponse> pageMauSacResponse(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<MauSac> mauSacPage = mauSacRepository.findAll(pageable);
        return mauSacPage.map(mauSacMapper::mauSacEntityToMauSacResponse);
    }

    @Override
    public MauSacResponse add(CreateMauSacRequest createMauSacRequest) {
        MauSac mauSac = mauSacMapper.createMauSacRequestToMauSacEntity(createMauSacRequest);
        mauSac.setNgayTao(LocalDate.now());
        mauSac.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return mauSacMapper.mauSacEntityToMauSacResponse(mauSacRepository.save(mauSac));
    }

    @Override
    public MauSacResponse update(UpdateMauSacRequest updateMauSacRequest) {
        MauSac mauSac = mauSacMapper.updateMauSacRequestToMauSacEntity(updateMauSacRequest);
        return mauSacMapper.mauSacEntityToMauSacResponse(mauSacRepository.save(mauSac));
    }

    @Override
    public MauSacResponse getOne(Integer id) {
        Optional<MauSac>mauSacOptional =mauSacRepository.findById(id);
        return mauSacMapper.mauSacEntityToMauSacResponse(mauSacOptional.get());
    }

    @Override
    public MauSacResponse delete(UpdateMauSacRequest updateMauSacRequest, Integer id) {
        return null;
    }
}
