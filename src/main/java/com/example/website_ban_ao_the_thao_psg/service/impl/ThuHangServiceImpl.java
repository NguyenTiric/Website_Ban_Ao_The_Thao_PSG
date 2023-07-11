package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class ThuHangServiceImpl implements ThuHangService {

    @Autowired
    ThuHangMapper thuHangMapper;

    @Autowired
    ThuHangRepository thuHangRepository;

    @Override
    public Page<ThuHangResponse> pageFindAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ThuHang> thuHangPage = this.thuHangRepository.findAll(pageable);
        return thuHangPage.map(this.thuHangMapper::thuHangEntiyToThuHangResponse);
    }

    @Override
    public ThuHangResponse add(CreateThuHangRequest createThuHangRequest) {
        ThuHang thuHang = this.thuHangMapper.createThuHangRequestToThuHangEntity(createThuHangRequest);
        thuHang.setNgayTao(LocalDate.now());
        thuHang.setTrangThai(ApplicationConstant.TrangThaiThuHang.ACTIVE);
        return this.thuHangMapper.thuHangEntiyToThuHangResponse(this.thuHangRepository.save(thuHang));
    }
}
