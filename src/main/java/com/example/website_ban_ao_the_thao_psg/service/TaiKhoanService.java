package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface TaiKhoanService {
    Page<TaiKhoanResponse>pageTaiKhoanActive(Integer pageNo,Integer size);
    Page<TaiKhoanResponse>pageTaiKhoanInActive(Integer pageNo,Integer size);

    TaiKhoanResponse add(CreateTaiKhoanRequest createTaiKhoanRequest);
    TaiKhoanResponse update(UpdateTaiKhoanRequest updateTaiKhoanRequest);

    TaiKhoanResponse getOne(Integer id);

    void delete(Integer id, LocalDate now);

    void revertTaiKhoan(Integer id,LocalDate now);

}
