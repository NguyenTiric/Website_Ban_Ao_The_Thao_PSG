package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.entity.LichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.model.mapper.LichSuHoaDonMapper;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.repository.LichSuHoaDonRepository;
import com.example.website_ban_ao_the_thao_psg.service.LichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class LichSuHoaDonServiceImpl implements LichSuHoaDonService {

    @Autowired
    private LichSuHoaDonRepository lichSuHoaDonRepository;

//    @Autowired
//    private LichSuHoaDonMapper lichSuHoaDonMapper;
    @Override
    public List<LichSuHoaDonResponse> getAll() {
//        List<LichSuHoaDon>lichSuHoaDon = lichSuHoaDonRepository.findAll();
//        Stream<LichSuHoaDon>lichSuHoaDons = lichSuHoaDon.stream();
//        return lichSuHoaDons.map(lichSuHoaDonMapper.listHoaDonEntityToHoaDonResponse(lichSuHoaDon));
        return null;
    }
}
