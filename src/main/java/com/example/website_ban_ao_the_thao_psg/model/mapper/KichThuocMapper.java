package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KichThuocResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KichThuocMapper {

    KichThuoc kichThuocResponseToKichThuocEntity(KichThuocResponse kichThuocResponse);

    KichThuocResponse kichThuocEntityToKichThuocResponse(KichThuoc kichThuoc);

    KichThuoc createKichThuocRequestToKichThuocEntity(CreateKichThuocRequest createKichThuocRequest);

    KichThuoc updateKichThuocRequestToKichThuocEntity(UpdateKichThuocRequest updateKichThuocRequest);

    List<KichThuocResponse> listKichThuocEntityToKichThuocResponses(List<KichThuoc> kichThuocList);
}
