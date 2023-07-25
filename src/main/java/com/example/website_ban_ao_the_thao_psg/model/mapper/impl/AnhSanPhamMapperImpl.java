package com.example.website_ban_ao_the_thao_psg.model.mapper.impl;

import com.example.website_ban_ao_the_thao_psg.entity.AnhSanPham;
import com.example.website_ban_ao_the_thao_psg.model.mapper.AnhSanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import jakarta.annotation.Generated;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-03-29T01:09:18+0700", comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)")
@Component
public class AnhSanPhamMapperImpl implements AnhSanPhamMapper {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AnhSanPham anhSanPhamResponeToAnhSanPhamEntity(AnhSanPhamResponse anhSanPhamResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        AnhSanPham anhSanPham = modelMapper.map(anhSanPhamResponse, AnhSanPham.class);
        return anhSanPham;
    }

    @Override
    public AnhSanPhamResponse anhSanPhamEntityToAnhSanPhamResponse(AnhSanPham anhSanPham) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        AnhSanPhamResponse anhSanPhamResponse = modelMapper.map(anhSanPham, AnhSanPhamResponse.class);
        return anhSanPhamResponse;
    }

    @Override
    public AnhSanPham createAnhSanPhamRequestToAnhSanPhamEntity(CreateAnhSanPhamRequest createAnhSanPhamRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        AnhSanPham anhSanPham = modelMapper.map(createAnhSanPhamRequest, AnhSanPham.class);
        return anhSanPham;
    }

    @Override
    public AnhSanPham updateAnhSanPhamRequestToAnhSanPhamEntity(UpdateAnhSanPhamRequest updateAnhSanPhamRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        AnhSanPham anhSanPham = modelMapper.map(updateAnhSanPhamRequest, AnhSanPham.class);
        return anhSanPham;
    }

    @Override
    public List<AnhSanPhamResponse> listAnhSanPhamEntityToAnhSanPhamResponse(List<AnhSanPham> anhSanPhamList) {
        List<AnhSanPhamResponse> list = new ArrayList<>(anhSanPhamList.size());
        for (AnhSanPham anhSP : anhSanPhamList) {
            list.add(anhSanPhamEntityToAnhSanPhamResponse(anhSP));
        }
        return list;
    }
}
