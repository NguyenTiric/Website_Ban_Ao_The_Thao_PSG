package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaiKhoanMapper {
    TaiKhoan taiKhoanResponseToTaiKhoanEntity(TaiKhoanResponse taiKhoanResponse);

    TaiKhoanResponse taiKhoanEntityToTaiKhoanResponse(TaiKhoan taiKhoan);

    TaiKhoan createTaiKhoanRequestToTaiKhoanEntity(CreateNhanVienRequest createTaiKhoanRequest);

    TaiKhoan updateTaiKhoanRequestToTaiKhoanEntity(UpdateNhanVienRequest updateNhanVienRequest);

    TaiKhoan createTaiKhoanRequestToTaiKhoanEntity(CreateKhachHangRequest createKhachHangRequest);

    TaiKhoan updateTaiKhoanRequestToTaiKhoanEntity(UpdateKhachHangRequest updateKhachHangRequest);

    List<TaiKhoanResponse>listTaiKhoanEntityToTaiKhoanResponse(List<TaiKhoan> taiKhoanList);

}
