package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiGioHangChiTiet;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
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
public class UpdateGioHangChiTietRequest {

    private Integer id;

    @NotNull(message = "Chi tiết sản phẩm không để trống")
    private ChiTietSanPham chiTietSanPham;

    @NotNull(message = "Tài khoản id không để trống")
    private TaiKhoan taiKhoanId;

    @NotNull(message = "Số lượng không để trống")
    private Integer soLuong;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;

    @Enumerated(EnumType.STRING)
    private TrangThaiGioHangChiTiet trangThai;
}
