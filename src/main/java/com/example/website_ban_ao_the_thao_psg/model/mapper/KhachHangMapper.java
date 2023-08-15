package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {

    KhachHang khachHangResponseToTaiKhoanEntity(KhachHangResponse khachHangResponse);

    KhachHangResponse khachHangEntityToTaiKhoanResponse(KhachHang khachHang);
    KhachHang createKhachHangRequestToTaiKhoanEntity(CreateKhachHangRequest createKhachHangRequest);

    KhachHang updateKhachHangRequestToTaiKhoanEntity(UpdateKhachHangRequest updateKhachHangRequest);

    List<KhachHangResponse> listKhachHangEntityToKhachHangResponse(List<KhachHang> khachHangList);

}
