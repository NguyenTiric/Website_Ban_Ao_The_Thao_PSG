package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.CongNghe;
import com.example.website_ban_ao_the_thao_psg.model.mapper.CongNgheMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CongNgheResponse;
import com.example.website_ban_ao_the_thao_psg.repository.CongNgheRepository;
import com.example.website_ban_ao_the_thao_psg.service.CongNgheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class CongNgheServiceImpl implements CongNgheService {
    @Autowired
    CongNgheRepository congNgheRepository;

    @Autowired
    CongNgheMapper congNgheMapper;


    @Override
    public Page<CongNgheResponse> pageCongNgheActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CongNghe> congNghePage = congNgheRepository.pageACTIVE(pageable);
        return congNghePage.map(congNgheMapper::congNgheEntityToCongNgheResponse);
    }

    @Override
    public Page<CongNgheResponse> pageCongNgheInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CongNghe> congNghePage = congNgheRepository.pageINACTIVE(pageable);
        return congNghePage.map(congNgheMapper::congNgheEntityToCongNgheResponse);

    }

    @Override
    public CongNgheResponse add(CreateCongNgheRequest createCongNgheRequest) {
        CongNghe congNghe = congNgheMapper.createCongNgheRequestToCongNgheEntity(createCongNgheRequest);
        congNghe.setNgayTao(LocalDate.now());
        congNghe.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return congNgheMapper.congNgheEntityToCongNgheResponse(congNgheRepository.save(congNghe));
    }

    @Override
    public CongNgheResponse update(UpdateCongNgheRequest updateCongNgheRequest) {
        CongNghe congNghe = congNgheMapper.updateCongNgheRequestToCongNgheEntity(updateCongNgheRequest);
        congNghe.setNgayCapNhat(LocalDate.now());
        return congNgheMapper.congNgheEntityToCongNgheResponse(congNgheRepository.save(congNghe));
    }

    @Override
    public CongNgheResponse getOne(Integer id) {
        Optional<CongNghe> congNgheOptional = congNgheRepository.findById(id);
        return congNgheMapper.congNgheEntityToCongNgheResponse(congNgheOptional.get());
    }

    @Override
    public Page<CongNgheResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<CongNghe> congNghePage = congNgheRepository.pageSearch(searchName, pageable);
        return congNghePage.map(congNgheMapper::congNgheEntityToCongNgheResponse);

    }

    @Override
    public void deleteCongNghe(Integer id, LocalDate now) {
        congNgheRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertCongNghe(Integer id, LocalDate now) {
        congNgheRepository.revert(id, LocalDate.now());
    }
}
