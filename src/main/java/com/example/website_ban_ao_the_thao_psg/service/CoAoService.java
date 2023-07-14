package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CoAoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CoAoService {
    Page<CoAoResponse> pageCoAoResponse(Integer pageNo, Integer size);

    CoAoResponse add(CreateCoAoRequest createCoAoRequest);

    CoAoResponse update(UpdateCoAoRequest updateCoAoRequest);

    CoAoResponse getOne(Integer id);

    CoAoResponse delete(UpdateCoAoRequest updateCoAoRequest, Integer id);
}
