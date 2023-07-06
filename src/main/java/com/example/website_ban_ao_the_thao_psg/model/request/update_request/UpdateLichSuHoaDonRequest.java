package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.LoaiLichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UpdateLichSuHoaDonRequest {

    private Integer id;

    private TaiKhoan taiKhoan;

    private HoaDon hoaDon;

    private LocalDateTime ngayTao;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private LoaiLichSuHoaDon loaiLichSuHoaDon;
}
