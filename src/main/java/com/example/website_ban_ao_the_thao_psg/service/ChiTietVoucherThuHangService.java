package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.ChiTietVoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietVoucherThuHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ChiTietVoucherThuHangService {
    Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangActive(Integer pageNo, Integer size);

    List<ChiTietVoucherThuHangResponse> getAll();

    Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangInActive(Integer pageNo, Integer size);

    void addChiTietVoucher(CreateThuHangRequest createThuHangRequest, List<VoucherThuHang> thuHangList);

    ChiTietVoucherThuHangResponse add(CreateChiTietVoucherThuHangRequest createChiTietVoucherThuHangRequest);

    ChiTietVoucherThuHangResponse update(UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest);

    List<ChiTietVoucherThuHangResponse> getTheoIdThuHang(Integer id);
    ChiTietVoucherThuHangResponse getOne(Integer id);

    Page<ChiTietVoucherThuHangResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);

    Page<ChiTietVoucherThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteChiTietVoucherThuHang(Integer id, LocalDate now);

    void revertChiTietVoucherThuHang(Integer id, LocalDate now);

    List<ChiTietVoucherThuHang> getByTrangThaiPending();

    List<ChiTietVoucherThuHang> getMaxidThuHang();

    void updateSoLuongVoucherThuHang(List<Integer> id, List<Integer> soLuong);

    void updateSoLuongVoucherThuHangActive(List<Integer> id, List<Integer> soLuong);

    void delete(Integer id);

    void updateListVoucherThuHangInUpdateChiTietVoucherThuHang(List<VoucherThuHang> voucherThuHangList, Integer id);
}
