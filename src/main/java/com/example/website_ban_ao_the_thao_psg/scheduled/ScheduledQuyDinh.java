package com.example.website_ban_ao_the_thao_psg.scheduled;

import com.example.website_ban_ao_the_thao_psg.entity.QuyDinh;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.repository.QuyDinhRepository;
import com.example.website_ban_ao_the_thao_psg.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledQuyDinh {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private QuyDinhRepository quyDinhRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Scheduled(fixedRate = 6000)
    public void thongBaoReset() {
        LocalDate currentDate = LocalDate.now();
        List<QuyDinh> listQuyDinh = quyDinhRepository.findByNgayCapNhatByTrangThai();

        for (QuyDinh quyDinh : listQuyDinh) {
            LocalDate ngayDatLaiThuHang = quyDinh.getNgayDatLaiThuHang();
            LocalDate ngayThongBao = ngayDatLaiThuHang.minusDays(30);

            if (ngayDatLaiThuHang.isEqual(currentDate)) {
                List<KhachHang> khachHangList = nhanVienRepository.findAll();
                nhanVienRepository.resetSoLuongDonHangThanhCongAndSoTienDaChiTieuVeKhong();
                for (KhachHang khachHang : khachHangList) {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(khachHang.getEmail());
                    message.setSubject("Thông báo đặt lại thứ hạng");
                    message.setText("Xin chào " + khachHang.getTen() + ",\n\nĐến trước 1 tháng nữa, chúng ta sẽ đặt lại thứ hạng. Vui lòng chuẩn bị cho điều này.\n\nTrân trọng,\nWebsite của chúng tôi");
                    emailSender.send(message);
                }
            } else if (ngayDatLaiThuHang.isEqual(ngayThongBao)) {
                List<KhachHang> khachHangList = nhanVienRepository.findAll();
                for (KhachHang khachHang : khachHangList) {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(khachHang.getEmail());
                    message.setSubject("Thông báo đặt lại thứ hạng");
                    message.setText("Xin chào " + khachHang.getTen() + ",\n\nĐến trước 1 tháng nữa, chúng ta sẽ đặt lại thứ hạng. Vui lòng chuẩn bị cho điều này.\n\nTrân trọng,\nWebsite của chúng tôi");
                    emailSender.send(message);
                }
            }
        }
    }

}
