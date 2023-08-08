package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.LichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;

import java.util.List;

public interface LichSuHoaDonMapper {
    LichSuHoaDon lichSuHoaDonResponseToLichSuHoaDonEntity(LichSuHoaDonResponse lichSuHoaDonResponse);

    LichSuHoaDonResponse lichSuHoaDonEntityToLichSuHoaDonResponse(LichSuHoaDon lichSuHoaDon);

    LichSuHoaDon createLichSuHoaDonRequestToLichSuHoaDonEntity(CreateLichSuHoaDonRequest createLichSuHoaDonRequest);

    LichSuHoaDon updateLichSuHoaDonRequestToLichSuHoaDonEntity(UpdateLichSuHoaDonRequest updateLichSuHoaDonRequest);

    List<LichSuHoaDonResponse> listLichSuHoaDonEntityToLichSuHoaDonResponse(List<LichSuHoaDon> lichSuHoaDonList);
}
