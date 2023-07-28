package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.entity.AnhSanPham;
import com.example.website_ban_ao_the_thao_psg.model.mapper.AnhSanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ChatLieuResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.repository.AnhSanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.repository.SanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.service.AnhSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class AnhSanPhamServiceImpl implements AnhSanPhamService {

    @Autowired
    AnhSanPhamRepository anhSanPhamRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    AnhSanPhamMapper anhSanPhamMapper;

    @Override
    public List<AnhSanPhamResponse> anhSanPhamResponseList(Integer idSP) {
        List<AnhSanPham> anhSanPhamList = anhSanPhamRepository.getAnhSanPhamBySanPham(sanPhamRepository.findById(idSP).get());
        return anhSanPhamMapper.listAnhSanPhamEntityToAnhSanPhamResponse(anhSanPhamList);
    }

    @Override
    public AnhSanPhamResponse getOne(Integer id) {
        return anhSanPhamMapper.anhSanPhamEntityToAnhSanPhamResponse(anhSanPhamRepository.findById(id).get());
    }

    @Override
    public void deleteAnhSanPham(Integer id, LocalDate now) {
        anhSanPhamRepository.delete(id,LocalDate.now());
    }
}
