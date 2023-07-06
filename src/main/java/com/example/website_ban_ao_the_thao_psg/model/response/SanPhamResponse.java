package com.example.website_ban_ao_the_thao_psg.model.response;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.CauThu;
import com.example.website_ban_ao_the_thao_psg.entity.ChatLieu;
import com.example.website_ban_ao_the_thao_psg.entity.CoAo;
import com.example.website_ban_ao_the_thao_psg.entity.CongNghe;
import com.example.website_ban_ao_the_thao_psg.entity.DongSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.LoaiSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
import com.example.website_ban_ao_the_thao_psg.entity.NhaSanXuat;
import com.example.website_ban_ao_the_thao_psg.entity.NuocSanXuat;
import com.example.website_ban_ao_the_thao_psg.entity.ThuongHieu;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SanPhamResponse {
    private Integer id;
    private MauSac mauSac;
    private LoaiSanPham loaiSanPham;
    private ChatLieu chatLieu;
    private DongSanPham dongSanPham;
    private CauThu cauThu;
    private NhaSanXuat nhaSanXuat;
    private ThuongHieu thuongHieu;
    private NuocSanXuat nuocSanXuat;
    private CongNghe congNghe;
    private CoAo coAo;
    private LocalDate namSanXuat;
    private String ma;
    private String ten;
    private BigDecimal gia;
    private String moTa;
    private String maVach;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private ApplicationConstant.TrangThaiTaiKhoan trangThai;
}
