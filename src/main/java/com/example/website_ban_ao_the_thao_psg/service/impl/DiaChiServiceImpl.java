package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.DiaChi;
import com.example.website_ban_ao_the_thao_psg.model.mapper.DiaChiMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DiaChiResponse;
import com.example.website_ban_ao_the_thao_psg.repository.DiaChiRepository;
import com.example.website_ban_ao_the_thao_psg.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DiaChiServiceImpl implements DiaChiService {
    @Autowired
    DiaChiRepository diaChiRepository;

    @Autowired
    DiaChiMapper diaChiMapper;


    @Override
    public Page<DiaChiResponse> pageDiaChiActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<DiaChi> diaChiPage = diaChiRepository.pageACTIVE(pageable);
        return diaChiPage.map(diaChiMapper::diaChiEntityToDiaChiResponse);
    }

    @Override
    public Page<DiaChiResponse> pageDiaChiInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<DiaChi> diaChiPage = diaChiRepository.pageINACTIVE(pageable);
        return diaChiPage.map(diaChiMapper::diaChiEntityToDiaChiResponse);

    }

    @Override
    public DiaChiResponse add(CreateDiaChiRequest createDiaChiRequest) {
        DiaChi diaChi = diaChiMapper.createDiaChiRequestToDiaChiEntity(createDiaChiRequest);
        diaChi.setNgayTao(LocalDate.now());
        diaChi.setTrangThai(ApplicationConstant.TrangThaiDiaChi.ACTIVE);
        return diaChiMapper.diaChiEntityToDiaChiResponse(diaChiRepository.save(diaChi));
    }

    @Override
    public DiaChiResponse update(UpdateDiaChiRequest updateDiaChiRequest) {
        DiaChi diaChi = diaChiMapper.updateDiaChiRequestToDiaChiEntity(updateDiaChiRequest);
        diaChi.setNgayCapNhap(LocalDate.now());
        diaChi.setTrangThai(ApplicationConstant.TrangThaiDiaChi.ACTIVE);
        return diaChiMapper.diaChiEntityToDiaChiResponse(diaChiRepository.save(diaChi));
    }

    @Override
    public DiaChiResponse getOne(Integer id) {
        Optional<DiaChi> diaChiOptional = diaChiRepository.findById(id);
        return diaChiMapper.diaChiEntityToDiaChiResponse(diaChiOptional.get());
    }

    @Override
    public Page<DiaChiResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<DiaChi> diaChiPage = diaChiRepository.pageSearchActive(searchName, pageable);
        return diaChiPage.map(diaChiMapper::diaChiEntityToDiaChiResponse);
    }

    @Override
    public Page<DiaChiResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<DiaChi> diaChiPage = diaChiRepository.pageSearchIvActive(searchName, pageable);
        return diaChiPage.map(diaChiMapper::diaChiEntityToDiaChiResponse);
    }

    @Override
    public void deleteDiaChi(Integer id, LocalDate now) {
        diaChiRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertDiaChi(Integer id, LocalDate now) {

        diaChiRepository.revert(id, LocalDate.now());
    }
}
