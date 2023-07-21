package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface NhanVienRepository extends JpaRepository<TaiKhoan,Integer> {

    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen " +
            "FROM tai_khoan tk " +
            "JOIN vai_tro vt ON tk.vai_tro_id = vt.id " +
            "WHERE (tk.ten LIKE %?1% OR tk.sdt LIKE %?1% OR tk.email LIKE %?1% OR tk.dia_chi LIKE %?1%) " +
            "AND tk.trang_thai = 'ACTIVE' AND (vt.ten ='Nhân Viên' OR vt.ten='Admin')", nativeQuery = true)
    Page<TaiKhoan> pageSearchACTIVE(@Param("search") String search, Pageable pageable);



    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND (vt.ten ='Nhân Viên' or vt.ten='Admin') and tk.trang_thai='ACTIVE'",
            countQuery = "SELECT COUNT(*) FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax) AND (vt.ten ='Nhân Viên' or vt.ten='Admin') and tk.trang_thai='ACTIVE'",
            nativeQuery = true)
    Page<TaiKhoan> pageSearchTuoiMinMax(
            @Param("tuoiMin") Integer min,
            @Param("tuoiMax") Integer max,
            Pageable pageable
    );


    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'INACTIVE' AND vt.ten = 'Nhân Viên'", nativeQuery = true)
    Page<TaiKhoan> pageINACTIVE(Pageable pageable);

    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'ACTIVE' AND (vt.ten = 'Nhân Viên' OR vt.ten='Admin')", nativeQuery = true)
    Page<TaiKhoan> pageACTIVE(Pageable pageable);



    @Transactional
    @Modifying
    @Query(value = "UPDATE TaiKhoan m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update TaiKhoan m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revert(@Param("id") Integer id, @Param("now") LocalDate now);
}
