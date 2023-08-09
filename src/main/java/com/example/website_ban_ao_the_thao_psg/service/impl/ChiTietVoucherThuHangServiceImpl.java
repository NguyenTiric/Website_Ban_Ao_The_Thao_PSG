package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietVoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ChiTietVoucherThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietVoucherThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ChiTietVoucherThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietVoucherThuHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ChiTietVoucherThuHangServiceImpl implements ChiTietVoucherThuHangService {

    @Autowired
    private ChiTietVoucherThuHangRepository chiTietVoucherThuHangRepository;

    @Autowired
    private ChiTietVoucherThuHangMapper chiTietVoucherThuHangMapper;

    @Override
    public Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageACTIVE(pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);
    }

    @Override
    public List<ChiTietVoucherThuHangResponse> getAll() {
        List<ChiTietVoucherThuHang> chiTietVoucherThuHangList = chiTietVoucherThuHangRepository.getAll();
        return chiTietVoucherThuHangMapper.listChiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangList);
    }

    @Override
    public Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageINACTIVE(pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);

    }

    @Override
    public ChiTietVoucherThuHangResponse add(CreateChiTietVoucherThuHangRequest createChiTietVoucherThuHangRequest) {
        ChiTietVoucherThuHang chiTietVoucherThuHang = chiTietVoucherThuHangMapper.createChiTietVoucherThuHangRequestToChiTietVouCherThuHangEntity(createChiTietVoucherThuHangRequest);
        chiTietVoucherThuHang.setNgayTao(LocalDate.now());
        chiTietVoucherThuHang.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return chiTietVoucherThuHangMapper.chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangRepository.save(chiTietVoucherThuHang));
    }

    @Override
    public ChiTietVoucherThuHangResponse update(UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest) {
        ChiTietVoucherThuHang chiTietVoucherThuHang = chiTietVoucherThuHangMapper.updateChiTietVoucherThuHangRequestToChiTietVouCherThuHangEntity(updateChiTietVoucherThuHangRequest);
        chiTietVoucherThuHang.setNgayCapNhat(LocalDate.now());
        chiTietVoucherThuHang.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return chiTietVoucherThuHangMapper.chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangRepository.save(chiTietVoucherThuHang));
    }

    @Override
    public ChiTietVoucherThuHangResponse getOne(Integer id) {
        Optional<ChiTietVoucherThuHang> chiTietVoucherThuHangOptional = chiTietVoucherThuHangRepository.findById(id);
        return chiTietVoucherThuHangMapper.chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangOptional.get());
    }

    @Override
    public Page<ChiTietVoucherThuHangResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageSearchActive(searchName, pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);
    }

    @Override
    public Page<ChiTietVoucherThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageSearchIvActive(searchName, pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);
    }


    @Override
    public void deleteChiTietVoucherThuHang(Integer id, LocalDate now) {
        chiTietVoucherThuHangRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertChiTietVoucherThuHang(Integer id, LocalDate now) {
        chiTietVoucherThuHangRepository.revert(id, LocalDate.now());
    }
}
