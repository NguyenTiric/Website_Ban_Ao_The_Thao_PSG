package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Override
    public Page<ChiTietSanPhamResponse> pageChiTietSanPhamActive(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public Page<ChiTietSanPhamResponse> pageChiTietSanPhamInActive(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public ChiTietSanPhamResponse addCtsp(CreateChiTietSanPhamRequest createChiTietSanPhamRequest) {
        return null;
    }

    @Override
    public ChiTietSanPhamResponse updateCtsp(UpdateChiTietSanPhamRequest updateChiTietSanPhamRequest) {
        return null;
    }

    @Override
    public ChiTietSanPhamResponse getOneCtsp(Integer id) {
        return null;
    }

    @Override
    public Page<ChiTietSanPhamResponse> searchNameOrMaActiveCtsp(String searchName, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public Page<ChiTietSanPhamResponse> searchNameOrMaInActiveCtsp(String searchName, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public void deleteChiTietSanPham(Integer id, LocalDate now) {

    }

    @Override
    public void revertChiTietSanPham(Integer id, LocalDate now) {

    }

    @Override
    public Page<SanPhamResponse> pageSanPhamActive(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public Page<SanPhamResponse> pageSanPhamInActive(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public SanPhamResponse addSp(CreateSanPhamRequest createSanPhamRequest) {
        return null;
    }

    @Override
    public SanPhamResponse updateSp(UpdateSanPhamRequest updateSanPhamRequest) {
        return null;
    }

    @Override
    public SanPhamResponse getOneSp(Integer id) {
        return null;
    }

    @Override
    public Page<SanPhamResponse> searchNameOrMaActiveSp(String searchName, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public Page<SanPhamResponse> searchNameOrMaInActiveSp(String searchName, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public void deleteSanPham(Integer id, LocalDate now) {

    }

    @Override
    public void revertSanPham(Integer id, LocalDate now) {

    }
}
