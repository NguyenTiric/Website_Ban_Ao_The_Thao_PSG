package com.example.website_ban_ao_the_thao_psg.model.response;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NhanVienResponse {
    private Integer id;
    private VaiTro vaiTro;
    private String ten;
    private Boolean gioiTinh;
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDate ngaySinh;
    private String diaChi;
    private String sdt;
    private String soCanCuocCongDan;
    private String email;
    private String matKhau;
    @Lob
    private Blob anh;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    @Enumerated(EnumType.STRING)
    private ApplicationConstant.TrangThaiTaiKhoan trangThai;
}
