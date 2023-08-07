package com.example.website_ban_ao_the_thao_psg.model.response;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.LoaiGiaoDich;
import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiGiaoDich;
import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.HinhThucThanhToan;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.NhanVien;
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
public class GiaoDichResponse {
    private Integer id;
    private HoaDon hoaDon;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    @Enumerated(EnumType.STRING)
    private TrangThaiGiaoDich trangThaiGiaoDich;
    @Enumerated(EnumType.STRING)
    private HinhThucThanhToan phuongThucThanhToan;
    private LocalDate ngayTao;
    private BigDecimal soTien;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private LoaiGiaoDich loaiGiaoDich;
}
