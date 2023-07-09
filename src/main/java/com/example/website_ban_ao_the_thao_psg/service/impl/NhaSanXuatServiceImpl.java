package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.NhaSanXuat;
import com.example.website_ban_ao_the_thao_psg.model.mapper.NhaSanXuatMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NhaSanXuatResponse;
import com.example.website_ban_ao_the_thao_psg.repository.NhaSanXuatRepository;
import com.example.website_ban_ao_the_thao_psg.service.NhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class NhaSanXuatServiceImpl implements NhaSanXuatService {

    @Autowired
    NhaSanXuatRepository nhaSanXuatRepository;

    @Autowired
    NhaSanXuatMapper nhaSanXuatMapper;


    @Override
    public Page<NhaSanXuatResponse> pageNhaSanXuatActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<NhaSanXuat> nhaSanXuatPage = nhaSanXuatRepository.pageACTIVE(pageable);
        return nhaSanXuatPage.map(nhaSanXuatMapper::nhaSanXuatEntityToNhaSanXuatResponse);

    }

    @Override
    public Page<NhaSanXuatResponse> pageNhaSanXuatInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<NhaSanXuat> nhaSanXuatPage = nhaSanXuatRepository.pageINACTIVE(pageable);
        return nhaSanXuatPage.map(nhaSanXuatMapper::nhaSanXuatEntityToNhaSanXuatResponse);

    }

    @Override
    public NhaSanXuatResponse add(CreateNhaSanXuatRequest createNhaSanXuatRequest) {
        NhaSanXuat nhaSanXuat = nhaSanXuatMapper.createNhaSanXuatRequestToNhaSanXuatEntity(createNhaSanXuatRequest);
        nhaSanXuat.setNgayTao(LocalDate.now());
        nhaSanXuat.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return nhaSanXuatMapper.nhaSanXuatEntityToNhaSanXuatResponse(nhaSanXuatRepository.save(nhaSanXuat));
    }

    @Override
    public NhaSanXuatResponse update(UpdateNhaSanXuatRequest updateNhaSanXuatRequest) {
        NhaSanXuat nhaSanXuat = nhaSanXuatMapper.updateNhaSanXuatRequestToNhaSanXuatEntity(updateNhaSanXuatRequest);
        nhaSanXuat.setNgayCapNhat(LocalDate.now());
        return nhaSanXuatMapper.nhaSanXuatEntityToNhaSanXuatResponse(nhaSanXuatRepository.save(nhaSanXuat));
    }

    @Override
    public NhaSanXuatResponse getOne(Integer id) {
        Optional<NhaSanXuat> nhaSanXuatOptional = nhaSanXuatRepository.findById(id);
        return nhaSanXuatMapper.nhaSanXuatEntityToNhaSanXuatResponse(nhaSanXuatOptional.get());
    }

    @Override
    public Page<NhaSanXuatResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<NhaSanXuat> nhaSanXuatPage = nhaSanXuatRepository.pageSearch(searchName, pageable);
        return nhaSanXuatPage.map(nhaSanXuatMapper::nhaSanXuatEntityToNhaSanXuatResponse);

    }

    @Override
    public void deleteNhaSanXuat(Integer id,LocalDate now) {
        nhaSanXuatRepository.delete(id,LocalDate.now());
    }

    @Override
    public void revertNhaSanXuat(Integer id,LocalDate now) {
        nhaSanXuatRepository.revert(id,LocalDate.now());
    }
}
