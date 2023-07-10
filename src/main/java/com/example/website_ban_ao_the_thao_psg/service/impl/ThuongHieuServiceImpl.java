package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuongHieuMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuongHieuResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ThuongHieuRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    ThuongHieuMapper thuongHieuMapper;
    @Autowired
    ThuongHieuRepository thuongHieuRepository;
    @Override
    public Page<ThuongHieuResponse> pageThuongHieuResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public ThuongHieuResponse add(CreateThuongHieuRequest createThuongHieuRequest) {
        return null;
    }

    @Override
    public ThuongHieuResponse update(UpdateThuongHieuRequest updateThuongHieuRequest) {
        return null;
    }

    @Override
    public ThuongHieuResponse getOne(Integer id) {
        return null;
    }

    @Override
    public ThuongHieuResponse delete(UpdateThuongHieuRequest updateThuongHieuRequest, Integer id) {
        return null;
    }
}
