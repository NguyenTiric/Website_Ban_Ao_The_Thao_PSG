package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiQuyDinh;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class UpdateQuyDinhRequest {

    private Integer id;

    @Enumerated(EnumType.STRING)
    private TrangThaiQuyDinh trangThai;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;

    private LocalDate ngayDatLaiThuHang;
}
