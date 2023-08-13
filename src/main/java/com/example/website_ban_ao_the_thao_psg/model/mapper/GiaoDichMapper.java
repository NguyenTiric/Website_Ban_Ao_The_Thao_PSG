package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.GiaoDich;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateGiaoDichRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateGiaoDichRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.GiaoDichResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GiaoDichMapper {
    GiaoDich giaoDichResponseToGiaoDichEntity(GiaoDichResponse giaoDichResponse);

    GiaoDichResponse giaoDichEntityToGiaoDichResponse(GiaoDich giaoDich);

    GiaoDich createGiaoDichRequestToGiaoDichEntity(CreateGiaoDichRequest createGiaoDichRequest);

    GiaoDich updateGiaoDichRequestToGiaoDichEntity(UpdateGiaoDichRequest updateGiaoDichRequest);

    List<GiaoDichResponse> listGiaoDichEntityToGiaoDichResponse(List<GiaoDich> listGiaoDich);
}
