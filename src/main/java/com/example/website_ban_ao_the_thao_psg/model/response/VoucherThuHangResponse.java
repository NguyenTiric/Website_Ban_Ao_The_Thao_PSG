package com.example.website_ban_ao_the_thao_psg.model.response;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiVoucher;
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
public class VoucherThuHangResponse {
    private Integer id;
    private String ma;
    private String ten;
    private Boolean loaiGiamGia;
    private Double giaTri;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private Double dieuKienSuDungVoucher;
    private Integer soLuong;
    private String moTa;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private TrangThaiVoucher trangThai;
}
