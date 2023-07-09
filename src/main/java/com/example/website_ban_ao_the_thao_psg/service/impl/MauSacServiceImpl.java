package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
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
    public Page<MauSacResponse> pageMauSacActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<MauSac> mauSacPage = mauSacRepository.pageACTIVE(pageable);
        return mauSacPage.map(mauSacMapper::mauSacEntityToMauSacResponse);

    }

    @Override
    public Page<MauSacResponse> pageMauSacInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<MauSac> mauSacPage = mauSacRepository.pageINACTIVE(pageable);
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
        mauSac.setNgayCapNhat(LocalDate.now());
        mauSac.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return mauSacMapper.mauSacEntityToMauSacResponse(mauSacRepository.save(mauSac));
    }

    @Override
    public MauSacResponse getOne(Integer id) {
        Optional<MauSac> mauSacOptional = mauSacRepository.findById(id);
        return mauSacMapper.mauSacEntityToMauSacResponse(mauSacOptional.get());
    }

    @Override
    public Page<MauSacResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<MauSac> mauSacPage = mauSacRepository.pageSearchActive(searchName, pageable);
        return mauSacPage.map(mauSacMapper::mauSacEntityToMauSacResponse);
    }

    @Override
    public Page<MauSacResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<MauSac> mauSacPage = mauSacRepository.pageSearchIvActive(searchName, pageable);
        return mauSacPage.map(mauSacMapper::mauSacEntityToMauSacResponse);
    }

    @Override
    public void deleteMauSac(Integer id,LocalDate now) {
        mauSacRepository.delete(id,LocalDate.now());
    }

    @Override
    public void revertMauSac(Integer id,LocalDate now) {
        mauSacRepository.revert(id,LocalDate.now());
    }


}
