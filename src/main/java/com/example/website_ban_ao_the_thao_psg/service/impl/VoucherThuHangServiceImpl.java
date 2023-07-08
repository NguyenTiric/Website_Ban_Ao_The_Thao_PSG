package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.VoucherThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.VoucherThuHangService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class VoucherThuHangServiceImpl implements VoucherThuHangService {
    @Override
    public Page<VoucherThuHangResponse> pageVoucherThuHangResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public VoucherThuHangResponse add(CreateVoucherThuHangRequest createVoucherThuHangRequest) {
        return null;
    }

    @Override
    public VoucherThuHangResponse update(UpdateVoucherThuHangRequest updateVoucherThuHangRequest) {
        return null;
    }

    @Override
    public VoucherThuHangResponse getOne(Integer id) {
        return null;
    }

    @Override
    public VoucherThuHangResponse delete(UpdateVoucherThuHangRequest updateVoucherThuHangRequest, Integer id) {
        return null;
    }
}
