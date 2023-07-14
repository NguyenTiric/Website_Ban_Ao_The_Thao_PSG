package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LoaiSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface LoaiSanPhamService {
    Page<LoaiSanPhamResponse> pageLoaiSanPhamResponse(Integer pageNo, Integer size);

    LoaiSanPhamResponse add(CreateLoaiSanPhamRequest createLoaiSanPhamRequest);

    LoaiSanPhamResponse update(UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest);

    LoaiSanPhamResponse getOne(Integer id);

    LoaiSanPhamResponse delete(UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest, Integer id);
}
