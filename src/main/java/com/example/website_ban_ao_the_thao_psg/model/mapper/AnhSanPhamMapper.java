package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.AnhSanPham;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnhSanPhamMapper {

    AnhSanPham anhSanPhamResponeToAnhSanPhamEntity(AnhSanPhamResponse anhSanPhamResponse);

    AnhSanPhamResponse anhSanPhamEntityToAnhSanPhamResponse(AnhSanPham anhSanPham);

    AnhSanPham createAnhSanPhamRequestToAnhSanPhamEntity(CreateAnhSanPhamRequest createAnhSanPhamRequest);

    AnhSanPham updateAnhSanPhamRequestToAnhSanPhamEntity(UpdateAnhSanPhamRequest updateAnhSanPhamRequest);

    List<AnhSanPhamResponse> listAnhSanPhamEntityToAnhSanPhamResponse(List<AnhSanPham> anhSanPhamList);
}
