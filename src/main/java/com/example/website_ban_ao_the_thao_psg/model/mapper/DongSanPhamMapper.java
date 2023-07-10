package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.DongSanPham;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DongSanPhamResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DongSanPhamMapper {

    DongSanPham dongSanPhamResponseToDongSanPhamEntity(DongSanPhamResponse dongSanPhamResponse);

    DongSanPhamResponse dongSanPhamEntityToDongSanPhamResponse(DongSanPham dongSanPham);

    DongSanPham createDongSanPhamRequestToDongSanPhamEntity(CreateDongSanPhamRequest createDongSanPhamRequest);

    DongSanPham updateDongSanPhamRequestToDongSanPhamEntity(UpdateDongSanPhamRequest updateDongSanPhamRequest);

    List<DongSanPhamResponse> listDongSanPhamEntityToDongSanPhamResponse(List<DongSanPham> dongSanPhamList);
}
