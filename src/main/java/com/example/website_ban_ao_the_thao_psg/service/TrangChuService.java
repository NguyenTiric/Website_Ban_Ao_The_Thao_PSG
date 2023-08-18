package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.AnhSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.CauThu;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.SanPham;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TrangChuService {
//    List<AnhSanPham> pageCauThu();
//    List<AnhSanPham> getAnhSanPhamBySanPham(SanPham sanPham);
List<SanPhamResponse> danhSachSanPhamCauThu(Integer id) ;
List<ChiTietSanPhamResponse> getChiTietSanPhamBySanPham(Integer idSP) ;
}
