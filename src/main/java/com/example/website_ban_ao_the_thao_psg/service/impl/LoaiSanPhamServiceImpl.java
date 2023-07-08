package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LoaiSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.LoaiSanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    @Override
    public Page<LoaiSanPhamResponse> pageLoaiSanPhamResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public LoaiSanPhamResponse add(CreateLoaiSanPhamRequest createLoaiSanPhamRequest) {
        return null;
    }

    @Override
    public LoaiSanPhamResponse update(UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest) {
        return null;
    }

    @Override
    public LoaiSanPhamResponse getOne(Integer id) {
        return null;
    }

    @Override
    public LoaiSanPhamResponse delete(UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest, Integer id) {
        return null;
    }
}
