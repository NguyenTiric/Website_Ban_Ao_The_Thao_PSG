package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.model.mapper.HoaDonMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateHoaDonRequest;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class HoaDonServiceImpl implements HoaDonService {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final int NUM_HOA_DON_TO_CREATE = 10;
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
    public HoaDonResponse addHoaDon() {

        if (hoaDonRepository.getHoaDonByTrangThai(ApplicationConstant.TrangThaiHoaDon.PENDING).size() >= NUM_HOA_DON_TO_CREATE) {
            return null;
        }

        HoaDon hoaDon = hoaDonMapper.createHoaDonRequestToHoaDonEntity(new CreateHoaDonRequest());
        hoaDon.setMa(GenCode.generateHoaDonCode());
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setTrangThai(ApplicationConstant.TrangThaiHoaDon.PENDING);

        // Trả về một đối tượng HoaDonResponse tùy chọn (có thể là hóa đơn cuối cùng tạo ra)
        return hoaDonMapper.hoaDonEntityToHoaDonResponse(hoaDonRepository.save(hoaDon));
    }


    @Override
    public HoaDonResponse getDetailHoaDon(Integer id) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(id);
        return hoaDonMapper.hoaDonEntityToHoaDonResponse(hoaDonOptional.get());
    }

    @Override
    public Page<HoaDonResponse> searchByAllRange(String name, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<HoaDon> pageHoaDonSearch = hoaDonRepository.listSearch(name, pageable);
        return pageHoaDonSearch.map(hoaDonMapper::hoaDonEntityToHoaDonResponse);
    }

    @Override
    public Page<HoaDonResponse> searchByDate(String beginDate, String endDate, Integer pageNo, Integer size) throws ParseException {
        Pageable pageable = PageRequest.of(pageNo, size);
        Date begin;
        Date end;
        begin = format.parse(beginDate);
        end = format.parse(endDate);
        Page<HoaDon> pageHoaDonByDate = hoaDonRepository.listSearchByDate(begin, end, pageable);
        return pageHoaDonByDate.map(hoaDonMapper::hoaDonEntityToHoaDonResponse);
    }

    @Override
    public Page<HoaDonResponse> pageHoaDon(Integer page, Integer size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<HoaDon>hoaDonResponses=hoaDonRepository.pageHoaDon(pageable);
        return hoaDonResponses.map(hoaDonMapper ::hoaDonEntityToHoaDonResponse);
    }

    @Override
    public Page<HoaDonResponse> pageSearchHoaDon(Integer page, Integer size, String tim) {
        Pageable pageable=PageRequest.of(page,size);
        Page<HoaDon>hoaDonResponses=hoaDonRepository.pageSearchHoaDon(pageable,tim);
        return hoaDonResponses.map(hoaDonMapper ::hoaDonEntityToHoaDonResponse);
    }

    @Override
    public Page<HoaDonResponse> pageSearchHoaDonBetweenDates(Integer page, Integer size, LocalDate batdau, LocalDate ketThuc) {
        Pageable pageable=PageRequest.of(page,size);
        Page<HoaDon>hoaDonResponses=hoaDonRepository.pageSearchHoaDonBetweenDates(pageable,batdau,ketThuc);
        return hoaDonResponses.map(hoaDonMapper ::hoaDonEntityToHoaDonResponse);
    }
}
