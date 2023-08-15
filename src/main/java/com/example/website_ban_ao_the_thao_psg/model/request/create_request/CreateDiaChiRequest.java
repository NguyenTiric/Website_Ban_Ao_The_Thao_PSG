package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiDiaChi;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class CreateDiaChiRequest {
    private Integer id;
    private KhachHang khachHang;
//    @NotBlank(message = "Tên không để trống")
    private String hoTen;
//    @NotBlank(message = "Số điện thoại không để trống")
//    @Size(max = 10,  message = "Số điện thoại không vượt quá 10 số")
//    @Size(min = 10,  message = "Số điện thoại không được nhỏ hơn 10 số")
//    @Pattern(regexp = "^[0-9]+$", message = "Số điện thoại chỉ được nhập chữ số")
    private String sdt;
    @NotBlank(message = "Địa chỉ chi tiết không để trống")
    private String diaChiChiTiet;
    private String maTinh;
    private String tenTinh;
    private String maQuanHuyen;
    private String tenQuanHuyen;
    private String maPhuongXa;
    private String tenPhuongXa;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhap;
    @Enumerated(EnumType.STRING)
    private TrangThaiDiaChi trangThai;
}
