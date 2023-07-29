package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiDiaChi;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
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

//    @NotNull(message = "Tài khoản không để trống")
    private TaiKhoan taiKhoan;

    @NotBlank(message = "Họ tên không để trống")
    @Size(min = 0, max = 45, message = "Họ tên không vượt quá 45 ký tự")
    private String hoTen;

    @NotBlank(message = "Số điện thoại không để trống")
    @Size(min = 0, max = 10, message = "Số điện thoại không vượt quá 10 ký tự")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
    private String sdt;

    @NotBlank(message = "Địa chỉ không để trống")
    @Size(min = 0, max = 255, message = "Địa chỉ không vượt quá 255 ký tự")
    private String diaChiChiTiet;

    @NotBlank(message = "Mã tỉnh không để trống")
    @Size(min = 0, max = 10, message = "Mã tỉnh không vượt quá 10 ký tự")
    private String maTinh;

    @NotBlank(message = "Tên tỉnh không để trống")
    @Size(min = 0, max = 255, message = "Tên tỉnh không vượt quá 255 ký tự")
    private String tenTinh;

    @NotBlank(message = "Mã quận huyện không để trống")
    @Size(min = 0, max = 10, message = "Mã quận huyện không vượt quá 10 ký tự")
    private String maQuanHuyen;

    @NotBlank(message = "Tên quận huyện không để trống")
    @Size(min = 0, max = 255, message = "Tên quận huyện không vượt quá 255 ký tự")
    private String tenQuanHuyen;

    @NotBlank(message = "Mã Phường/Xã không để trống")
    @Size(min = 0, max = 255, message = "Mã Phường/Xã không vượt quá 255 ký tự")
    private String maPhuongXa;

    @NotBlank(message = "Tên Phường/Xã không để trống")
    @Size(min = 0, max = 255, message = "Tên Phường/Xã không vượt quá 255 ký tự")
    private String tenPhuongXa;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhap;

    @Enumerated(EnumType.STRING)
    private TrangThaiDiaChi trangThai;
}
