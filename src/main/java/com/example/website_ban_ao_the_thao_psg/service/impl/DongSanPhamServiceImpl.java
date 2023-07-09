package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.DongSanPham;
import com.example.website_ban_ao_the_thao_psg.model.mapper.DongSanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DongSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.repository.DongSanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.service.DongSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DongSanPhamServiceImpl implements DongSanPhamService {
    @Autowired
    DongSanPhamRepository dongSanPhamRepository;

    @Autowired
    DongSanPhamMapper dongSanPhamMapper;


    @Override
    public Page<DongSanPhamResponse> pageDongSanPhamActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<DongSanPham> dongSanPhamPage = dongSanPhamRepository.pageACTIVE(pageable);
        return dongSanPhamPage.map(dongSanPhamMapper::dongSanPhamEntityToDongSanPhamResponse);
    }

    @Override
    public Page<DongSanPhamResponse> pageDongSanPhamInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<DongSanPham> dongSanPhamPage = dongSanPhamRepository.pageINACTIVE(pageable);
        return dongSanPhamPage.map(dongSanPhamMapper::dongSanPhamEntityToDongSanPhamResponse);

    }

    @Override
    public DongSanPhamResponse add(CreateDongSanPhamRequest createDongSanPhamRequest) {
        DongSanPham dongSanPham = dongSanPhamMapper.createDongSanPhamRequestToDongSanPhamEntity(createDongSanPhamRequest);
        dongSanPham.setNgayTao(LocalDate.now());
        dongSanPham.setTrangThai(ApplicationConstant.TrangThaiSanPham.ACTIVE);
        return dongSanPhamMapper.dongSanPhamEntityToDongSanPhamResponse(dongSanPhamRepository.save(dongSanPham));
    }

    @Override
    public DongSanPhamResponse update(UpdateDongSanPhamRequest updateDongSanPhamRequest) {
        DongSanPham dongSanPham = dongSanPhamMapper.updateDongSanPhamRequestToDongSanPhamEntity(updateDongSanPhamRequest);
        dongSanPham.setNgayCapNhat(LocalDate.now());
        return dongSanPhamMapper.dongSanPhamEntityToDongSanPhamResponse(dongSanPhamRepository.save(dongSanPham));
    }

    @Override
    public DongSanPhamResponse getOne(Integer id) {
        Optional<DongSanPham> dongSanPhamOptional = dongSanPhamRepository.findById(id);
        return dongSanPhamMapper.dongSanPhamEntityToDongSanPhamResponse(dongSanPhamOptional.get());
    }

    @Override
    public Page<DongSanPhamResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<DongSanPham> dongSanPhamPage = dongSanPhamRepository.pageSearch(searchName, pageable);
        return dongSanPhamPage.map(dongSanPhamMapper::dongSanPhamEntityToDongSanPhamResponse);

    }

    @Override
    public void deleteDongSanPham(Integer id,LocalDate now) {
        dongSanPhamRepository.delete(id,LocalDate.now());
    }

    @Override
    public void revertDongSanPham(Integer id,LocalDate now) {
        dongSanPhamRepository.revert(id,LocalDate.now());
    }
}
