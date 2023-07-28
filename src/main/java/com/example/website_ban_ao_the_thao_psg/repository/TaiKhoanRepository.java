package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Integer> {

    List<TaiKhoan> findByNgayCapNhatBeforeAndTrangThai(Date ngayReset, String trangThai);

    @Query(value = "select c.ngayDatLaiThuHang from QuyDinh c WHERE c.trangThai = 'ACTIVE'")
    LocalDate findNgayDatLai();

    @Query(value = "UPDATE TaiKhoan t SET t.soLuongDonHangThanhCong = 0, t.soTienDaChiTieu = 0")
    List<TaiKhoan> resetToanBoThuHang();

    @Transactional
    @Modifying
    @Query("UPDATE TaiKhoan t SET t.soLuongDonHangThanhCong = 0, t.soTienDaChiTieu = 0 ")
    void resetSoLuongDonHangThanhCongAndSoTienDaChiTieuVeKhong();
}
