package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
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
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    List<HoaDon> getHoaDonByTrangThai(ApplicationConstant.TrangThaiHoaDon trangThai);

    @Transactional
    @Modifying
    @Query(value = "update HoaDon m set m.trangThai = :trangThai where m.id = :id")
    void updateTrangThai(@Param("id") Integer id, @Param("trangThai") ApplicationConstant.TrangThaiHoaDon trangThaiHoaDon);

    @Query(value = "select * from hoa_don where sdt_nguoi_nhan = ?1 or ma = ?1 or tai_khoan_id = (SELECT id FROM tai_khoan WHERE ten = ?1) ", nativeQuery = true)
    Page<HoaDon> listSearch(String path, Pageable pageable);

    @Query(value = "select * from hoa_don where ngay_tao >= ?1 or ngay_tao <= ?2", nativeQuery = true)
    Page<HoaDon> listSearchByDate(Date beginDate, Date endDate,Pageable pageable);

    @Query(value = "SELECT * FROM hoa_don", nativeQuery = true)
    Page<HoaDon> pageHoaDon(Pageable pageable);

    @Query(value = "SELECT hd.* FROM hoa_don hd " +
            "JOIN khach_hang kh ON hd.khach_hang_id = kh.id " +
            "WHERE kh.ten LIKE CONCAT('%',:tim,'%') OR hd.sdt_nguoi_nhan LIKE CONCAT('%',:tim,'%')", nativeQuery = true)
    Page<HoaDon> pageSearchHoaDon(Pageable pageable, @Param("tim") String tim);


    @Query(value = "SELECT hd.* FROM hoa_don hd " +
            "JOIN khach_hang kh ON hd.khach_hang_id = kh.id " +
            "WHERE DATE(hd.ngay_tao) BETWEEN DATE(:ngayBatDau) AND DATE(:ngayKetThuc)", nativeQuery = true)
    Page<HoaDon> pageSearchHoaDonBetweenDates(Pageable pageable, @Param("ngayBatDau") LocalDate ngayBatDau, @Param("ngayKetThuc") LocalDate ngayKetThuc);

    @Query("SELECT h FROM HoaDon h WHERE h.trangThai = :trangThai")
    Page<HoaDon> pageComboboxTrangThaiHoaDon(@Param("trangThai") ApplicationConstant.TrangThaiHoaDon trangThai, Pageable pageable);

}
