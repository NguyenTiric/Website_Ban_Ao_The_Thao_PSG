package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DongSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DongSanPhamService {
    Page<DongSanPhamResponse> pageDongSanPhamResponse(Integer pageNo, Integer size);

    DongSanPhamResponse add(CreateDongSanPhamRequest createDongSanPhamRequest);

    DongSanPhamResponse update(UpdateDongSanPhamRequest updateDongSanPhamRequest);

    DongSanPhamResponse getOne(Integer id);

    DongSanPhamResponse delete(UpdateDongSanPhamRequest updateDongSanPhamRequest, Integer id);
}
