package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.LichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
import com.example.website_ban_ao_the_thao_psg.model.mapper.LichSuHoaDonMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import jakarta.annotation.Generated;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
public class LichSuHoaDonMapperImpl implements LichSuHoaDonMapper {
    @Autowired
    ModelMapper mapper;
    @Override
    public LichSuHoaDon hoaDonResponseToHoaDonEntity(LichSuHoaDonResponse lichSuHoaDonResponse) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        LichSuHoaDon lichSuHoaDon = mapper.map(lichSuHoaDonResponse,LichSuHoaDon.class);
        return lichSuHoaDon;
    }

    @Override
    public LichSuHoaDonResponse hoaDonEntityToHoaDonResponse(LichSuHoaDon lichSuHoaDon) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        LichSuHoaDonResponse lichSuHoaDonResponse = mapper.map(lichSuHoaDon,LichSuHoaDonResponse.class);
        return lichSuHoaDonResponse;
    }

    @Override
    public LichSuHoaDon createHoaDonRequestToHoaDonEntity(CreateLichSuHoaDonRequest createLichSuHoaDonRequest) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        LichSuHoaDon lichSuHoaDon = mapper.map(createLichSuHoaDonRequest,LichSuHoaDon.class);
        return lichSuHoaDon;
    }

    @Override
    public LichSuHoaDon updateHoaDonRequestToHoaDonEntity(UpdateLichSuHoaDonRequest updateLichSuHoaDonRequest) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        LichSuHoaDon lichSuHoaDon = mapper.map(updateLichSuHoaDonRequest,LichSuHoaDon.class);
        return lichSuHoaDon;
    }

    @Override
    public List<LichSuHoaDonResponse> listHoaDonEntityToHoaDonResponse(List<LichSuHoaDon> listHoaDon) {
        List<LichSuHoaDonResponse> list = new ArrayList<>(listHoaDon.size());
        for (LichSuHoaDon hd : listHoaDon) {
            list.add(hoaDonEntityToHoaDonResponse(hd));
        }
        return list;
    }
}
