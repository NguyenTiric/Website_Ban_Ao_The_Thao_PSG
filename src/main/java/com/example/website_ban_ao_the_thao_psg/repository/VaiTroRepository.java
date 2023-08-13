package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
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
public interface VaiTroRepository extends JpaRepository<VaiTro,Integer> {
    @Query(value = "SELECT * FROM vai_tro WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='ACTIVE' ", nativeQuery = true)
    Page<VaiTro> pageSearchActive(String searchString, Pageable pageable);

    @Query(value = "SELECT * FROM vai_tro WHERE ten LIKE %?1% OR ma LIKE %?1% and trang_thai='INACTIVE' ", nativeQuery = true)
    Page<VaiTro> pageSearchIvActive(String searchString, Pageable pageable);

    @Query(value = "SELECT * FROM vai_tro WHERE trang_thai='INACTIVE' ", nativeQuery = true)
    Page<VaiTro> pageINACTIVE(Pageable pageable);

    @Query(value = "SELECT * FROM vai_tro WHERE trang_thai='ACTIVE' ", nativeQuery = true)
    Page<VaiTro> pageACTIVE(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE VaiTro m SET m.trangThai = 'INACTIVE', m.ngayCapNhap = :now WHERE m.id = :id")
    void delete(@Param("id") Integer id, @Param("now") LocalDate now);

    @Transactional
    @Modifying
    @Query(value = "update VaiTro m set m.trangThai = 'ACTIVE', m.ngayCapNhap= :now where m.id = :id")
    void revert(@Param("id") Integer id, @Param("now") LocalDate now);

    @Query(value = "SELECT * FROM vai_tro vt WHERE vt.trang_thai = 'ACTIVE'", nativeQuery = true)
    List<VaiTro> getAll();


}
