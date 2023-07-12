package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaiKhoanMapper {
    TaiKhoan taiKhoanResponseToTaiKhoanEntity(TaiKhoanResponse taiKhoanResponse);

    TaiKhoanResponse taiKhoanEntityToTaiKhoanResponse(TaiKhoan taiKhoan);

    TaiKhoan createTaiKhoanRequestToTaiKhoanEntity(CreateTaiKhoanRequest createTaiKhoanRequest);

    TaiKhoan updateTaiKhoanRequestToTaiKhoanEntity(UpdateTaiKhoanRequest updateTaiKhoanRequest);

    List<TaiKhoanResponse>listTaiKhoanEntityToTaiKhoanResponse(List<TaiKhoan> taiKhoanList);

}
