package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateQuyDinhRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateQuyDinhRequest;
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
    public QuyDinhResponse add(CreateQuyDinhRequest createQuyDinhRequest) {
        return null;
    }

    @Override
    public QuyDinhResponse update(UpdateQuyDinhRequest updateQuyDinhRequest) {
        return null;
    }

    @Override
    public QuyDinhResponse getOne(Integer id) {
        return null;
    }

    @Override
    public QuyDinhResponse delete(UpdateQuyDinhRequest updateQuyDinhRequest, Integer id) {
        return null;
    }
}
