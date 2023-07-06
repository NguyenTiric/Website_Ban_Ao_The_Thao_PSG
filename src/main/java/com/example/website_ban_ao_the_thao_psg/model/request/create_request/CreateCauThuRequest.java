package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiSanPham;
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
public class CreateCauThuRequest {

    private Integer id;

    private String ma;

    @NotBlank(message = "Tên không để trống")
    @Size(min = 1, max = 45, message = "Email không vượt quá 45 ký tự")
    private String ten;

    @NotNull(message = "Số áo không để trống")
    @Min(value = 1, message = "Số áo phải là số nguyên và lớn hơn 0")
    private Integer soAo;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;

    @Enumerated(EnumType.STRING)
    private TrangThaiSanPham trangThai;
}
