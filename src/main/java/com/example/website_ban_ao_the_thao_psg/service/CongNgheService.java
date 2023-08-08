package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CongNgheResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.DongSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CongNgheService {
    Page<CongNgheResponse> pageCongNgheActive(Integer pageNo, Integer size);

    List<CongNgheResponse> getAll();

    Page<CongNgheResponse> pageCongNgheInActive(Integer pageNo, Integer size);

    CongNgheResponse add(CreateCongNgheRequest createCongNgheRequest);

    CongNgheResponse update(UpdateCongNgheRequest updateCongNgheRequest);

    CongNgheResponse getOne(Integer id);

    Page<CongNgheResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<CongNgheResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteCongNghe(Integer id, LocalDate now);

    void revertCongNghe(Integer id,LocalDate now);
}
