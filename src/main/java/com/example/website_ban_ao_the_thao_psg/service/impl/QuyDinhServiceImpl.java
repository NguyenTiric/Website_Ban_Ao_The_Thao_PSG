package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.QuyDinhResponse;
import com.example.website_ban_ao_the_thao_psg.service.QuyDinhService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class QuyDinhServiceImpl implements QuyDinhService {
    @Override
    public Page<QuyDinhResponse> pageQuyDinhResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public QuyDinhResponse add(CreateSanPhamRequest createSanPhamRequest) {
        return null;
    }

    @Override
    public QuyDinhResponse update(UpdateSanPhamRequest updateSanPhamRequest) {
        return null;
    }

    @Override
    public QuyDinhResponse getOne(Integer id) {
        return null;
    }

    @Override
    public QuyDinhResponse delete(UpdateSanPhamRequest updateSanPhamRequest, Integer id) {
        return null;
    }
}
