package com.example.website_ban_ao_the_thao_psg.model.response;

import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
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
public class HoaDonTraHangResponse {
    private Integer id;
    private HoaDon hoaDon;
    private KhachHang khachHang;
    private LocalDate ngayDoiTra;
    private BigDecimal tienHoanTraKhach;
    private String ghiChu;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    private Integer trangThai;
}
