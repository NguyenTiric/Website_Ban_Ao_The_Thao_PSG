package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.LichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.GiaoDichResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonChiTietResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface HoaDonService {
    List<HoaDonResponse> getAllHoaDonCho();

    List<LichSuHoaDonResponse> getAllLichSuHoaDon(Integer idHd);
    List<GiaoDichResponse> getAllGiaoDich(Integer idHd);
    List<HoaDonChiTietResponse> getAllHoaDonChiTiet(Integer idHd);

    HoaDonResponse addHoaDon();

    void updateTrangThaiHoaDon(ApplicationConstant.TrangThaiHoaDon trangThaiHoaDon,Integer idHd,String moTa);

    HoaDonResponse getDetailHoaDon(Integer id);

    Page<HoaDonResponse> searchByAllRange(String name, Integer pageNo, Integer size);

    Page<HoaDonResponse> searchByDate(String beginDate, String endDate, Integer pageNo, Integer size) throws ParseException;
    Page<HoaDonResponse>pageHoaDon(Integer page,Integer size);
    Page<HoaDonResponse>pageSearchHoaDon(Integer page,Integer size,String tim);
    Page<HoaDonResponse>pageSearchHoaDonBetweenDates(Integer page, Integer size, LocalDate batdau,LocalDate ketThuc);
}
