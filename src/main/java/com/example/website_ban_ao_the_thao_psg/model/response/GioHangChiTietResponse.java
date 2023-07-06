package com.example.website_ban_ao_the_thao_psg.model.response;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiGioHangChiTiet;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GioHangChiTietResponse {
    private Integer id;
    private ChiTietSanPham chiTietSanPham;
    private TaiKhoan taiKhoanId;
    private Integer soLuong;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private TrangThaiGioHangChiTiet trangThai;
}
