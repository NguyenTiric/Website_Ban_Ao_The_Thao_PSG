package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AnhSanPhamService {
    List<AnhSanPhamResponse> anhSanPhamResponseList(Integer idSP);
    AnhSanPhamResponse getOne(Integer id);
    void deleteAnhSanPham(Integer id, LocalDate now);
}
