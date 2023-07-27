package com.example.website_ban_ao_the_thao_psg.scheduled;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.repository.TaiKhoanRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@EnableScheduling
@Component
public class ScheduledThuHang {

    @Autowired
    private ThuHangService thuHangService;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private ThuHangRepository thuHangRepository;

    @Scheduled(fixedRate = 5000)
    public void updateThuHang(){
        List<TaiKhoan> taiKhoans = taiKhoanRepository.findAll();

        for (TaiKhoan taiKhoan : taiKhoans) {
            BigDecimal soTienDaChiTieu = taiKhoan.getSoTienDaChiTieu();
            Integer soLuongDonHangThanhCong = taiKhoan.getSoLuongDonHangThanhCong();

            List<ThuHang> thuHangList = thuHangRepository.findAllBySoTienKhachChiToiThieuLessThanEqualAndSoLuongDonHangToiThieuLessThanEqual(
                    soTienDaChiTieu, soLuongDonHangThanhCong);

            if (!thuHangList.isEmpty()) {
                ThuHang thuHang = thuHangList.get(0);
                taiKhoan.setThuHang(thuHang);
                taiKhoanRepository.save(taiKhoan);
            }
        }
    }

//    @Scheduled(fixedDelay = 6000)
//    public void thucHienCapNhat() {
//        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
//
//        for (TaiKhoan taiKhoan : taiKhoanList) {
//            Integer soLuongDonHangThanhCong = taiKhoan.getSoLuongDonHangThanhCong();
//            BigDecimal soTienDaChiTieu = taiKhoan.getSoTienDaChiTieu();
//
//            ThuHang mucThuHang = this.thuHangRepository.findByTen(String.valueOf(taiKhoan.getThuHang()));
//
//            if (mucThuHang != null && soLuongDonHangThanhCong.compareTo(mucThuHang.getSoLuongDonHangToiThieu()) >= 0 &&
//                    soTienDaChiTieu.compareTo(mucThuHang.getSoTienKhachChiToiThieu()) >= 0) {
//                taiKhoan.setId(mucThuHang.getId());
//                this.taiKhoanRepository.save(taiKhoan);
//            }
//        }
//        System.out.println("void 2");
//    }
}
