package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CoAoResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.CongNgheResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface CoAoService {
    Page<CoAoResponse> pageCoAoActive(Integer pageNo, Integer size);

    Page<CoAoResponse> pageCoAoInActive(Integer pageNo, Integer size);

    CoAoResponse add(CreateCoAoRequest createCoAoRequest);

    CoAoResponse update(UpdateCoAoRequest updateCoAoRequest);

    CoAoResponse getOne(Integer id);

    Page<CoAoResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<CoAoResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteCoAo(Integer id, LocalDate now);

    void revertCoAo(Integer id,LocalDate now);
}
