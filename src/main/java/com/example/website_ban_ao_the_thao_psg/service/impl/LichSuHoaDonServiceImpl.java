package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.LichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.model.mapper.LichSuHoaDonMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.repository.HoaDonRepository;
import com.example.website_ban_ao_the_thao_psg.repository.LichSuHoaDonRepository;
import com.example.website_ban_ao_the_thao_psg.service.LichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Component
public class LichSuHoaDonServiceImpl implements LichSuHoaDonService {

    @Autowired
    private LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private LichSuHoaDonMapper lichSuHoaDonMapper;

    @Override
    public List<LichSuHoaDonResponse> getAll(Integer idHd) {
        List<LichSuHoaDon> list = lichSuHoaDonRepository.getLichSuHoaDonByHoaDon(hoaDonRepository.findById(idHd).get());
        return lichSuHoaDonMapper.listLichSuHoaDonEntityToLichSuHoaDonResponse(list);
    }

    @Override
    public LichSuHoaDonResponse add(CreateLichSuHoaDonRequest createLichSuHoaDonRequest) {
        LichSuHoaDon lichSuHoaDon = lichSuHoaDonMapper.createLichSuHoaDonRequestToLichSuHoaDonEntity(createLichSuHoaDonRequest);
        lichSuHoaDon.setNgayTao(LocalDateTime.now());
        return null;
    }


}
