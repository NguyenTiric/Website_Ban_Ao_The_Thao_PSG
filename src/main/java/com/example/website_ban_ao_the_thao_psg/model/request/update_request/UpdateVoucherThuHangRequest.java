package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiVoucher;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class UpdateVoucherThuHangRequest {

    private Integer id;

    private String ma;

    @NotBlank(message = "Tên không để trống")
    @Size(min = 1, max = 45, message = "Tên không vượt quá 45 ký tự")
    private String ten;

    @NotNull(message = "Loại giảm giá không để trống")
    private Boolean loaiGiamGia;

    @NotNull(message = "Gía trị không để trống")
    @Min(value = 1, message = "Gía trị là số nguyên và lớn hơn 0")
    private Double giaTri;

    @NotNull(message = "Ngày bắt đầu không để trống")
    private LocalDate ngayBatDau;

    @NotNull(message = "Ngày kết thúc không để trống")
    private LocalDate ngayKetThuc;

    @NotNull(message = "Điều kiện sử dung Voucher không để trống")
    private Double dieuKienSuDungVoucher;

    @NotNull(message = "Số lượng không để trống")
    @Min(value = 1, message = "Số lượng là số nguyên và lớn hơn 0")
    private Integer soLuong;

    @NotBlank(message = "Mô tả không để trống")
    @Size(min = 1, max = 225, message = "Mô tả không vượt quá 225 ký tự")
    private String moTa;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;

    @Enumerated(EnumType.STRING)
    private TrangThaiVoucher trangThai;
}
