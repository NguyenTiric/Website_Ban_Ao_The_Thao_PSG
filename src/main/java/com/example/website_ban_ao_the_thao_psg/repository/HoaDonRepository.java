package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    List<HoaDon> getHoaDonByTrangThai(ApplicationConstant.TrangThaiHoaDon trangThai);
    @Query(value = "select * from hoa_don where sdt_nguoi_nhan = ?1 or ma = ?1 or tai_khoan_id = (SELECT id FROM tai_khoan WHERE ten = ?1) ", nativeQuery = true)
    Page<HoaDon> listSearch(String path, Pageable pageable);

    @Query(value = "select * from hoa_don where ngay_tao >= ?1 or ngay_tao <= ?2", nativeQuery = true)
    Page<HoaDon> listSearchByDate(Date beginDate, Date endDate,Pageable pageable);
}
