package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietVoucherThuHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ChiTietVoucherThuHangService {
    Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangResponse(Integer pageNo, Integer size);

    ChiTietVoucherThuHangResponse add(CreateChiTietVoucherThuHangRequest createChiTietVoucherThuHangRequest);

    ChiTietVoucherThuHangResponse update(UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest);

    ChiTietVoucherThuHangResponse getOne(Integer id);

    ChiTietVoucherThuHangResponse delete(UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest, Integer id);
}
