package com.example.website_ban_ao_the_thao_psg.model.response;

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
public class LyDoTraHangResponse {
    private Integer id;
    private String ma;
    private String moTa;
    private String lyDoTraHang;
    private String hinhAnh;
    private String video;
    private String trangThai;
}
