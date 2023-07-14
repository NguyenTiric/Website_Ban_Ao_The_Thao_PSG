package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ThuHangService {
    Page<ThuHangResponse> pageThuHangResponse(Integer pageNo, Integer size);

    ThuHangResponse add(CreateThuHangRequest createThuHangRequest);

    ThuHangResponse update(UpdateThuHangRequest updateThuHangRequest);

    ThuHangResponse getOne(Integer id);

    ThuHangResponse delete(UpdateThuHangRequest updateThuHangRequest, Integer id);
}
