package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuongHieuResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ThuongHieuService {
    Page<ThuongHieuResponse> pageThuongHieuResponse(Integer pageNo, Integer size);

    ThuongHieuResponse add(CreateThuongHieuRequest createThuongHieuRequest);

    ThuongHieuResponse update(UpdateThuongHieuRequest updateThuongHieuRequest);

    ThuongHieuResponse getOne(Integer id);

    ThuongHieuResponse delete(UpdateThuongHieuRequest updateThuongHieuRequest, Integer id);
}
