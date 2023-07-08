package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Override
    public Page<ChiTietSanPhamResponse> pageChiTietSanPhamResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public ChiTietSanPhamResponse addCtsp(CreateChiTietSanPhamRequest createChiTietSanPhamRequest) {
        return null;
    }

    @Override
    public ChiTietSanPhamResponse updateCtsp(UpdateChiTietSanPhamRequest updateChiTietSanPhamRequest) {
        return null;
    }

    @Override
    public ChiTietSanPhamResponse getOneCtsp(Integer id) {
        return null;
    }

    @Override
    public ChiTietSanPhamResponse deleteCtsp(UpdateChiTietSanPhamRequest updateChiTietSanPhamRequest, Integer id) {
        return null;
    }

    @Override
    public Page<SanPhamResponse> pageSanPhamResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public SanPhamResponse addSp(CreateSanPhamRequest createSanPhamRequest) {
        return null;
    }

    @Override
    public SanPhamResponse updateSp(UpdateSanPhamRequest updateSanPhamRequest) {
        return null;
    }

    @Override
    public SanPhamResponse getOneSP(Integer id) {
        return null;
    }

    @Override
    public SanPhamResponse deleteSp(UpdateSanPhamRequest updateSanPhamRequest, Integer id) {
        return null;
    }

    @Override
    public AnhSanPhamResponse addAsp(CreateAnhSanPhamRequest createAnhSanPhamRequest) {
        return null;
    }

    @Override
    public AnhSanPhamResponse updateAsp(UpdateAnhSanPhamRequest updateAnhSanPhamRequest) {
        return null;
    }

    @Override
    public AnhSanPhamResponse getOneAsp(Integer id) {
        return null;
    }

    @Override
    public AnhSanPhamResponse deleteSp(UpdateAnhSanPhamRequest updateAnhSanPhamRequest, Integer id) {
        return null;
    }
}
