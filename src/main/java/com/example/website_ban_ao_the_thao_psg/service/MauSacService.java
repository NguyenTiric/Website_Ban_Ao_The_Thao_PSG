package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface MauSacService {

    Page<MauSacResponse> pageMauSacActive(Integer pageNo, Integer size);

    Page<MauSacResponse> pageMauSacInActive(Integer pageNo, Integer size);

    MauSacResponse add(CreateMauSacRequest createMauSacRequest);

    MauSacResponse update(UpdateMauSacRequest updateMauSacRequest);

    MauSacResponse getOne(Integer id);

    MauSacResponse delete(UpdateMauSacRequest updateMauSacRequest, Integer id);

    Page<MauSacResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size);

    void deleteMauSac(Integer id, LocalDate now);

    void revertMauSac(Integer id,LocalDate now);


}
