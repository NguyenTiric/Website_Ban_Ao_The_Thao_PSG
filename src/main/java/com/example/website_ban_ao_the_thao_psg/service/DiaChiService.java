package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DiaChiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DiaChiService {
    Page<DiaChiResponse> pageDiaChiResponse(Integer pageNo, Integer size);

    DiaChiResponse add(CreateDiaChiRequest createDiaChiRequest);

    DiaChiResponse update(UpdateDiaChiRequest updateDiaChiRequest);

    DiaChiResponse getOne(Integer id);

    DiaChiResponse delete(UpdateDiaChiRequest updateDiaChiRequest, Integer id);
}
