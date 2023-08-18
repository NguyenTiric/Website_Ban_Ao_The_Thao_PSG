package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.GiaoDichResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonChiTietResponse;
import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateHoaDonRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.LichSuHoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ViVoucherResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface HoaDonService {
    List<HoaDonResponse> getAllHoaDonCho();

    Integer idhoaDonIndex();

    List<LichSuHoaDonResponse> getAllLichSuHoaDon(Integer idHd);

    List<GiaoDichResponse> getAllGiaoDich(Integer idHd);

    List<HoaDonChiTietResponse> getAllHoaDonChiTiet(Integer idHd);

    void updateHoaDonWithKhachHang(Integer hoaDonId, Integer customerId);

    HoaDonChiTietResponse getOneHdct(Integer id);

    HoaDonResponse addHoaDon();

    void updateTrangThaiHoaDon(ApplicationConstant.TrangThaiHoaDon trangThaiHoaDon, Integer idHd, String moTa);

    void thanhToanHoaDon(UpdateHoaDonRequest updateHoaDonRequest, ApplicationConstant.TrangThaiHoaDon trangThaiHoaDon, ApplicationConstant.HinhThucThanhToan hinhThucThanhToan);

    void addHoaDonChiTiet(Integer idCtsp, Integer idHd);

    void updateHoaDonChiTietQuantity(Integer idHdct, Integer newQuantity);

    void deleteById(Integer id);

    HoaDonResponse getDetailHoaDon(Integer id);

    List<ViVoucherResponse> getAllViVoucher(KhachHang khachHang);

    Page<HoaDonResponse> searchByAllRange(String name, Integer pageNo, Integer size);

    Page<HoaDonResponse> searchByDate(String beginDate, String endDate, Integer pageNo, Integer size) throws ParseException;

    Page<HoaDonResponse> pageComboboxTrangThaiHoaDon(Integer page, Integer size, ApplicationConstant.TrangThaiHoaDon trangThai);

    Page<HoaDonResponse> pageHoaDon(Integer page, Integer size);

    Page<HoaDonResponse> pageSearchHoaDon(Integer page, Integer size, String tim);

    Page<HoaDonResponse> pageSearchHoaDonBetweenDates(Integer page, Integer size, LocalDate batdau, LocalDate ketThuc);
}
