package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface NhanVienService {
    Page<TaiKhoanResponse>pageTaiKhoanActive(Integer pageNo,Integer size);
    Page<TaiKhoanResponse>pageTaiKhoanInActive(Integer pageNo,Integer size);

    TaiKhoanResponse add(CreateNhanVienRequest createTaiKhoanRequest);
    TaiKhoanResponse update(UpdateNhanVienRequest updateNhanVienRequest);

    TaiKhoanResponse getOne(Integer id);

    void delete(Integer id, LocalDate now);

    void revertTaiKhoan(Integer id,LocalDate now);

    Page<TaiKhoanResponse>pageSearchACTIVE(String search,Integer pageNo,Integer size);
    Page<TaiKhoanResponse>pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo,Integer size);

}
