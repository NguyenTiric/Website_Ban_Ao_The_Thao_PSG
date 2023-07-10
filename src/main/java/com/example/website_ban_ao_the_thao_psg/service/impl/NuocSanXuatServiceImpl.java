package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NuocSanXuatResponse;
import com.example.website_ban_ao_the_thao_psg.service.NuocSanXuatService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class NuocSanXuatServiceImpl implements NuocSanXuatService {
    @Override
    public Page<NuocSanXuatResponse> pageNuocSanXuatResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public NuocSanXuatResponse add(CreateNuocSanXuatRequest createNuocSanXuatRequest) {
        return null;
    }

    @Override
    public NuocSanXuatResponse update(UpdateNuocSanXuatRequest updateNuocSanXuatRequest) {
        return null;
    }

    @Override
    public NuocSanXuatResponse getOne(Integer id) {
        return null;
    }

    @Override
    public NuocSanXuatResponse delete(UpdateNuocSanXuatRequest updateNuocSanXuatRequest, Integer id) {
        return null;
    }
}
