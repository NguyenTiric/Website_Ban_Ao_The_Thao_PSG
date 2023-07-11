package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.VoucherThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.VoucherThuHangResponse;
import jakarta.annotation.Generated;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
@Component
public class VoucherThuHangMapperImpl implements VoucherThuHangMapper {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public VoucherThuHang voucherThuHangResponseToVoucherThuHangEntity(VoucherThuHangResponse voucherThuHangResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        VoucherThuHang voucherThuHang = modelMapper.map(voucherThuHangResponse, VoucherThuHang.class);
        return voucherThuHang;
    }

    @Override
    public VoucherThuHangResponse voucherThuHangEntityToVoucherThuHangResponse(VoucherThuHang voucherThuHang) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        VoucherThuHangResponse voucherThuHangResponse = modelMapper.map(voucherThuHang, VoucherThuHangResponse.class);

        return voucherThuHangResponse;
    }

    @Override
    public VoucherThuHang createVoucherThuHangRequestToVoucherThuHangEntity(CreateThuongHieuRequest createThuongHieuRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        VoucherThuHang voucherThuHang = modelMapper.map(createThuongHieuRequest, VoucherThuHang.class);
        return voucherThuHang;
    }

    @Override
    public VoucherThuHang updateVoucherThuHangRequestToVoucherThuHangEntity(UpdateVoucherThuHangRequest updateVoucherThuHangRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        VoucherThuHang voucherThuHang = modelMapper.map(updateVoucherThuHangRequest, VoucherThuHang.class);
        return voucherThuHang;
    }

    @Override
    public List<VoucherThuHangResponse> listVoucherThuHangEntityToVoucherThuHangResponse(List<VoucherThuHang> voucherThuHangList) {
        List<VoucherThuHangResponse> list = new ArrayList<>(voucherThuHangList.size());
        for (VoucherThuHang th : voucherThuHangList) {
            list.add(voucherThuHangEntityToVoucherThuHangResponse(th));
        }
        return list;
    }
}
