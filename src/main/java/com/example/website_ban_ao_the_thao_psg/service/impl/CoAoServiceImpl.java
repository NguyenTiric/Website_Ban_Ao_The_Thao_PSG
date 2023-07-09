package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.CoAo;
import com.example.website_ban_ao_the_thao_psg.model.mapper.CoAoMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CoAoResponse;
import com.example.website_ban_ao_the_thao_psg.repository.CoAoRepository;
import com.example.website_ban_ao_the_thao_psg.service.CoAoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class CoAoServiceImpl implements CoAoService {
    @Autowired
    CoAoRepository coAoRepository;

    @Autowired
    CoAoMapper coAoMapper;


    @Override
    public Page<CoAoResponse> pageCoAoActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CoAo> coAoPage = coAoRepository.pageACTIVE(pageable);
        return coAoPage.map(coAoMapper::coAoEntityToCoAoResponse);
    }

    @Override
    public Page<CoAoResponse> pageCoAoInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CoAo> coAoPage = coAoRepository.pageINACTIVE(pageable);
        return coAoPage.map(coAoMapper::coAoEntityToCoAoResponse);

    }

    @Override
    public CoAoResponse add(CreateCoAoRequest createCoAoRequest) {
        CoAo coAo = coAoMapper.createCoAoRequestToCoAoEntity(createCoAoRequest);
        coAo.setNgayTao(LocalDate.now());
        coAo.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return coAoMapper.coAoEntityToCoAoResponse(coAoRepository.save(coAo));
    }

    @Override
    public CoAoResponse update(UpdateCoAoRequest updateCoAoRequest) {
        CoAo coAo = coAoMapper.updateCoAoRequestToCoAoEntity(updateCoAoRequest);
        coAo.setNgayCapNhat(LocalDate.now());
        return coAoMapper.coAoEntityToCoAoResponse(coAoRepository.save(coAo));
    }

    @Override
    public CoAoResponse getOne(Integer id) {
        Optional<CoAo> coAoOptional = coAoRepository.findById(id);
        return coAoMapper.coAoEntityToCoAoResponse(coAoOptional.get());
    }

    @Override
    public Page<CoAoResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CoAo> coAoPage = coAoRepository.pageSearch(searchName, pageable);
        return coAoPage.map(coAoMapper::coAoEntityToCoAoResponse);

    }

    @Override
    public void deleteCoAo(Integer id, LocalDate now) {
        coAoRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertCoAo(Integer id, LocalDate now) {
        coAoRepository.revert(id, LocalDate.now());
    }
}
