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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.## VND")
    private BigDecimal soTienGiam;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ngayBatDau;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ngayKetThuc;
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.## VND")
    private BigDecimal giaTriDonHangToiThieu;
    private String moTa;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private TrangThaiVoucher trangThai;
}
