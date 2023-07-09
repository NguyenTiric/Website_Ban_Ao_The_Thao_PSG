package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KichThuocResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface KichThuocService {
    Page<KichThuocResponse> pageKichThuocActive(Integer pageNo, Integer size);

    Page<KichThuocResponse> pageKichThuocInActive(Integer pageNo, Integer size);

    KichThuocResponse add(CreateKichThuocRequest createKichThuocRequest);

    KichThuocResponse update(UpdateKichThuocRequest updateKichThuocRequest);

    KichThuocResponse getOne(Integer id);

    Page<KichThuocResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size);

    void deleteKichThuoc(Integer id, LocalDate now);

    void revertKichThuoc(Integer id,LocalDate now);
}
