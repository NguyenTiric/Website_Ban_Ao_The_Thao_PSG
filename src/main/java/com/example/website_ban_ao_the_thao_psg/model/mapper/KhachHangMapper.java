package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {

    TaiKhoan taiKhoanResponseToTaiKhoanEntity(KhachHangResponse khachHangResponse);

    KhachHangResponse khachHangEntityToTaiKhoanResponse(TaiKhoan taiKhoan);
    TaiKhoan createKhachHangRequestToTaiKhoanEntity(CreateKhachHangRequest createKhachHangRequest);

    TaiKhoan updateKhachHangRequestToTaiKhoanEntity(UpdateKhachHangRequest updateKhachHangRequest);
}
