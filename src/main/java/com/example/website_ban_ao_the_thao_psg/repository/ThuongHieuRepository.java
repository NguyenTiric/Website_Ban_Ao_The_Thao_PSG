package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuongHieu;
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
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Integer> {
    @Query(value = "SELECT * FROM thuong_hieu WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='ACTIVE' ", nativeQuery = true)
    Page<ThuongHieu> pageSearchActive(String searchString, Pageable pageable);
   @Query(value = "SELECT * FROM thuong_hieu WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='INACTIVE' ", nativeQuery = true)
    Page<ThuongHieu> pageSearchIvActive(String searchString, Pageable pageable);

    @Query(value = "SELECT * FROM thuong_hieu WHERE trang_thai='INACTIVE' ", nativeQuery = true)
    Page<ThuongHieu> pageINACTIVE(Pageable pageable);

    @Query(value = "SELECT * FROM thuong_hieu WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    Page<ThuongHieu> pageACTIVE(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ThuongHieu m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update ThuongHieu m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revert(@Param("id") Integer id, @Param("now") LocalDate now);
}
