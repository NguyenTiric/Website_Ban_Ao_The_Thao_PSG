package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NuocSanXuatResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface NuocSanXuatService {
    Page<NuocSanXuatResponse> pageNuocSanXuatResponse(Integer pageNo, Integer size);

    NuocSanXuatResponse add(CreateNuocSanXuatRequest createNuocSanXuatRequest);

    NuocSanXuatResponse update(UpdateNuocSanXuatRequest updateNuocSanXuatRequest);

    NuocSanXuatResponse getOne(Integer id);

    NuocSanXuatResponse delete(UpdateNuocSanXuatRequest updateNuocSanXuatRequest, Integer id);
}
