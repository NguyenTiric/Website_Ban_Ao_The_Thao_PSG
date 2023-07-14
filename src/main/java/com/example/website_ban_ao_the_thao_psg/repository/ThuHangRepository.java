package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
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
public interface ThuHangRepository extends JpaRepository<ThuHang,Integer> {

    @Query(value = "SELECT * FROM thu_hang WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='ACTIVE' ", nativeQuery = true)
    Page<ThuHang> pageSearchActive(String searchString, org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT * FROM thu_hang WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='INACTIVE' ", nativeQuery = true)
    Page<ThuHang> pageSearchIvActive(String searchString, org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT * FROM thu_hang WHERE trang_thai='INACTIVE' ", nativeQuery = true)
    Page<ThuHang> pageINACTIVE(org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT * FROM thu_hang WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    Page<ThuHang> pageACTIVE(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ThuHang m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update ThuHang m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revert(@Param("id") Integer id, @Param("now") LocalDate now);

}
