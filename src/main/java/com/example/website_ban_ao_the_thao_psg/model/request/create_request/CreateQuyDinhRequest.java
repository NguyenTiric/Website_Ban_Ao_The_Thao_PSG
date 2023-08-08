package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiQuyDinh;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
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
public class CreateQuyDinhRequest {

    private Integer id;

    @Enumerated(EnumType.STRING)
    private TrangThaiQuyDinh trangThai;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;


    @Future(message = "Không được chọn ngày quá khứ")
    @NotNull(message = "Ngày đặt lại không để trống")
    private LocalDate ngayDatLaiThuHang;
}
