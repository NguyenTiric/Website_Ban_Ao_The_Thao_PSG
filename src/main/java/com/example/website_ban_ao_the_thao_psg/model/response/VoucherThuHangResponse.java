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
    private BigDecimal soTienGiam;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private Double dieuKienSuDungVoucher;
    private Integer soLuong;
    private String moTa;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime ngayTao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private TrangThaiVoucher trangThai;
}
