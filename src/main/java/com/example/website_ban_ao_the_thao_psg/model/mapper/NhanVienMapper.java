package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.NhanVienResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {
    TaiKhoan taiKhoanResponseToTaiKhoanEntity(NhanVienResponse nhanVienResponse);

    KhachHangResponse nhanVienEntityToTaiKhoanResponse(TaiKhoan taiKhoan);

    TaiKhoan createNhanVienRequestToTaiKhoanEntity(CreateNhanVienRequest createTaiKhoanRequest);

    TaiKhoan updateNhanVienRequestToTaiKhoanEntity(UpdateNhanVienRequest updateNhanVienRequest);



    List<KhachHangResponse>listTaiKhoanEntityToTaiKhoanResponse(List<TaiKhoan> taiKhoanList);

}
