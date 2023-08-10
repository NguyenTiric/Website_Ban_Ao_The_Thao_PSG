package com.example.website_ban_ao_the_thao_psg.service;


import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVoucherThuHangRequest;

import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVoucherThuHangRequest;

import com.example.website_ban_ao_the_thao_psg.model.response.VoucherThuHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDateTime;
import java.util.List;

public interface VoucherThuHangService {

    Page<VoucherThuHangResponse> pageVouCherThuHangActive(Integer pageNo, Integer size);

    Page<VoucherThuHangResponse> pageVouCherThuHangInActive(Integer pageNo, Integer size);

//    Page<VoucherThuHangResponse> pageVouCherThuHangPending(Integer pageNo, Integer size);

    VoucherThuHangResponse add(CreateVoucherThuHangRequest createVouCherThuHangRequest);

    VoucherThuHangResponse update(UpdateVoucherThuHangRequest updateVouCherThuHangRequest);

    VoucherThuHangResponse getOne(Integer id);
    Page<VoucherThuHangResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<VoucherThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteVoucherThuHang(Integer id, LocalDateTime now);

    void revertVoucherThuHang(Integer id,LocalDateTime now);





}
