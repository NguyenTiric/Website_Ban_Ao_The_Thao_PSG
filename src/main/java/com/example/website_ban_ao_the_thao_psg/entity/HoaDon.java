package com.example.website_ban_ao_the_thao_psg.entity;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.HinhThucBanHang;
import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiHoaDon;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_thanh_toan")
    private LocalDate ngayThanhToan;

    @Column(name = "ngay_ship")
    private LocalDate ngayShip;

    @Column(name = "ngay_nhan")
    private LocalDate ngayNhan;

    @Column(name = "tien_mat_khach_tra")
    private BigDecimal tienMatKhachTra;

    @Column(name = "tien_khach_chuyen_khoan")
    private BigDecimal tienKhachChuyenKhoan;

    @Column(name = "tien_ship")
    private BigDecimal tienShip;

    @Column(name = "thanh_tien")
    private BigDecimal thanhTien;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "sdt_nguoi_nhan")
    private String sdtNguoiNhan;

    @Column(name = "sdt_nguoi_ship")
    private String sdtNguoiShip;

    @Column(name = "ma_voucher_thu_hang")
    private String maVoucherThuHang;

    @Column(name = "phan_tram_giam_gia")
    private Integer phanTramGiamGia;

    @Column(name = "ma_giao_dich")
    private String maGiaoDich;

    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @Column(name = "hinh_thuc_ban_hang")
    @Enumerated(EnumType.STRING)
    private HinhThucBanHang hinhThucBanHang;

    @Column(name = "trang_thai")
    @Enumerated(EnumType.STRING)
    private TrangThaiHoaDon trangThai;
}
