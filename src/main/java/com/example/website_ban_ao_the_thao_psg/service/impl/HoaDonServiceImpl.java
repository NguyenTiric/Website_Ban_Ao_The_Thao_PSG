package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.GiaoDich;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDonChiTiet;
import com.example.website_ban_ao_the_thao_psg.entity.LichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.model.mapper.GiaoDichMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.HoaDonChiTietMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.HoaDonMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.LichSuHoaDonMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLichSuHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.GiaoDichResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonChiTietResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.repository.GiaoDichRepository;
import com.example.website_ban_ao_the_thao_psg.repository.HoaDonChiTietRepository;
import com.example.website_ban_ao_the_thao_psg.repository.HoaDonRepository;
import com.example.website_ban_ao_the_thao_psg.repository.LichSuHoaDonRepository;
import com.example.website_ban_ao_the_thao_psg.service.HoaDonService;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonChiTietMapper hoaDonChiTietMapper;

    @Autowired
    private GiaoDichMapper giaoDichMapper;

    @Autowired
    private LichSuHoaDonMapper lichSuHoaDonMapper;

    @Autowired
    private LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    private GiaoDichRepository giaoDichRepository;

    @Override
    public List<HoaDonResponse> getAllHoaDonCho() {
        List<HoaDon> hoaDonList = hoaDonRepository.getHoaDonByTrangThai(ApplicationConstant.TrangThaiHoaDon.PENDING);
        return hoaDonMapper.listHoaDonEntityToHoaDonResponse(hoaDonList);
    }

    @Override
    public List<LichSuHoaDonResponse> getAllLichSuHoaDon(Integer idHd) {
        List<LichSuHoaDon> list = lichSuHoaDonRepository.getLichSuHoaDonByHoaDon(hoaDonRepository.findById(idHd).get());
        return lichSuHoaDonMapper.listLichSuHoaDonEntityToLichSuHoaDonResponse(list);
    }

    @Override
    public List<GiaoDichResponse> getAllGiaoDich(Integer idHd) {
        List<GiaoDich> list = giaoDichRepository.getGiaoDichByHoaDon(hoaDonRepository.findById(idHd).get());
        return giaoDichMapper.listGiaoDichEntityToGiaoDichResponse(list);
    }

    @Override
    public List<HoaDonChiTietResponse> getAllHoaDonChiTiet(Integer idHd) {
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.getHoaDonChiTietByHoaDon(hoaDonRepository.findById(idHd).get());
        return hoaDonChiTietMapper.listHoaDonChiTietEntityToHoaDonChiTietResponse(list);
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
        HoaDon hd = hoaDonRepository.save(hoaDon);

        LichSuHoaDon lichSuHoaDon = lichSuHoaDonMapper.createLichSuHoaDonRequestToLichSuHoaDonEntity(new CreateLichSuHoaDonRequest());
        lichSuHoaDon.setHoaDon(hd);
        lichSuHoaDon.setMoTa("Tạo Hóa Đơn Cho Khách");
        lichSuHoaDon.setNgayTao(LocalDateTime.now());
        lichSuHoaDon.setLoaiLichSuHoaDon(ApplicationConstant.LoaiLichSuHoaDon.CREATED);
        lichSuHoaDonRepository.save(lichSuHoaDon);

        return hoaDonMapper.hoaDonEntityToHoaDonResponse(hd);
    }

    @Override
    public void updateTrangThaiHoaDon(ApplicationConstant.TrangThaiHoaDon trangThaiHoaDon, Integer idHd, String moTa) {
        HoaDon hoaDon = hoaDonRepository.findById(idHd).get();
        LichSuHoaDon lichSuHoaDon = lichSuHoaDonMapper.createLichSuHoaDonRequestToLichSuHoaDonEntity(new CreateLichSuHoaDonRequest());
        lichSuHoaDon.setHoaDon(hoaDon);
        lichSuHoaDon.setNgayTao(LocalDateTime.now());
        lichSuHoaDon.setMoTa(moTa);
        lichSuHoaDon.setTaiKhoan(null);

        switch (trangThaiHoaDon) {
            case APPROVED:
                lichSuHoaDon.setLoaiLichSuHoaDon(ApplicationConstant.LoaiLichSuHoaDon.APPROVED);
                break;
            case SHIPPING:
                lichSuHoaDon.setLoaiLichSuHoaDon(ApplicationConstant.LoaiLichSuHoaDon.SHIPPING);
                break;
            case CANCELLED:
                lichSuHoaDon.setLoaiLichSuHoaDon(ApplicationConstant.LoaiLichSuHoaDon.CANCELLED);
                break;
            case CONFIRMED:
                lichSuHoaDon.setLoaiLichSuHoaDon(ApplicationConstant.LoaiLichSuHoaDon.CONFIRMED);
                break;
            case REVERSE:
                lichSuHoaDon.setLoaiLichSuHoaDon(ApplicationConstant.LoaiLichSuHoaDon.REVERSED);
                break;
            default:
                break;
        }
        lichSuHoaDonRepository.save(lichSuHoaDon);

        hoaDonRepository.updateTrangThai(idHd, trangThaiHoaDon, LocalDate.now());
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
}
