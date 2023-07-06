package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.HinhThucThanhToan;
import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.LoaiGiaoDich;
import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiGiaoDich;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
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
public class UpdateGiaoDichRequest {

    private Integer id;

    @NotNull(message = "Hóa đơn không để trống")
    private HoaDon hoaDon;

    @NotNull(message = "Tài khoản không để trống")
    private TaiKhoan taiKhoan;

    @Enumerated(EnumType.STRING)
    private TrangThaiGiaoDich trangThaiGiaoDich;

    @Enumerated(EnumType.STRING)
    private HinhThucThanhToan phuongThucThanhToan;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;

    @Enumerated(EnumType.STRING)
    private LoaiGiaoDich loaiGiaoDich;
}
