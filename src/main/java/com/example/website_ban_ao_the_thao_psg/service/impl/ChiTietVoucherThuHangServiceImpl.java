package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietVoucherThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietVoucherThuHangService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ChiTietVoucherThuHangServiceImpl implements ChiTietVoucherThuHangService {
    @Override
    public Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public ChiTietVoucherThuHangResponse add(CreateChiTietVoucherThuHangRequest createChiTietVoucherThuHangRequest) {
        return null;
    }

    @Override
    public ChiTietVoucherThuHangResponse update(UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest) {
        return null;
    }

    @Override
    public ChiTietVoucherThuHangResponse getOne(Integer id) {
        return null;
    }

    @Override
    public ChiTietVoucherThuHangResponse delete(UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest, Integer id) {
        return null;
    }
}
