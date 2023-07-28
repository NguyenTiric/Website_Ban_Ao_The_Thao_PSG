package com.example.website_ban_ao_the_thao_psg.scheduled;

import com.example.website_ban_ao_the_thao_psg.entity.QuyDinh;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.repository.QuyDinhRepository;
import com.example.website_ban_ao_the_thao_psg.repository.TaiKhoanRepository;
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
    private TaiKhoanRepository taiKhoanRepository;

    @Scheduled(fixedRate = 6000)
    public void thongBaoReset(){
        SimpleMailMessage message = new SimpleMailMessage();

        LocalDate currentDate = LocalDate.now();

        List<QuyDinh> listQuyDinh = quyDinhRepository.findByNgayCapNhatByTrangThai();
        System.out.println(listQuyDinh);
        for (QuyDinh quyDinh : listQuyDinh) {
            LocalDate ngayDatLaiThuHang = quyDinh.getNgayDatLaiThuHang();
            LocalDate ngayThongBao = ngayDatLaiThuHang.minusDays(30);

            if (ngayDatLaiThuHang.isEqual(currentDate)) {
                List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
                taiKhoanRepository.resetSoLuongDonHangThanhCongAndSoTienDaChiTieuVeKhong();
                for (TaiKhoan taiKhoan : taiKhoanList) {
                    message.setTo("tunganh.devj@gmail.com");
                    message.setSubject("Thông báo đặt lại thứ hạng");
                    message.setText("Xin chào " + taiKhoan.getTen() + ",\n\nĐến trước 1 tháng nữa, chúng ta sẽ đặt lại thứ hạng. Vui lòng chuẩn bị cho điều này.\n\nTrân trọng,\nWebsite của chúng tôi");
                    emailSender.send(message);
                }
            } else if (ngayDatLaiThuHang.isEqual(ngayThongBao)) {
                List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
                for (TaiKhoan taiKhoan : taiKhoanList) {
                    System.out.println(ngayThongBao);
                    message.setTo("tunganh.devj@gmail.com");
                    message.setSubject("Thông báo đặt lại thứ hạng");
                    message.setText("Xin chào " + taiKhoan.getTen() + ",\n\nĐến trước 1 tháng nữa, chúng ta sẽ đặt lại thứ hạng. Vui lòng chuẩn bị cho điều này.\n\nTrân trọng,\nWebsite của chúng tôi");
                    emailSender.send(message);
                }
            }
        }
    }
}
