package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KichThuocResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface KichThuocService {
    Page<KichThuocResponse> pageKichThuocResponse(Integer pageNo, Integer size);

    KichThuocResponse add(CreateKichThuocRequest createKichThuocRequest);

    KichThuocResponse update(UpdateKichThuocRequest updateKichThuocRequest);

    KichThuocResponse getOne(Integer id);

    KichThuocResponse delete(UpdateKichThuocRequest updateKichThuocRequest, Integer id);
}
