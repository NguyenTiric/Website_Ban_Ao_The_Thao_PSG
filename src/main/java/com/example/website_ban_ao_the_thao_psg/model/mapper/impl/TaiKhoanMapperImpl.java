package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.mapper.TaiKhoanMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
@Component
public class TaiKhoanMapperImpl implements TaiKhoanMapper {
    @Autowired
    ModelMapper modelMapper;
    @Override
    public TaiKhoan taiKhoanResponseToTaiKhoanEntity(TaiKhoanResponse taiKhoanResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(taiKhoanResponse,TaiKhoan.class);
        return taiKhoan;
    }

    @Override
    public TaiKhoanResponse taiKhoanEntityToTaiKhoanResponse(TaiKhoan taiKhoan) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoanResponse taiKhoanResponse=modelMapper.map(taiKhoan,TaiKhoanResponse.class);
        return taiKhoanResponse;
    }

    @Override
    public TaiKhoan createTaiKhoanRequestToTaiKhoanEntity(CreateNhanVienRequest createTaiKhoanRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(createTaiKhoanRequest,TaiKhoan.class);
        return taiKhoan;
    }

    @Override
    public TaiKhoan updateTaiKhoanRequestToTaiKhoanEntity(UpdateNhanVienRequest updateNhanVienRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(updateNhanVienRequest,TaiKhoan.class);
        return taiKhoan;
    }

    @Override
    public TaiKhoan createTaiKhoanRequestToTaiKhoanEntity(CreateKhachHangRequest createKhachHangRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(createKhachHangRequest,TaiKhoan.class);
        return taiKhoan;
    }

    @Override
    public TaiKhoan updateTaiKhoanRequestToTaiKhoanEntity(UpdateKhachHangRequest updateKhachHangRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaiKhoan taiKhoan=modelMapper.map(updateKhachHangRequest,TaiKhoan.class);
        return taiKhoan;
    }

    @Override
    public List<TaiKhoanResponse> listTaiKhoanEntityToTaiKhoanResponse(List<TaiKhoan> taiKhoanList) {
        List<TaiKhoanResponse> list = new ArrayList<>(taiKhoanList.size());
        for (TaiKhoan th : taiKhoanList) {
            list.add(taiKhoanEntityToTaiKhoanResponse(th));
        }
        return list;
    }
    }

