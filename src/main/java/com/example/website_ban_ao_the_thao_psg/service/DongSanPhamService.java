package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DongSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.KichThuocResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface DongSanPhamService {
    Page<DongSanPhamResponse> pageDongSanPhamActive(Integer pageNo, Integer size);

    Page<DongSanPhamResponse> pageDongSanPhamInActive(Integer pageNo, Integer size);

    DongSanPhamResponse add(CreateDongSanPhamRequest createDongSanPhamRequest);

    DongSanPhamResponse update(UpdateDongSanPhamRequest updateDongSanPhamRequest);

    DongSanPhamResponse getOne(Integer id);

    Page<DongSanPhamResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<DongSanPhamResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteDongSanPham(Integer id, LocalDate now);

    void revertDongSanPham(Integer id,LocalDate now);
}
