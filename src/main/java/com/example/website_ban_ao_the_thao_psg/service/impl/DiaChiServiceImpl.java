package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DiaChiResponse;
import com.example.website_ban_ao_the_thao_psg.service.DiaChiService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class DiaChiServiceImpl implements DiaChiService {
    @Override
    public Page<DiaChiResponse> pageDiaChiResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public DiaChiResponse add(CreateDiaChiRequest createDiaChiRequest) {
        return null;
    }

    @Override
    public DiaChiResponse update(UpdateDiaChiRequest updateDiaChiRequest) {
        return null;
    }

    @Override
    public DiaChiResponse getOne(Integer id) {
        return null;
    }

    @Override
    public DiaChiResponse delete(UpdateDiaChiRequest updateDiaChiRequest, Integer id) {
        return null;
    }
}
