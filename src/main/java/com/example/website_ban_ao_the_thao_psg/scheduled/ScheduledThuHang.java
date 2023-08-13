package com.example.website_ban_ao_the_thao_psg.scheduled;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.NhanVienRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@EnableScheduling
@Component
public class ScheduledThuHang {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private ThuHangRepository thuHangRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Scheduled(fixedRate = 6000)
    public void updateThuHang() {
        SimpleMailMessage message = new SimpleMailMessage();

        List<KhachHang> khachHangs = this.khachHangRepository.findAll();
        List<ThuHang> activeThuHangList = this.thuHangRepository.findAllByActive();
        for (KhachHang khachHang : khachHangs) {
            BigDecimal soTienDaChiTieu = khachHang.getSoTienDaChiTieu();
            Integer soLuongDonHangThanhCong = khachHang.getSoLuongDonHangThanhCong();
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
                khachHang.setThuHang(selectedThuHang);
                khachHangRepository.save(khachHang);
            }
        }
    }
}
