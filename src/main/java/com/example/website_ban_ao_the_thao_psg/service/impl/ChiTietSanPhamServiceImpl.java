package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.entity.SanPham;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ChiTietSanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.SanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ChiTietSanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.repository.SanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.service.CauThuService;
import com.example.website_ban_ao_the_thao_psg.service.ChatLieuService;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.CoAoService;
import com.example.website_ban_ao_the_thao_psg.service.CongNgheService;
import com.example.website_ban_ao_the_thao_psg.service.DongSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.KichThuocService;
import com.example.website_ban_ao_the_thao_psg.service.LoaiSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.MauSacService;
import com.example.website_ban_ao_the_thao_psg.service.NhaSanXuatService;
import com.example.website_ban_ao_the_thao_psg.service.NuocSanXuatService;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import com.example.website_ban_ao_the_thao_psg.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    ChiTietSanPhamMapper chiTietSanPhamMapper;

    @Autowired
    SanPhamMapper sanPhamMapper;

    @Override
    public Page<ChiTietSanPhamResponse> pageChiTietSanPhamActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamRepository.pageACTIVE(pageable);
        return chiTietSanPhamPage.map(chiTietSanPhamMapper::chiTietSanPhamEntityTochiTietSanPhamResponse);
    }

    @Override
    public Page<ChiTietSanPhamResponse> pageChiTietSanPhamInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamRepository.pageINACTIVE(pageable);
        return chiTietSanPhamPage.map(chiTietSanPhamMapper::chiTietSanPhamEntityTochiTietSanPhamResponse);
    }

    @Override
    public List<ChiTietSanPhamResponse> getAllPending() {
        return chiTietSanPhamMapper.listchiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamRepository.getAllPending());
    }

    @Override
    public void addCtsp(CreateChiTietSanPhamRequest createChiTietSanPhamRequest, CreateSanPhamRequest createSanPhamRequest, List<KichThuoc> kichThuocList) {
        SanPham sanPham = sanPhamMapper.createSanPhamRequestToSanPhamEntity(createSanPhamRequest);
        sanPham.setMa(GenCode.generateSanPhamCode());
        sanPham.setNgayTao(LocalDate.now());
        sanPham.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        for (KichThuoc ktId : kichThuocList) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamMapper.createChiTietSanPhamRequestToChiTietSanPhamEntity(createChiTietSanPhamRequest);
            chiTietSanPham.setSanPham(sanPham);
            chiTietSanPham.setKichThuoc(ktId);
            chiTietSanPham.setSoLuong(0);
            chiTietSanPham.setNgayTao(LocalDate.now());
            chiTietSanPham.setTrangThai(ApplicationConstant.TrangThaiChiTietSanPham.PENDING);
            chiTietSanPhamMapper.chiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamRepository.save(chiTietSanPham));
        }
    }

    @Override
    public ChiTietSanPhamResponse updateCtsp(UpdateChiTietSanPhamRequest updateChiTietSanPhamRequest) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamMapper.updateChiTietSanPhamRequestToChiTietSanPhamEntity(updateChiTietSanPhamRequest);
        chiTietSanPham.setNgayTao(LocalDate.now());
        chiTietSanPham.setTrangThai(ApplicationConstant.TrangThaiChiTietSanPham.ACTIVE);
        return chiTietSanPhamMapper.chiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamRepository.save(chiTietSanPham));
    }

    @Override
    public ChiTietSanPhamResponse getOneCtsp(Integer id) {
        Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findById(id);
        return chiTietSanPhamMapper.chiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamOptional.get());
    }

    @Override
    public Page<ChiTietSanPhamResponse> searchNameOrMaActiveCtsp(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamRepository.pageSearchActive(searchName, pageable);
        return chiTietSanPhamPage.map(chiTietSanPhamMapper::chiTietSanPhamEntityTochiTietSanPhamResponse);
    }

    @Override
    public Page<ChiTietSanPhamResponse> searchNameOrMaInActiveCtsp(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamRepository.pageSearchIvActive(searchName, pageable);
        return chiTietSanPhamPage.map(chiTietSanPhamMapper::chiTietSanPhamEntityTochiTietSanPhamResponse);
    }

    @Override
    public void deleteChiTietSanPham(Integer id, LocalDate now) {
        chiTietSanPhamRepository.delete(id, LocalDate.now());
    }

    @Override
    public void deletePending(Integer id) {
        chiTietSanPhamRepository.deletePending(id);
    }

    @Override
    public void revertChiTietSanPham(Integer id, LocalDate now) {
        chiTietSanPhamRepository.revert(id, LocalDate.now());
    }

    @Override
    public Page<SanPhamResponse> pageSanPhamActive(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public Page<SanPhamResponse> pageSanPhamInActive(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public SanPhamResponse addSp(CreateSanPhamRequest createSanPhamRequest) {
        return null;
    }

    @Override
    public SanPhamResponse updateSp(UpdateSanPhamRequest updateSanPhamRequest) {
        return null;
    }

    @Override
    public SanPhamResponse getOneSp(Integer id) {
        return null;
    }

    @Override
    public Page<SanPhamResponse> searchNameOrMaActiveSp(String searchName, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public Page<SanPhamResponse> searchNameOrMaInActiveSp(String searchName, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public void deleteSanPham(Integer id, LocalDate now) {

    }

    @Override
    public void revertSanPham(Integer id, LocalDate now) {

    }
}
