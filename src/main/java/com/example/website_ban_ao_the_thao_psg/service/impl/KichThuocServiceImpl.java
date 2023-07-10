package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KichThuocResponse;
import com.example.website_ban_ao_the_thao_psg.service.KichThuocService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class KichThuocServiceImpl implements KichThuocService {
    @Override
    public Page<KichThuocResponse> pageKichThuocResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public KichThuocResponse add(CreateKichThuocRequest createKichThuocRequest) {
        return null;
    }

    @Override
    public KichThuocResponse update(UpdateKichThuocRequest updateKichThuocRequest) {
        return null;
    }

    @Override
    public KichThuocResponse getOne(Integer id) {
        return null;
    }

    @Override
    public KichThuocResponse delete(UpdateKichThuocRequest updateKichThuocRequest, Integer id) {
        return null;
    }
}
