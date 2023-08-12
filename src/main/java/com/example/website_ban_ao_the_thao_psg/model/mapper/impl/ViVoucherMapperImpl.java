package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.ViVoucher;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ViVoucherMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ViVoucherMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateViVoucherRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateViVoucherRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ViVoucherResponse;
import jakarta.annotation.Generated;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
@Component
public class ViVoucherMapperImpl implements ViVoucherMapper {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ViVoucher viVoucherResponseToViVoucherEntity(ViVoucherResponse viVoucherResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ViVoucher viVoucher = modelMapper.map(viVoucherResponse, ViVoucher.class);
        return viVoucher;
    }

    @Override
    public ViVoucherResponse viVoucherEntityToViVoucherResponse(ViVoucher viVoucher) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ViVoucherResponse viVoucherResponse = modelMapper.map(viVoucher, ViVoucherResponse.class);
        return viVoucherResponse;
    }

    @Override
    public ViVoucher createViVoucherRequestToViVoucherEntity(CreateViVoucherRequest createViVoucherRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ViVoucher viVoucher = modelMapper.map(createViVoucherRequest, ViVoucher.class);
        return viVoucher;
    }

    @Override
    public ViVoucher updateViVoucherRequestToViVoucherEntity(UpdateViVoucherRequest updateViVoucherRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ViVoucher viVoucher = modelMapper.map(updateViVoucherRequest, ViVoucher.class);
        return viVoucher;
    }

    @Override
    public List<ViVoucherResponse> listViVoucherEntityToViVoucherResponse(List<ViVoucher> viVoucherList) {
        List<ViVoucherResponse> list = new ArrayList<>(viVoucherList.size());
        for (ViVoucher th : viVoucherList) {
            list.add(viVoucherEntityToViVoucherResponse(th));
        }
        return list;
    }
}
