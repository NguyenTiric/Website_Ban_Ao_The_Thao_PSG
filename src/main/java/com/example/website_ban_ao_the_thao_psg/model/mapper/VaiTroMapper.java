package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVaiTroRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVaiTroRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.VaiTroResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaiTroMapper {

    VaiTro vaiTroResponseToVaiTroEntity(VaiTroResponse vaiTroResponse);

    VaiTroResponse vaiTroEntityToVaiTroResponse(VaiTro vaiTro);

    VaiTro createVaiTroRequestToVaiTroEntity(CreateVaiTroRequest createVaiTroRequest);

    VaiTro updateVaiTroRequestToVaiTroEntity(UpdateVaiTroRequest updateVaiTroRequest);

    List<VaiTroResponse> listVaiTroEntityToVaiTroResponse(List<VaiTro> vaiTroList);
}
