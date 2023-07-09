package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CauThuResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface CauThuService {
    Page<CauThuResponse> pageCauThuActive(Integer pageNo, Integer size);

    Page<CauThuResponse> pageCauThuInActive(Integer pageNo, Integer size);

    CauThuResponse add(CreateCauThuRequest createCauThuRequest);

    CauThuResponse update(UpdateCauThuRequest updateCauThuRequest);

    CauThuResponse getOne(Integer id);

    Page<CauThuResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size);

    void deleteCauThu(Integer id, LocalDate now);

    void revertCauThu(Integer id,LocalDate now);
}
