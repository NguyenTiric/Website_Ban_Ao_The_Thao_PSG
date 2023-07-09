package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CauThuResponse;
import com.example.website_ban_ao_the_thao_psg.service.CauThuService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CauThuServiceImpl implements CauThuService {

    @Override
    public Page<CauThuResponse> pageCauThuResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public CauThuResponse add(CreateCauThuRequest createCauThuRequest) {
        return null;
    }

    @Override
    public CauThuResponse update(UpdateCauThuRequest updateCauThuRequest) {
        return null;
    }

    @Override
    public CauThuResponse getOne(Integer id) {
        return null;
    }

    @Override
    public CauThuResponse delete(UpdateCauThuRequest updateCauThuRequest, Integer id) {
        return null;
    }
}
