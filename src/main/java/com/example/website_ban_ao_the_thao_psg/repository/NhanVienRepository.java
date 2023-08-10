package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.NhanVien;
import com.example.website_ban_ao_the_thao_psg.model.response.NhanVienResponse;
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
public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {

    @Query(value = "SELECT * " +
            "FROM nhan_vien nv " +
            "WHERE (nv.ten LIKE %?1% OR nv.ma LIKE %?1% OR nv.sdt LIKE %?1% OR nv.email LIKE %?1% OR nv.dia_chi LIKE %?1%) " +
            "AND nv.trang_thai = 'ACTIVE'", nativeQuery = true)
    Page<NhanVien> pageSearchACTIVENhanVien(@Param("search") String search, Pageable pageable);



    @Query(value = "SELECT * FROM nhan_vien  WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) <= :tuoiMax)  and trang_thai='ACTIVE'",
            countQuery = "SELECT COUNT(*) FROM nhan_vien  WHERE (:tuoiMin IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) >= :tuoiMin) AND (:tuoiMax IS NULL OR EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM ngay_sinh) <= :tuoiMax)  and trang_thai='ACTIVE'",
            nativeQuery = true)
    Page<NhanVien> pageSearchTuoiMinMaxNhanVien(
            @Param("tuoiMin") Integer min,
            @Param("tuoiMax") Integer max,
            Pageable pageable
    );


    @Query(value = "SELECT *  FROM nhan_vien  WHERE trang_thai = 'INACTIVE' ", nativeQuery = true)
    Page<NhanVien> pageINACTIVENhanVien(Pageable pageable);

    @Query(value = "SELECT * FROM nhan_vien  WHERE trang_thai = 'ACTIVE'", nativeQuery = true)
    Page<NhanVien> pageACTIVENhanVien(Pageable pageable);



    @Transactional
    @Modifying
    @Query(value = "UPDATE NhanVien m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void deleteNhanVien(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update NhanVien m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revertNhanVien(@Param("id") Integer id, @Param("now") LocalDate now);



    @Query("SELECT CASE WHEN COUNT(nv) > 0 THEN true ELSE false END FROM NhanVien nv WHERE nv.sdt = :sdt ")
    boolean existsBySdtNhanVien(String sdt);

    @Query("SELECT CASE WHEN COUNT(nv) > 0 THEN true ELSE false END FROM NhanVien nv  WHERE nv.email = :email ")
    boolean existsByEmailNhanVien(String email);

    @Query("SELECT CASE WHEN COUNT(nv) > 0 THEN true ELSE false END FROM NhanVien nv WHERE nv.sdt = :sdt AND nv.id <> :id")
    boolean existsBySdtNhanVienWithDifferentId(String sdt, Integer id);

    @Query("SELECT CASE WHEN COUNT(nv) > 0 THEN true ELSE false END FROM NhanVien nv WHERE nv.email = :email AND nv.id <> :id")
    boolean existsByEmailNhanVienWithDifferentId(String email, Integer id);



}
