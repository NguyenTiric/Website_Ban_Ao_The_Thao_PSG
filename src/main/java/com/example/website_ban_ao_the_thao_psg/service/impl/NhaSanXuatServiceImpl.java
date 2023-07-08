package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NhaSanXuatResponse;
import com.example.website_ban_ao_the_thao_psg.service.NhaSanXuatService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class NhaSanXuatServiceImpl implements NhaSanXuatService {
    @Override
    public Page<NhaSanXuatResponse> pageNhaSanXuatResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public NhaSanXuatResponse add(CreateNhaSanXuatRequest createNhaSanXuatRequest) {
        return null;
    }

    @Override
    public NhaSanXuatResponse update(UpdateNhaSanXuatRequest updateNhaSanXuatRequest) {
        return null;
    }

    @Override
    public NhaSanXuatResponse getOne(Integer id) {
        return null;
    }

    @Override
    public NhaSanXuatResponse delete(UpdateNhaSanXuatRequest updateNhaSanXuatRequest, Integer id) {
        return null;
    }
}
