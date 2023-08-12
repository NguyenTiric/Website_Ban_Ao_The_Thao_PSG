package com.example.website_ban_ao_the_thao_psg.scheduled;

import com.example.website_ban_ao_the_thao_psg.entity.QuyDinh;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.QuyDinhRepository;
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
    private KhachHangRepository khachHangRepository;

    private boolean isEmailSentToday = false;

    @Scheduled(fixedRate = 6000)
    public void resetEmailFlag() {
        isEmailSentToday = false;
    }

    @Scheduled(fixedRate = 6000)
    public void thongBaoReset() {

        LocalDate currentDateTime = LocalDate.now();
        List<QuyDinh> listQuyDinh = quyDinhRepository.findByNgayCapNhatByTrangThai();

//        System.out.println("Trạng thái ngày đặt lại: " + listQuyDinh);

        for (QuyDinh quyDinh : listQuyDinh) {
            LocalDate ngayDatLaiThuHang = quyDinh.getNgayDatLaiThuHang();
            LocalDate ngayThongBao = ngayDatLaiThuHang.minusDays(30);

            System.out.println("Ngày đặt lại thứ hạng: " + ngayDatLaiThuHang);
            System.out.println("Ngày thông báo: " + ngayThongBao);
            System.out.println("Ngày giờ hiện tại:" + currentDateTime);

            if (ngayDatLaiThuHang.isEqual(currentDateTime)) {
                List<KhachHang> khachHangList = khachHangRepository.findAll();
                khachHangRepository.resetSoLuongDonHangThanhCongAndSoTienDaChiTieuVeKhong();
                this.quyDinhRepository.updateTrangThaiSauResetThuHang(ngayDatLaiThuHang);
                System.out.println("Thành công nhé!");

                if (!isEmailSentToday) {
                    for (KhachHang khachHang : khachHangList) {
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setTo(khachHang.getEmail());
                        message.setSubject("Thông báo đặt lại thứ hạng");
                        message.setText("Xin chào " + khachHang.getTen() + ",\n\nĐã đến ngày reset lại thứ hạng của bạn về mặc định. Cảm ơn vì bạn đã đông hành cùng chúng tôi.\n\nTrân trọng,\nWebsite bán áo thể thao PSG");
                        emailSender.send(message);

                        System.out.println("Ngon!");
                        isEmailSentToday = true;
                    }
                }

            } else if (ngayThongBao.isEqual(currentDateTime)) {
                List<KhachHang> khachHangList = khachHangRepository.findAll();
                if (!isEmailSentToday) {
                    for (KhachHang khachHang : khachHangList) {
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setTo(khachHang.getEmail());
                        message.setSubject("Thông báo đặt lại thứ hạng");
                        message.setText("Xin chào " + khachHang.getTen() + ",\n\nĐến trước 1 tháng nữa, chúng ta sẽ đặt lại thứ hạng. Vui lòng chuẩn bị cho điều này.\n\nTrân trọng,\nWebsite bán áo thể thao PSG");
                        emailSender.send(message);
                        System.out.println("Ngon2!");
                        isEmailSentToday = true;
                    }
                }

            } else {
                System.out.println("Ngu, chết rồi\n");
            }
        }
    }
}
