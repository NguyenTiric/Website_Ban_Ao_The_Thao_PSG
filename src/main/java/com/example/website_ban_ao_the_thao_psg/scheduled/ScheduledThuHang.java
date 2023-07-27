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
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private ThuHangRepository thuHangRepository;

    @Scheduled(fixedRate = 5000)
    public void updateThuHang() {
        List<TaiKhoan> taiKhoans = this.taiKhoanRepository.findAll();
        List<ThuHang> activeThuHangList = this.thuHangRepository.findAllByActive();

        for (TaiKhoan taiKhoan : taiKhoans) {
            BigDecimal soTienDaChiTieu = taiKhoan.getSoTienDaChiTieu();
            Integer soLuongDonHangThanhCong = taiKhoan.getSoLuongDonHangThanhCong();
            ThuHang selectedThuHang = null;

            for (ThuHang thuHang : activeThuHangList) {
                BigDecimal soTienToiThieu = thuHang.getSoTienKhachChiToiThieu();
                Integer soDonHangToiThieu = thuHang.getSoLuongDonHangToiThieu();

                if (soDonHangToiThieu <= soLuongDonHangThanhCong && soTienDaChiTieu.compareTo(soTienToiThieu) >= 0) {
                    if (selectedThuHang == null || (soDonHangToiThieu >= selectedThuHang.getSoLuongDonHangToiThieu()
                            && soTienToiThieu.compareTo(selectedThuHang.getSoTienKhachChiToiThieu()) >= 0)) {
                        selectedThuHang = thuHang;
                    }
                }
            }

            if (selectedThuHang != null) {
                taiKhoan.setThuHang(selectedThuHang);
                taiKhoanRepository.save(taiKhoan);
            }
        }
    }
}
