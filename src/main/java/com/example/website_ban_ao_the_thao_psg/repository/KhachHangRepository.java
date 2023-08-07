package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Integer> {

    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND vt.ten ='Khách Hàng' and tk.trang_thai='ACTIVE'",
            countQuery = "SELECT COUNT(*) FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND vt.ten ='Khách Hàng' and tk.trang_thai='ACTIVE'",
            nativeQuery = true)
    Page<KhachHang> pageSearchTuoiMinMaxKhachHang(
            @Param("tuoiMin") Integer min,
            @Param("tuoiMax") Integer max,
            Pageable pageable
    );
    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id where tk.ten like %?1% or tk.sdt like %?1% or tk.email like %?1% or tk.dia_chi like %?1% and tk.trang_thai = 'ACTIVE' and vt.ten = 'Khách Hàng'", nativeQuery = true )
    Page<KhachHang> pageSearchACTIVEKhachHang(@Param("search") String search, Pageable pageable);

    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'ACTIVE' AND vt.ten = 'Khách Hàng'", nativeQuery = true)
    Page<KhachHang> pageACTIVEKhachHang(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE KhachHang m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void deleteKhachHang(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update KhachHang m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revertKhachHang(@Param("id") Integer id, @Param("now") LocalDate now);

    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'INACTIVE' AND vt.ten = 'Khách Hàng'", nativeQuery = true)
    Page<KhachHang> pageINACTIVEKhachHang(Pageable pageable);

    List<KhachHang> findByNgayCapNhatBeforeAndTrangThai(Date ngayReset, String trangThai);

    @Query(value = "select c.ngayDatLaiThuHang from QuyDinh c WHERE c.trangThai = 'ACTIVE'")
    LocalDate findNgayDatLai();

    @Query(value = "UPDATE KhachHang t SET t.soLuongDonHangThanhCong = 0, t.soTienDaChiTieu = 0")
    List<KhachHang> resetToanBoThuHang();

    @Transactional
    @Modifying
    @Query("UPDATE KhachHang t SET t.soLuongDonHangThanhCong = 0, t.soTienDaChiTieu = 0 ")
    void resetSoLuongDonHangThanhCongAndSoTienDaChiTieuVeKhong();

}
