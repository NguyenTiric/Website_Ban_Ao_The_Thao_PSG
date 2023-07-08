package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CoAoResponse;
import com.example.website_ban_ao_the_thao_psg.service.CoAoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CoAoServiceImpl implements CoAoService {
    @Override
    public Page<CoAoResponse> pageCoAoResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public CoAoResponse add(CreateCoAoRequest createCoAoRequest) {
        return null;
    }

    @Override
    public CoAoResponse update(UpdateCoAoRequest updateCoAoRequest) {
        return null;
    }

    @Override
    public CoAoResponse getOne(Integer id) {
        return null;
    }

    @Override
    public CoAoResponse delete(UpdateCoAoRequest updateCoAoRequest, Integer id) {
        return null;
    }
}
