package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NhaSanXuatResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.NuocSanXuatResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface NhaSanXuatService {
    Page<NhaSanXuatResponse> pageNhaSanXuatResponse(Integer pageNo, Integer size);

    NhaSanXuatResponse add(CreateNhaSanXuatRequest createNhaSanXuatRequest);

    NhaSanXuatResponse update(UpdateNhaSanXuatRequest updateNhaSanXuatRequest);

    NhaSanXuatResponse getOne(Integer id);

    NhaSanXuatResponse delete(UpdateNhaSanXuatRequest updateNhaSanXuatRequest, Integer id);
}
