package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ChiTietSanPhamService {

    Page<ChiTietSanPhamResponse> pageChiTietSanPhamActive(Integer pageNo, Integer size);

    Page<ChiTietSanPhamResponse> pageChiTietSanPhamInActive(Integer pageNo, Integer size);
    List<ChiTietSanPhamResponse> getAllPending();

    void addCtsp(CreateChiTietSanPhamRequest createChiTietSanPhamRequest,CreateSanPhamRequest createSanPhamRequest,List<KichThuoc> kichThuocList);

    ChiTietSanPhamResponse updateCtsp(UpdateChiTietSanPhamRequest updateChiTietSanPhamRequest);

    ChiTietSanPhamResponse getOneCtsp(Integer id);

    Page<ChiTietSanPhamResponse> searchNameOrMaActiveCtsp(String searchName, Integer pageNo, Integer size);

    Page<ChiTietSanPhamResponse> searchNameOrMaInActiveCtsp(String searchName, Integer pageNo, Integer size);

    void deleteChiTietSanPham(Integer id, LocalDate now);
    void deletePending(Integer id);
    void revertChiTietSanPham(Integer id,LocalDate now);

    Page<SanPhamResponse> pageSanPhamActive(Integer pageNo, Integer size);

    Page<SanPhamResponse> pageSanPhamInActive(Integer pageNo, Integer size);

    SanPhamResponse addSp(CreateSanPhamRequest createSanPhamRequest);

    SanPhamResponse updateSp(UpdateSanPhamRequest updateSanPhamRequest);

    SanPhamResponse getOneSp(Integer id);

    Page<SanPhamResponse> searchNameOrMaActiveSp(String searchName, Integer pageNo, Integer size);

    Page<SanPhamResponse> searchNameOrMaInActiveSp(String searchName, Integer pageNo, Integer size);

    void deleteSanPham(Integer id, LocalDate now);

    void revertSanPham(Integer id,LocalDate now);

}
