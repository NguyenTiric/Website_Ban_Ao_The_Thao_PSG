package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.LyDoTraHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLyDoTraHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLyDoTraHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LyDoTraHangResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LyDoTraHangMapper {

    LyDoTraHang lyDoTraHangResponseToLyDoTraHangEntity(LyDoTraHangResponse lyDoTraHangResponse);

    LyDoTraHangResponse lyDoTraHangEntityToLyDoTraHangResponse(LyDoTraHang lyDoTraHang);

    LyDoTraHang createLyDoTraHangRequestToLyDoTraHangEntity(CreateLyDoTraHangRequest createLyDoTraHangRequest);

    LyDoTraHang updateLyDoTraHangRequestToLyDoTraHangEntity(UpdateLyDoTraHangRequest updateLyDoTraHangRequest);

    List<LyDoTraHangResponse> listLyDoTraHangEntityToLyDoTraHangResponses(List<LyDoTraHang> lyDoTraHangList);

}
