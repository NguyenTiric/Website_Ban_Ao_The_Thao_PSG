package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {
    @Query(value = "SELECT * FROM chi_tiet_san_pham WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='ACTIVE' ", nativeQuery = true)
    Page<ChiTietSanPham> pageSearchActive(String searchString, Pageable pageable);

    @Query(value = "SELECT * FROM chi_tiet_san_pham WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    List<ChiTietSanPham> getAll();

    @Query(value = "SELECT * FROM chi_tiet_san_pham WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='INACTIVE' ", nativeQuery = true)
    Page<ChiTietSanPham> pageSearchIvActive(String searchString, Pageable pageable);

    @Query(value = "SELECT * FROM chi_tiet_san_pham WHERE trang_thai='INACTIVE' ", nativeQuery = true)
    Page<ChiTietSanPham> pageINACTIVE(Pageable pageable);
    @Query(value = "delete from chi_tiet_san_pham where trang_thai = 'PENDING' and id = ?", nativeQuery = true)
    void deletePending(@Param("id") Integer id);
    @Query(value = "SELECT * FROM chi_tiet_san_pham WHERE trang_thai='PENDING' ", nativeQuery = true)
    List<ChiTietSanPham> getAllPending();

    @Query(value = "SELECT * FROM chi_tiet_san_pham WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    Page<ChiTietSanPham> pageACTIVE(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ChiTietSanPham m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update ChiTietSanPham m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revert(@Param("id") Integer id, @Param("now") LocalDate now);
}
