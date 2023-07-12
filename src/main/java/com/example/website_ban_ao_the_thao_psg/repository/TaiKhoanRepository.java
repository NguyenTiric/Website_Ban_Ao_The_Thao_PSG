package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
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
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Integer> {
    @Query(value = "SELECT * from tai_khoan WHERE trang_thai='INACTIVE' ", nativeQuery = true)
    Page<TaiKhoan> pageINACTIVE(Pageable pageable);

    @Query(value = "SELECT * from tai_khoan WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    Page<TaiKhoan> pageACTIVE(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TaiKhoan m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);
}
