package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.LichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;

import java.util.List;

public interface LichSuHoaDonMapper {
    LichSuHoaDon hoaDonResponseToHoaDonEntity(LichSuHoaDonResponse lichSuHoaDonResponse);

    LichSuHoaDonResponse hoaDonEntityToHoaDonResponse(LichSuHoaDon lichSuHoaDon);

    LichSuHoaDon createHoaDonRequestToHoaDonEntity(CreateLichSuHoaDonRequest createLichSuHoaDonRequest);

    LichSuHoaDon updateHoaDonRequestToHoaDonEntity(UpdateLichSuHoaDonRequest updateLichSuHoaDonRequest);

    List<LichSuHoaDonResponse> listHoaDonEntityToHoaDonResponse(List<LichSuHoaDon> listHoaDon);
}
