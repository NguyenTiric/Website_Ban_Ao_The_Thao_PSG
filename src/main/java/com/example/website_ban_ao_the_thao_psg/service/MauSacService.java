package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuongHieuResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MauSacService {

    Page<MauSacResponse> pageMauSacActive(Integer pageNo, Integer size);
    List<MauSacResponse> getAll();
    Page<MauSacResponse> pageMauSacInActive(Integer pageNo, Integer size);

    MauSacResponse add(CreateMauSacRequest createMauSacRequest);

    MauSacResponse update(UpdateMauSacRequest updateMauSacRequest);

    MauSacResponse getOne(Integer id);

    Page<MauSacResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<MauSacResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteMauSac(Integer id, LocalDate now);

    void revertMauSac(Integer id,LocalDate now);


}
