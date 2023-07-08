package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.NuocSanXuat;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NuocSanXuatResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NuocSanXuatMapper {

    NuocSanXuat nuocSanXuatResponseToNuocSanXuatEntity(NuocSanXuatResponse nuocSanXuatResponse);

    NuocSanXuatResponse nuocSanXuatEntityToNuocSanXuatResponse(NuocSanXuat nuocSanXuat);

    NuocSanXuat createNuocSanXuatRequestToNuocSanXuatEntity(CreateNuocSanXuatRequest createNuocSanXuatRequest);

    NuocSanXuat updateNuocSanXuatRequestToNuocSanXuatEntity(UpdateNuocSanXuatRequest updateNuocSanXuatRequest);

    List<NuocSanXuatResponse> listNuocSanXuatEntityToNuocSanXuatResponses(List<NuocSanXuat> nuocSanXuatList);
}
