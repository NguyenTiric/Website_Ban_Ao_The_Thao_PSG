package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateLyDoTraHangRequest {

    private Integer id;

    private String ma;
    @NotBlank(message = "Mô tả không để trống")
    @Size(min = 1, max = 225, message = "Mô tả không vượt quá 225 ký tự")
    private String moTa;

    @NotBlank(message = "Lý do trả hàng không để trống")
    @Size(min = 1, max = 225, message = "Lý do trả hàng không vượt quá 225 ký tự")
    private String lyDoTraHang;

    @NotBlank(message = "Hình ảnh không để trống")
    @Size(min = 1, max = 225, message = "Tên không vượt quá 225 ký tự")
    private String hinhAnh;

    @NotBlank(message = "Video không để trống")
    @Size(min = 1, max = 225, message = "Video không vượt quá 225 ký tự")
    private String video;

    @NotBlank(message = "Trạng thái không để trống")
    @Size(min = 1, max = 225, message = "Trạng thái không vượt quá 225 ký tự")
    private String trangThai;
}
