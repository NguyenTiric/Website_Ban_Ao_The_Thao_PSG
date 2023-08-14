package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateNhanVienRequest {

    private Integer id;

    private VaiTro vaiTro;
    @NotBlank(message = "Không Được để Trống")
    private String ten;

    private String ma;

    @NotNull(message = "Giới Tính Không Được Để Trống")
    private Boolean gioiTinh;

    @NotNull(message = "Ngày Sinh Không Được Để Trống")
    private LocalDate ngaySinh;

//    @NotBlank(message = "Không Được để Trống")
    private String diaChi;

    @NotBlank(message = "Không Được để Trống")
    private String sdt;
//    @NotBlank(message = "Không Được để Trống")
    private String soCanCuocCongDan;

    @NotBlank(message = "Không Được để Trống")
    private String email;
//    @NotBlank(message = "Không Được để Trống")
    private String matKhau;

    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private ApplicationConstant.TrangThaiTaiKhoan trangThai;

    @Lob
    private Blob anhNV;
}
