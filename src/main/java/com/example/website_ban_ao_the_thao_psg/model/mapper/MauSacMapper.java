package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MauSacMapper {

    MauSac mauSacResponseToMauSacEntity(MauSacResponse mauSacResponse);

    MauSacResponse mauSacEntityToMauSacResponse(MauSac mauSac);

    MauSac createMauSacRequestToMauSacEntity(CreateMauSacRequest createMauSacRequest);

    MauSac updateMauSacRequestToMauSacEntity(UpdateMauSacRequest updateMauSacRequest);

    List<MauSacResponse> listMauSacEntityToMauSacResponse(List<MauSac> mauSacList);
}
