package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.KhachHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
@Component
public class KhachHangMapperImpl implements KhachHangMapper {

    @Autowired
    ModelMapper modelMapper;
    @Override
    public KhachHang khachHangResponseToTaiKhoanEntity(KhachHangResponse khachHangResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        KhachHang khachHang =modelMapper.map(khachHangResponse, KhachHang.class);
        return khachHang;
    }

    @Override
    public KhachHangResponse khachHangEntityToTaiKhoanResponse(KhachHang khachHang) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        KhachHangResponse khachHangResponse =modelMapper.map(khachHang, KhachHangResponse.class);
        return khachHangResponse;
    }

    @Override
    public KhachHang createKhachHangRequestToTaiKhoanEntity(CreateKhachHangRequest createKhachHangRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        KhachHang khachHang =modelMapper.map(createKhachHangRequest, KhachHang.class);
        return khachHang;
    }

    @Override
    public KhachHang updateKhachHangRequestToTaiKhoanEntity(UpdateKhachHangRequest updateKhachHangRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        KhachHang khachHang =modelMapper.map(updateKhachHangRequest, KhachHang.class);
        return khachHang;
    }
}
