package com.example.website_ban_ao_the_thao_psg.model.request.create_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.LoaiLichSuHoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import com.example.website_ban_ao_the_thao_psg.entity.NhanVien;
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
public class CreateLichSuHoaDonRequest {

    private Integer Id;
    private NhanVien nhanVien;
    private HoaDon hoaDon;
    private LocalDateTime ngayTao;
    private String moTa;
    @Enumerated(EnumType.STRING)
    private LoaiLichSuHoaDon loaiLichSuHoaDon;
}
