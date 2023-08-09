package com.example.website_ban_ao_the_thao_psg.entity;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiSanPham;
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
@Table(name = "vi_voucher")
public class ViVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_voucher_thu_hang_id")
    private ChiTietVoucherThuHang chiTietVoucherThuHang;

    @Column(name = "ma")
    private String ma;

    @Column(name = "so_tien_giam")
    private BigDecimal soTienGiam;

    @Column(name = "gia_tri_don_hang_toi_thieu")
    private BigDecimal giaTriDonHangToiThieu;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @Column(name = "trang_thai")
    @Enumerated(EnumType.STRING)
    private ApplicationConstant.TrangThaiViVoucher trangThai;

}
