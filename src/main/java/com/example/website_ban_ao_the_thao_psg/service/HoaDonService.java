package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface HoaDonService {
    List<HoaDonResponse> getAllHoaDonCho();

    HoaDonResponse getDetailHoaDon(Integer id);

    Page<HoaDonResponse> searchByAllRange(String name, Integer pageNo, Integer size);

    Page<HoaDonResponse> searchByDate(String beginDate, String endDate, Integer pageNo, Integer size) throws ParseException;
}
