package com.example.website_ban_ao_the_thao_psg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "hoa_don_tra_hang chi_tiet")
public class HoaDonTraHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hoa_don_tra_hang_id")
    private HoaDonTraHang hoaDonTraHang;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "ly_do_id")
    private Integer lyDoId;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "ten_hang")
    private String tenHang;

    @Column(name = "kich_thuoc")
    private String kichThuoc;

    @Column(name = "mau_sac")
    private String mauSac;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "so_luong_tra")
    private String soLuongTra;

    @Column(name = "tra_hang")
    private String traHang;

    @Column(name = "ly_do")
    private String lyDo;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @Column(name = "trang_thai")
    private String trangThai;
}
