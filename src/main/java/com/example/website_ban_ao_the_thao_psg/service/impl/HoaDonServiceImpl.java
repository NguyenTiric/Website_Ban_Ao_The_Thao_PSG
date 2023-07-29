package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.model.mapper.HoaDonMapper;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.repository.HoaDonRepository;
import com.example.website_ban_ao_the_thao_psg.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class HoaDonServiceImpl implements HoaDonService {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonMapper hoaDonMapper;


    @Override
    public List<HoaDonResponse> getAllHoaDonCho() {
        List<HoaDon> hoaDonList = hoaDonRepository.getHoaDonByTrangThai(ApplicationConstant.TrangThaiHoaDon.PENDING);
        return hoaDonMapper.listHoaDonEntityToHoaDonResponse(hoaDonList);
    }


    @Override
    public HoaDonResponse getDetailHoaDon(Integer id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        return hoaDonMapper.hoaDonEntityToHoaDonResponse(hoaDon);
    }

    @Override
    public Page<HoaDonResponse> searchByAllRange(String name, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<HoaDon> pageHoaDonSearch = hoaDonRepository.listSearch(name, pageable);
        return pageHoaDonSearch.map(hoaDonMapper::hoaDonEntityToHoaDonResponse);
    }

    @Override
    public Page<HoaDonResponse> searchByDate(String beginDate, String endDate,Integer pageNo, Integer size) throws ParseException {
        Pageable pageable = PageRequest.of(pageNo, size);
        Date begin;
        Date end;
        begin = format.parse(beginDate);
        end = format.parse(endDate);
        Page<HoaDon> pageHoaDonByDate = hoaDonRepository.listSearchByDate(begin, end, pageable);
        return pageHoaDonByDate.map(hoaDonMapper::hoaDonEntityToHoaDonResponse);
    }
}
