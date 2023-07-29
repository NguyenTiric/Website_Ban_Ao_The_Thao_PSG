package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
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
public interface VoucherThuHangRepository extends JpaRepository<VoucherThuHang,Integer> {

    @Query(value = "SELECT * FROM vocucher_thu_hang WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='ACTIVE' ", nativeQuery = true)
    Page<VoucherThuHang> pageSearchActive(String searchString, Pageable pageable);
    @Query(value = "SELECT * FROM vocucher_thu_hang WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='INACTIVE' ", nativeQuery = true)
    Page<VoucherThuHang> pageSearchIvActive(String searchString, Pageable pageable);

    @Query(value = "SELECT * FROM vocucher_thu_hang WHERE trang_thai='INACTIVE' ", nativeQuery = true)
    Page<VoucherThuHang> pageINACTIVE(Pageable pageable);

    @Query(value = "SELECT * FROM vocucher_thu_hang WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    Page<VoucherThuHang> pageACTIVE(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE VoucherThuHang m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update VoucherThuHang m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revert(@Param("id") Integer id, @Param("now") LocalDate now);
}
