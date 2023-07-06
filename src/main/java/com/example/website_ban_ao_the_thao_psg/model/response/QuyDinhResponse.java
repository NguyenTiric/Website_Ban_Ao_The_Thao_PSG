package com.example.website_ban_ao_the_thao_psg.model.response;

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
public class QuyDinhResponse {
    private Integer id;
    private String trangThai;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    private LocalDate ngayDatLaiThuHang;
}
