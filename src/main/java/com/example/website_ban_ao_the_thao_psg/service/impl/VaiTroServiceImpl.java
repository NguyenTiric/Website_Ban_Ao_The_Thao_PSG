package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.mapper.VaiTroMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVaiTroRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVaiTroRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.VaiTroResponse;
import com.example.website_ban_ao_the_thao_psg.repository.VaiTroRepository;
import com.example.website_ban_ao_the_thao_psg.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class VaiTroServiceImpl implements VaiTroService {
    @Autowired
    VaiTroRepository vaiTroRepository;

    @Autowired
    VaiTroMapper vaiTroMapper;


    @Override
    public Page<VaiTroResponse> pageVaiTroActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VaiTro> vaiTroPage = vaiTroRepository.pageACTIVE(pageable);
        return vaiTroPage.map(vaiTroMapper::vaiTroEntityToVaiTroResponse);
    }

    @Override
    public Page<VaiTroResponse> pageVaiTroInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VaiTro> vaiTroPage = vaiTroRepository.pageINACTIVE(pageable);
        return vaiTroPage.map(vaiTroMapper::vaiTroEntityToVaiTroResponse);

    }

    @Override
    public VaiTroResponse add(CreateVaiTroRequest createVaiTroRequest) {
        VaiTro vaiTro = vaiTroMapper.createVaiTroRequestToVaiTroEntity(createVaiTroRequest);
        vaiTro.setMa(GenCode.generateVaiTroCode());
        vaiTro.setNgayTao(LocalDate.now());
        vaiTro.setTrangThai(ApplicationConstant.TrangThaiVaiTro.ACTIVE);
        return vaiTroMapper.vaiTroEntityToVaiTroResponse(vaiTroRepository.save(vaiTro));
    }

    @Override
    public VaiTroResponse update(UpdateVaiTroRequest updateVaiTroRequest) {
        VaiTro vaiTro = vaiTroMapper.updateVaiTroRequestToVaiTroEntity(updateVaiTroRequest);
        vaiTro.setNgayCapNhap(LocalDate.now());
        return vaiTroMapper.vaiTroEntityToVaiTroResponse(vaiTroRepository.save(vaiTro));
    }

    @Override
    public VaiTroResponse getOne(Integer id) {
        Optional<VaiTro> vaiTroOptional = vaiTroRepository.findById(id);
        return vaiTroMapper.vaiTroEntityToVaiTroResponse(vaiTroOptional.get());
    }

    @Override
    public Page<VaiTroResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VaiTro> vaiTroPage = vaiTroRepository.pageSearchActive(searchName, pageable);
        return vaiTroPage.map(vaiTroMapper::vaiTroEntityToVaiTroResponse);
    }

    @Override
    public Page<VaiTroResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<VaiTro> vaiTroPage = vaiTroRepository.pageSearchIvActive(searchName, pageable);
        return vaiTroPage.map(vaiTroMapper::vaiTroEntityToVaiTroResponse);
    }


    @Override
    public void deleteVaiTro(Integer id, LocalDate now) {
        vaiTroRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertVaiTro(Integer id, LocalDate now) {
        vaiTroRepository.revert(id, LocalDate.now());
    }
}
