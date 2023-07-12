package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.mapper.TaiKhoanMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import com.example.website_ban_ao_the_thao_psg.repository.TaiKhoanRepository;
import com.example.website_ban_ao_the_thao_psg.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private TaiKhoanMapper taiKhoanMapper;
    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage=taiKhoanRepository.pageACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage=taiKhoanRepository.pageINACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public TaiKhoanResponse add(CreateTaiKhoanRequest createTaiKhoanRequest) {
        TaiKhoan taiKhoan=taiKhoanMapper.createTaiKhoanRequestToTaiKhoanEntity(createTaiKhoanRequest);
        taiKhoan.setNgayTao(LocalDate.now());
        taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(taiKhoanRepository.save(taiKhoan));
    }

    @Override
    public TaiKhoanResponse update(UpdateTaiKhoanRequest updateTaiKhoanRequest) {
        TaiKhoan taiKhoan=taiKhoanMapper.updateTaiKhoanRequestToTaiKhoanEntity(updateTaiKhoanRequest);
        taiKhoan.setNgayCapNhat(LocalDate.now());
        taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(taiKhoanRepository.save(taiKhoan));
    }

    @Override
    public TaiKhoanResponse getOne(Integer id) {
        Optional<TaiKhoan>optionalTaiKhoan=taiKhoanRepository.findById(id);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(optionalTaiKhoan.get());
    }

    @Override
    public void delete(Integer id, LocalDate now) {

    }

    @Override
    public void revertTaiKhoan(Integer id, LocalDate now) {

    }
}
