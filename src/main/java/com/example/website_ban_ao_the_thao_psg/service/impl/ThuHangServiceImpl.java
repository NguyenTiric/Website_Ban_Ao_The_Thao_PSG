package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ThuHangServiceImpl implements ThuHangService {
    @Override
    public Page<ThuHangResponse> pageThuHangResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public ThuHangResponse add(CreateThuHangRequest createThuHangRequest) {
        return null;
    }

    @Override
    public ThuHangResponse update(UpdateThuHangRequest updateThuHangRequest) {
        return null;
    }

    @Override
    public ThuHangResponse getOne(Integer id) {
        return null;
    }

    @Override
    public ThuHangResponse delete(UpdateThuHangRequest updateThuHangRequest, Integer id) {
        return null;
    }
}
