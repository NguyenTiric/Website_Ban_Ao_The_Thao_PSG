package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.QuyDinh;
import com.example.website_ban_ao_the_thao_psg.entity.QuyDinh;
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
public interface QuyDinhRepository extends JpaRepository<QuyDinh,Integer> {

    @Query(value = "SELECT * FROM quy_dinh WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='ACTIVE' ", nativeQuery = true)
    Page<QuyDinh> pageSearchActive(String searchString, Pageable pageable);
    @Query(value = "SELECT * FROM quy_dinh WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='INACTIVE' ", nativeQuery = true)
    Page<QuyDinh> pageSearchIvActive(String searchString, Pageable pageable);

    @Query(value = "SELECT * FROM quy_dinh WHERE trang_thai='INACTIVE' ", nativeQuery = true)
    Page<QuyDinh> pageINACTIVE(Pageable pageable);

    @Query(value = "SELECT * FROM quy_dinh WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    Page<QuyDinh> pageACTIVE(Pageable pageable);

    @Query(value = "select c from QuyDinh c where c.trangThai = 'ACTIVE'")
    List<QuyDinh> findByNgayCapNhatByTrangThai();

    @Transactional
    @Modifying
    @Query(value = "UPDATE QuyDinh m SET m.trangThai = 'INACTIVE', m.ngayCapNhat = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update QuyDinh m set m.trangThai = 'ACTIVE', m.ngayCapNhat= :now where m.id = :id")
    void revert(@Param("id") Integer id, @Param("now") LocalDate now);
}
