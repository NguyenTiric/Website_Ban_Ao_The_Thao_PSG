package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiVoucher;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
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

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UpdateChiTietVoucherThuHangRequest {

    private Integer id;

    @NotNull(message = "Thứ hạng không để trống")
    private ThuHang thuHang;

    @NotNull(message = "Thứ hạng không để trống")
    private VoucherThuHang voucherThuHang;


    @NotNull(message = "Số lượng không để trống")
    @Min(value = 1, message = "Số lượng là số nguyên và lớn hơn 0")
    private Integer soLuong;

    @Enumerated(EnumType.STRING)
    private TrangThaiVoucher trangThai;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;
}
