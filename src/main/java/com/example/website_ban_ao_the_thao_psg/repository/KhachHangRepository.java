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

    @Query(value = "SELECT * FROM khach_hang  WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) <= :tuoiMax) and trang_thai='ACTIVE'",
            countQuery = "SELECT COUNT(*) FROM khach_hang   WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) <= :tuoiMax) trang_thai='ACTIVE'",
            nativeQuery = true)
    Page<KhachHang> pageSearchTuoiMinMaxKhachHang(
            @Param("tuoiMin") Integer min,
            @Param("tuoiMax") Integer max,
            Pageable pageable
    );
    @Query(value = "SELECT * FROM khach_hang  where ten like %?1% or sdt like %?1% or email like %?1% or dia_chi like %?1% and trang_thai = 'ACTIVE' ", nativeQuery = true )
    Page<KhachHang> pageSearchACTIVEKhachHang(@Param("search") String search, Pageable pageable);

    @Query(value = "SELECT * FROM khach_hang WHERE trang_thai = 'ACTIVE'", nativeQuery = true)
    Page<KhachHang> pageACTIVEKhachHang(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE KhachHang m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void deleteKhachHang(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update KhachHang m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revertKhachHang(@Param("id") Integer id, @Param("now") LocalDate now);

    @Query(value = "SELECT * FROM khach_hang  WHERE trang_thai = 'INACTIVE'", nativeQuery = true)
    Page<KhachHang> pageINACTIVEKhachHang(Pageable pageable);

//    List<KhachHang> findByNgayCapNhatBeforeAndTrangThai(Date ngayReset, String trangThai);
//
//    @Query(value = "select c.ngayDatLaiThuHang from QuyDinh c WHERE c.trangThai = 'ACTIVE'")
//    LocalDate findNgayDatLai();
//
//    @Query(value = "UPDATE KhachHang t SET t.soLuongDonHangThanhCong = 0, t.soTienDaChiTieu = 0")
//    List<KhachHang> resetToanBoThuHang();

//    @Transactional
//    @Modifying
//    @Query("UPDATE KhachHang t SET t.soLuongDonHangThanhCong = 0, t.soTienDaChiTieu = 0 ")
//    void resetSoLuongDonHangThanhCongAndSoTienDaChiTieuVeKhong();

        @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM KhachHang tk  WHERE tk.sdt = :sdt ")
    boolean existsBySdtKhachHang(String sdt);

    @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM KhachHang tk  WHERE tk.email = :email")
    boolean existsByEmailKhachHang(String email);

    @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM KhachHang tk WHERE tk.sdt = :sdt AND tk.id <> :id")
    boolean existsBySdtKhachHangWithDifferentId(String sdt, Integer id);

    @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM KhachHang tk WHERE tk.email = :email AND tk.id <> :id")
    boolean existsByEmailKhachHangWithDifferentId(String email, Integer id);
}
