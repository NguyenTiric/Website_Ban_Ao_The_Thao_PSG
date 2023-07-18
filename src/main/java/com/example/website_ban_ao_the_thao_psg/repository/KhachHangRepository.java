package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface KhachHangRepository extends JpaRepository<TaiKhoan,Integer> {

    @Query(value = "SELECT * FROM tai_khoan tk WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax)",
            countQuery = "SELECT COUNT(*) FROM tai_khoan tk WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM tk.ngay_sinh) <= :tuoiMax)",
            nativeQuery = true)
    Page<TaiKhoan> pageSearchTuoiMinMax(
            @Param("tuoiMin") Integer min,
            @Param("tuoiMax") Integer max,
            Pageable pageable
    );


    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id where tk.ten like %?1% or tk.sdt like %?1% or tk.email like %?1% or tk.dia_chi like %?1% and trang_thai = 'ACTIVE' and vt.ten = 'Khách Hàng'", nativeQuery = true )
    Page<TaiKhoan> pageSearchACTIVE(@Param("search") String search, Pageable pageable);



    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'INACTIVE' AND vt.ten = 'Khách Hàng'", nativeQuery = true)
    Page<TaiKhoan> pageINACTIVE(Pageable pageable);

    @Query(value = "SELECT tk.*, vt.ten AS vaiTroTen FROM tai_khoan tk JOIN vai_tro vt ON tk.vai_tro_id = vt.id WHERE tk.trang_thai = 'ACTIVE' AND vt.ten = 'Khách Hàng'", nativeQuery = true)
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
