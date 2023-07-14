package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.AnhSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ChiTietSanPhamService {

    Page<ChiTietSanPhamResponse> pageChiTietSanPhamResponse(Integer pageNo, Integer size);

    ChiTietSanPhamResponse addCtsp(CreateChiTietSanPhamRequest createChiTietSanPhamRequest);

    ChiTietSanPhamResponse updateCtsp(UpdateChiTietSanPhamRequest updateChiTietSanPhamRequest);

    ChiTietSanPhamResponse getOneCtsp(Integer id);

    ChiTietSanPhamResponse deleteCtsp(UpdateChiTietSanPhamRequest updateChiTietSanPhamRequest, Integer id);

    Page<SanPhamResponse> pageSanPhamResponse(Integer pageNo, Integer size);

    SanPhamResponse addSp(CreateSanPhamRequest createSanPhamRequest);

    SanPhamResponse updateSp(UpdateSanPhamRequest updateSanPhamRequest);

    SanPhamResponse getOneSP(Integer id);

    SanPhamResponse deleteSp(UpdateSanPhamRequest updateSanPhamRequest, Integer id);

    AnhSanPhamResponse addAsp(CreateAnhSanPhamRequest createAnhSanPhamRequest);

    AnhSanPhamResponse updateAsp(UpdateAnhSanPhamRequest updateAnhSanPhamRequest);

    AnhSanPhamResponse getOneAsp(Integer id);

    AnhSanPhamResponse deleteSp(UpdateAnhSanPhamRequest updateAnhSanPhamRequest, Integer id);

}
