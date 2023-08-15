package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietVoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import jakarta.persistence.Column;
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
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateViVoucherRequest {
    private Integer id;
    private KhachHang khachHang;
    private ChiTietVoucherThuHang chiTietVoucherThuHang;
    private String ma;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private BigDecimal soTienGiam;
    private BigDecimal giaTriDonHangToiThieu;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private ApplicationConstant.TrangThaiViVoucher trangThai;

}
