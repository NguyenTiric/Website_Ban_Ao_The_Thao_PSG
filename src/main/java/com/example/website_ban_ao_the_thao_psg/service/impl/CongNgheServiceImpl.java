package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CongNgheResponse;
import com.example.website_ban_ao_the_thao_psg.service.CongNgheService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CongNgheServiceImpl implements CongNgheService {
    @Override
    public Page<CongNgheResponse> pageCongNgheResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public CongNgheResponse add(CreateCongNgheRequest createCongNgheRequest) {
        return null;
    }

    @Override
    public CongNgheResponse update(UpdateCongNgheRequest updateCongNgheRequest) {
        return null;
    }

    @Override
    public CongNgheResponse getOne(Integer id) {
        return null;
    }

    @Override
    public CongNgheResponse delete(UpdateCongNgheRequest updateCongNgheRequest, Integer id) {
        return null;
    }
}
