package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.NuocSanXuat;
import com.example.website_ban_ao_the_thao_psg.model.mapper.NuocSanXuatMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NuocSanXuatResponse;
import com.example.website_ban_ao_the_thao_psg.repository.NuocSanXuatRepository;
import com.example.website_ban_ao_the_thao_psg.service.NuocSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class NuocSanXuatServiceImpl implements NuocSanXuatService {

    @Autowired
    NuocSanXuatRepository nuocSanXuatRepository;

    @Autowired
    NuocSanXuatMapper nuocSanXuatMapper;


    @Override
    public Page<NuocSanXuatResponse> pageNuocSanXuatActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<NuocSanXuat> nuocSanXuatPage = nuocSanXuatRepository.pageACTIVE(pageable);
        return nuocSanXuatPage.map(nuocSanXuatMapper::nuocSanXuatEntityToNuocSanXuatResponse);

    }

    @Override
    public Page<NuocSanXuatResponse> pageNuocSanXuatInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<NuocSanXuat> nuocSanXuatPage = nuocSanXuatRepository.pageINACTIVE(pageable);
        return nuocSanXuatPage.map(nuocSanXuatMapper::nuocSanXuatEntityToNuocSanXuatResponse);

    }

    @Override
    public NuocSanXuatResponse add(CreateNuocSanXuatRequest createNuocSanXuatRequest) {
        NuocSanXuat nuocSanXuat = nuocSanXuatMapper.createNuocSanXuatRequestToNuocSanXuatEntity(createNuocSanXuatRequest);
        nuocSanXuat.setMa(GenCode.generateNuocSanXuatCode());
        nuocSanXuat.setNgayTao(LocalDate.now());
        nuocSanXuat.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return nuocSanXuatMapper.nuocSanXuatEntityToNuocSanXuatResponse(nuocSanXuatRepository.save(nuocSanXuat));
    }

    @Override
    public NuocSanXuatResponse update(UpdateNuocSanXuatRequest updateNuocSanXuatRequest) {
        NuocSanXuat nuocSanXuat = nuocSanXuatMapper.updateNuocSanXuatRequestToNuocSanXuatEntity(updateNuocSanXuatRequest);
        nuocSanXuat.setNgayCapNhat(LocalDate.now());
        nuocSanXuat.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return nuocSanXuatMapper.nuocSanXuatEntityToNuocSanXuatResponse(nuocSanXuatRepository.save(nuocSanXuat));
    }

    @Override
    public NuocSanXuatResponse getOne(Integer id) {
        Optional<NuocSanXuat> nuocSanXuatOptional = nuocSanXuatRepository.findById(id);
        return nuocSanXuatMapper.nuocSanXuatEntityToNuocSanXuatResponse(nuocSanXuatOptional.get());
    }

    @Override
    public Page<NuocSanXuatResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<NuocSanXuat> nuocSanXuatPage = nuocSanXuatRepository.pageSearchActive(searchName, pageable);
        return nuocSanXuatPage.map(nuocSanXuatMapper::nuocSanXuatEntityToNuocSanXuatResponse);
    }

    @Override
    public Page<NuocSanXuatResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<NuocSanXuat> nuocSanXuatPage = nuocSanXuatRepository.pageSearchIvActive(searchName, pageable);
        return nuocSanXuatPage.map(nuocSanXuatMapper::nuocSanXuatEntityToNuocSanXuatResponse);
    }


    @Override
    public void deleteNuocSanXuat(Integer id,LocalDate now) {
        nuocSanXuatRepository.delete(id,LocalDate.now());
    }

    @Override
    public void revertNuocSanXuat(Integer id,LocalDate now) {
        nuocSanXuatRepository.revert(id,LocalDate.now());
    }
}
