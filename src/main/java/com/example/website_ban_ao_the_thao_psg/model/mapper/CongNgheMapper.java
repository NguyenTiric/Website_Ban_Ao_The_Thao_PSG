package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.CongNghe;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CongNgheResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CongNgheMapper {

    CongNghe congNgheResponseToCongNgheEntity(CongNgheResponse congNgheResponse);

    CongNgheResponse congNgheEntityToCongNgheResponse(CongNghe congNghe);

    CongNghe createCongNgheRequestToCongNgheEntity(CreateCongNgheRequest createCongNgheRequest);

    CongNghe updateCongNgheRequestToCongNgheEntity(UpdateCongNgheRequest updateCongNgheRequest);

    List<CongNgheResponse> listCongNgheEntityToCongNgheResponses(List<CongNghe> congNgheList);
}
