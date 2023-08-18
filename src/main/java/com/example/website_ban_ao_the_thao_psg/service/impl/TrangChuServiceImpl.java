package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.entity.SanPham;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ChiTietSanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.SanPhamMapper;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ChiTietSanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.repository.SanPhamRepository;
import com.example.website_ban_ao_the_thao_psg.service.TrangChuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrangChuServiceImpl implements TrangChuService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private SanPhamMapper sanPhamMapper;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private ChiTietSanPhamMapper chiTietSanPhamMapper;

    @Override
    public List<SanPhamResponse> danhSachSanPhamCauThu(Integer id) {
        return sanPhamMapper.listSanPhamEntityToSanPhamResponse(sanPhamRepository.danhSachSanPhamCauThu(id));
    }

    @Override
    public List<ChiTietSanPhamResponse> getChiTietSanPhamBySanPham(Integer idSP)  {
        return chiTietSanPhamMapper.listchiTietSanPhamEntityTochiTietSanPhamResponse(chiTietSanPhamRepository.getChiTietSanPhamBySanPham(sanPhamRepository.findById(idSP).get()));

    }


//    @Override
//    public List<AnhSanPham> pageCauThu() {
//        return trangChuRepository.danhSachCauThuHienThi();
//    }

//    @Override
//    public List<AnhSanPham> getAnhSanPhamBySanPham(SanPham sanPham) {
//        return null;
//    }
}
