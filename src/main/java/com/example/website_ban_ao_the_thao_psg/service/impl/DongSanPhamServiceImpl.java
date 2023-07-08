package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DongSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.DongSanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class DongSanPhamServiceImpl implements DongSanPhamService {
    @Override
    public Page<DongSanPhamResponse> pageDongSanPhamResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public DongSanPhamResponse add(CreateDongSanPhamRequest createDongSanPhamRequest) {
        return null;
    }

    @Override
    public DongSanPhamResponse update(UpdateDongSanPhamRequest updateDongSanPhamRequest) {
        return null;
    }

    @Override
    public DongSanPhamResponse getOne(Integer id) {
        return null;
    }

    @Override
    public DongSanPhamResponse delete(UpdateDongSanPhamRequest updateDongSanPhamRequest, Integer id) {
        return null;
    }
}
