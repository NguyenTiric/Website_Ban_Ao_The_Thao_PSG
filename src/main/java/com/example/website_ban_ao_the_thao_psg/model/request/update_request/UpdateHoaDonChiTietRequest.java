package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiHoaDonChiTiet;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class UpdateHoaDonChiTietRequest {

    private Integer id;

    @NotNull(message = "Chi tiết sản phẩm không để trống")
    private ChiTietSanPham chiTietSanPham;

    @NotNull(message = "Hóa đơn không để trống")
    private HoaDon hoaDon;

    @NotNull(message = "Số lượng không để trống")
    @Min(value = 1, message = "Số lượng phải là số nguyên và lớn hơn 0")
    private Integer soLuong;

    @NotNull(message = "Đơn giá không để trống")
    @Min(value = 1, message = "Đơn giá phải là số nguyên và lớn hơn 0")
    private BigDecimal donGia;

    @NotNull(message = "Giá bán không để trống")
    @Min(value = 1, message = "Giá bán phải là số nguyên và lớn hơn 0")
    private BigDecimal giaBan;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;

    @Enumerated(EnumType.STRING)
    private TrangThaiHoaDonChiTiet trangThai;
}
