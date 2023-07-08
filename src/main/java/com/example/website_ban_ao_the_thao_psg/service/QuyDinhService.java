package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.QuyDinhResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface QuyDinhService {
    Page<QuyDinhResponse> pageQuyDinhResponse(Integer pageNo, Integer size);

    QuyDinhResponse add(CreateSanPhamRequest createSanPhamRequest);

    QuyDinhResponse update(UpdateSanPhamRequest updateSanPhamRequest);

    QuyDinhResponse getOne(Integer id);

    QuyDinhResponse delete(UpdateSanPhamRequest updateSanPhamRequest, Integer id);
}
