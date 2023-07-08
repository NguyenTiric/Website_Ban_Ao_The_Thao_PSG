package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.SanPham;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SanPhamMapper {

    SanPham sanPhamResponseToSanPhamEntity(SanPhamResponse sanPhamResponse);

    SanPhamResponse sanPhamEntityToSanPhamResponse(SanPham sanPham);

    SanPham createSanPhamRequestToSanPhamEntity(CreateSanPhamRequest createSanPhamRequest);

    SanPham updateSanPhamRequestToSanPhamEntity(UpdateSanPhamRequest updateSanPhamRequest);

    List<SanPhamResponse> listSanPhamEntityToSanPhamResponse(List<SanPham> sanPhamList);
}
