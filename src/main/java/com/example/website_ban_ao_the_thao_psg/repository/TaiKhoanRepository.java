//package com.example.website_ban_ao_the_thao_psg.repository;
//
//import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
//import jakarta.transaction.Transactional;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//
//@Repository
//public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Integer> {
//
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen " +
//            "FROM tai_khoan tk " +
//            "JOIN vai_tro vt ON tk.vai_tro_id = vt.id " +
//            "WHERE (tk.ten LIKE %?1% OR tk.sdt LIKE %?1% OR tk.email LIKE %?1% OR tk.dia_chi LIKE %?1%) " +
//            "AND tk.trang_thai = 'ACTIVE' AND (vt.ten ='Nhân Viên' OR vt.ten='Admin')", nativeQuery = true)
//    Page<TaiKhoan> pageSearchACTIVENhanVien(@Param("search") String search, Pageable pageable);
//
//
//
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND (vt.ten ='Nhân Viên' or vt.ten='Admin') and tk.trang_thai='ACTIVE'",
//            countQuery = "SELECT COUNT(*) FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND (vt.ten ='Nhân Viên' or vt.ten='Admin') and tk.trang_thai='ACTIVE'",
//            nativeQuery = true)
//    Page<TaiKhoan> pageSearchTuoiMinMaxNhanVien(
//            @Param("tuoiMin") Integer min,
//            @Param("tuoiMax") Integer max,
//            Pageable pageable
//    );
//
//
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'INACTIVE' AND vt.ten = 'Nhân Viên'", nativeQuery = true)
//    Page<TaiKhoan> pageINACTIVENhanVien(Pageable pageable);
//
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'ACTIVE' AND (vt.ten = 'Nhân Viên' OR vt.ten='Admin')", nativeQuery = true)
//    Page<TaiKhoan> pageACTIVENhanVien(Pageable pageable);
//
//
//
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE TaiKhoan m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
//    void deleteNhanVien(@Param("id") Integer id, @Param("now") LocalDate now);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update TaiKhoan m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
//    void revertNhanVien(@Param("id") Integer id, @Param("now") LocalDate now);
//
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND vt.ten ='Khách Hàng' and tk.trang_thai='ACTIVE'",
//            countQuery = "SELECT COUNT(*) FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND vt.ten ='Khách Hàng' and tk.trang_thai='ACTIVE'",
//            nativeQuery = true)
//    Page<TaiKhoan> pageSearchTuoiMinMaxKhachHang(
//            @Param("tuoiMin") Integer min,
//            @Param("tuoiMax") Integer max,
//            Pageable pageable
//    );
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id where tk.ten like %?1% or tk.sdt like %?1% or tk.email like %?1% or tk.dia_chi like %?1% and tk.trang_thai = 'ACTIVE' and vt.ten = 'Khách Hàng'", nativeQuery = true )
//    Page<TaiKhoan> pageSearchACTIVEKhachHang(@Param("search") String search, Pageable pageable);
//
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'ACTIVE' AND vt.ten = 'Khách Hàng'", nativeQuery = true)
//    Page<TaiKhoan> pageACTIVEKhachHang(Pageable pageable);
//
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE TaiKhoan m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
//    void deleteKhachHang(@Param("id") Integer id, @Param("now") LocalDate now);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update TaiKhoan m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
//    void revertKhachHang(@Param("id") Integer id, @Param("now") LocalDate now);
//
//    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'INACTIVE' AND vt.ten = 'Khách Hàng'", nativeQuery = true)
//    Page<TaiKhoan> pageINACTIVEKhachHang(Pageable pageable);
//
//    @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM TaiKhoan tk JOIN tk.vaiTro vt WHERE tk.sdt = :sdt AND vt.ten = 'Khách Hàng'")
//    boolean existsBySdtKhachHang(String sdt);
//
//    @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM TaiKhoan tk JOIN tk.vaiTro vt WHERE tk.sdt = :sdt AND vt.ten = 'Nhân Viên'")
//    boolean existsBySdtNhanVien(String sdt);
//
//    @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM TaiKhoan tk JOIN tk.vaiTro vt WHERE tk.email = :email AND vt.ten = 'Nhân Viên'")
//    boolean existsByEmailNhanVien(String email);
//
//    @Query("SELECT CASE WHEN COUNT(tk) > 0 THEN true ELSE false END FROM TaiKhoan tk JOIN tk.vaiTro vt WHERE tk.email = :email AND vt.ten = 'Khách Hàng'")
//    boolean existsByEmailKhachHang(String email);
//
//
//
//}
