package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.mapper.NhanVienMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.NhanVienResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
@Component
public class NhanVienMapperImpl implements NhanVienMapper {
    @Autowired
    ModelMapper modelMapper;
    @Override
    public TaiKhoan taiKhoanResponseToTaiKhoanEntity(NhanVienResponse nhanVienResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(nhanVienResponse,TaiKhoan.class);
        return taiKhoan;
    }

    @Override
    public KhachHangResponse nhanVienEntityToTaiKhoanResponse(TaiKhoan taiKhoan) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        KhachHangResponse khachHangResponse =modelMapper.map(taiKhoan, KhachHangResponse.class);
        return khachHangResponse;
    }

    @Override
    public TaiKhoan createNhanVienRequestToTaiKhoanEntity(CreateNhanVienRequest createTaiKhoanRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(createTaiKhoanRequest,TaiKhoan.class);
        return taiKhoan;
    }

    @Override
    public TaiKhoan updateNhanVienRequestToTaiKhoanEntity(UpdateNhanVienRequest updateNhanVienRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(updateNhanVienRequest,TaiKhoan.class);
        return taiKhoan;
    }



    @Override
    public List<KhachHangResponse> listTaiKhoanEntityToTaiKhoanResponse(List<TaiKhoan> taiKhoanList) {
        List<KhachHangResponse> list = new ArrayList<>(taiKhoanList.size());
        for (TaiKhoan th : taiKhoanList) {
            list.add(nhanVienEntityToTaiKhoanResponse(th));
        }
        return list;
    }
    }

