package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.GiaoDich;
import com.example.website_ban_ao_the_thao_psg.model.mapper.GiaoDichMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.GiaoDichMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateGiaoDichRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateGiaoDichRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.GiaoDichResponse;
import jakarta.annotation.Generated;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
public class GiaoDichMapperImpl implements GiaoDichMapper {

    @Autowired
    ModelMapper mapper;

    @Override
    public GiaoDich giaoDichResponseToGiaoDichEntity(GiaoDichResponse giaoDichResponse) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        GiaoDich giaoDich = mapper.map(giaoDichResponse, GiaoDich.class);
        return giaoDich;
    }

    @Override
    public GiaoDichResponse giaoDichEntityToGiaoDichResponse(GiaoDich giaoDich) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        GiaoDichResponse giaoDichResponse = mapper.map(giaoDich, GiaoDichResponse.class);
        return giaoDichResponse;
    }

    @Override
    public GiaoDich createGiaoDichRequestToGiaoDichEntity(CreateGiaoDichRequest createGiaoDichRequest) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        GiaoDich giaoDich = mapper.map(createGiaoDichRequest, GiaoDich.class);
        return giaoDich;
    }

    @Override
    public GiaoDich updateGiaoDichRequestToGiaoDichEntity(UpdateGiaoDichRequest updateGiaoDichRequest) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        GiaoDich giaoDich = mapper.map(updateGiaoDichRequest, GiaoDich.class);
        return giaoDich;
    }

    @Override
    public List<GiaoDichResponse> listGiaoDichEntityToGiaoDichResponse(List<GiaoDich> listGiaoDich) {
        List<GiaoDichResponse> list = new ArrayList<>(listGiaoDich.size());
        for (GiaoDich hd : listGiaoDich) {
            list.add(giaoDichEntityToGiaoDichResponse(hd));
        }
        return list;
    }
}
