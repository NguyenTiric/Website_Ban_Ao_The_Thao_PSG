package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.HoaDonChiTiet;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateHoaDonChiTietRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateHoaDonChiTietRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonChiTietResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HoaDonChiTietMapper {
    HoaDonChiTiet hoaDonChiTietResponseToHoaDonChiTietEntity(HoaDonChiTietResponse hoaDonChiTietResponse);

    HoaDonChiTietResponse hoaDonChiTietEntityToHoaDonChiTietResponse(HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet createHoaDonChiTietRequestToHoaDonChiTietEntity(CreateHoaDonChiTietRequest createHoaDonChiTietRequest);

    HoaDonChiTiet updateHoaDonChiTietRequestToHoaDonChiTietEntity(UpdateHoaDonChiTietRequest updateHoaDonChiTietRequest);

    List<HoaDonChiTietResponse> listHoaDonChiTietEntityToHoaDonChiTietResponse(List<HoaDonChiTiet> listHoaDonChiTiet);
}
