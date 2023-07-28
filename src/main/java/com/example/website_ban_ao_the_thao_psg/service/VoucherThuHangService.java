package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.VoucherThuHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface VoucherThuHangService {
    Page<VoucherThuHangResponse> pageVoucherThuHangResponse(Integer pageNo, Integer size);

    VoucherThuHangResponse add(CreateVoucherThuHangRequest createVoucherThuHangRequest);

    VoucherThuHangResponse update(UpdateVoucherThuHangRequest updateVoucherThuHangRequest);

    VoucherThuHangResponse getOne(Integer id);

    VoucherThuHangResponse delete(UpdateVoucherThuHangRequest updateVoucherThuHangRequest, Integer id);

}
