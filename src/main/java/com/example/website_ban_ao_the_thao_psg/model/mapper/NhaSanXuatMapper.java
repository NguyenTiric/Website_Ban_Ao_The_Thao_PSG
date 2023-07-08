package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.NhaSanXuat;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NhaSanXuatResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NhaSanXuatMapper {

    NhaSanXuat nhaSanXuatResponseToNhaSanXuatEntity(NhaSanXuatResponse nhaSanXuatResponse);

    NhaSanXuatResponse nhaSanXuatEntityToNhaSanXuatResponse(NhaSanXuat nhaSanXuat);

    NhaSanXuat createNhaSanXuatRequestToNhaSanXuatEntity(CreateNhaSanXuatRequest createNhaSanXuatRequest);

    NhaSanXuat updateNhaSanXuatRequestToNhaSanXuatEntity(UpdateNhaSanXuatRequest updateNhaSanXuatRequest);

    List<NhaSanXuatResponse> listNhaSanXuatEntityToNhaSanXuatRespnse(List<NhaSanXuat> nhaSanXuatList);
}
