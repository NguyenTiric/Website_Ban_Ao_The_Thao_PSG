package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.entity.SanPham;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface ChiTietSanPhamService {

    List<ChiTietSanPhamResponse> listChiTietSanPhamBySanPham(Integer idSP);

    List<ChiTietSanPhamResponse> getAllChiTietSanPham();

    Page<ChiTietSanPhamResponse> pageChiTietSanPhamActive(Integer pageNo, Integer size);

    Page<ChiTietSanPhamResponse> pageChiTietSanPhamInActive(Integer pageNo, Integer size);

    List<ChiTietSanPhamResponse> getAllPending();

    void addCtsp(CreateSanPhamRequest createSanPhamRequest, List<KichThuoc> kichThuocList, MultipartFile[] files) throws IOException, SQLException;

    void updateSoLuongPending(List<Integer> id, List<Integer> soLuong);

    void updateSoLuongActive(List<Integer> id, List<Integer> soLuong);

    void updateTrangThai();

    void insertCtsp(List<KichThuoc> kichThuocList,Integer idSP);

    ChiTietSanPhamResponse getOneCtsp(Integer id);
//
//    Page<ChiTietSanPhamResponse> searchNameOrMaActiveCtsp(String searchName, Integer pageNo, Integer size);
//
//    Page<ChiTietSanPhamResponse> searchNameOrMaInActiveCtsp(String searchName, Integer pageNo, Integer size);

//    void deleteChiTietSanPham(Integer id);

    void deleteChiTietSanPham(Integer id);

    void revertChiTietSanPham(Integer id, LocalDate now);

    Page<SanPhamResponse> pageSanPhamActive(Integer pageNo, Integer size);

    Page<SanPhamResponse> pageSanPhamInActive(Integer pageNo, Integer size);

    List<SanPhamResponse> getAllSanPhamActive();

    SanPhamResponse addSp(CreateSanPhamRequest createSanPhamRequest);

    void updateSp(UpdateSanPhamRequest updateSanPhamRequest, MultipartFile[] files) throws IOException, SQLException;

    SanPhamResponse getOneSp(Integer id);

    List<SanPhamResponse> getAllSP();
    Page<SanPhamResponse> searchNameOrMaActiveSp(String searchName, Integer pageNo, Integer size);

    Page<SanPhamResponse> searchNameOrMaInActiveSp(String searchName, Integer pageNo, Integer size);

    void deleteSanPham(Integer id, LocalDate now);

    void revertSanPham(Integer id, LocalDate now);

    void deleteChiTietSanPhamUpdate(Integer id, LocalDate now);


}
