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
import java.time.LocalDateTime;
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
    public void thongBaoReset() {

        LocalDate currentDateTime = LocalDate.now();
        List<QuyDinh> listQuyDinh = quyDinhRepository.findByNgayCapNhatByTrangThai();

        System.out.println("Trạng thái ngày đặt lại: " + listQuyDinh);

        for (QuyDinh quyDinh : listQuyDinh) {
            LocalDate ngayDatLaiThuHang = quyDinh.getNgayDatLaiThuHang();
            LocalDate ngayThongBao = ngayDatLaiThuHang.minusDays(30);

            System.out.println("Ngày đặt lại thứ hạng: " + ngayDatLaiThuHang);
            System.out.println("Ngày thông báo: " + ngayThongBao);
            System.out.println("Ngày giờ hiện tại:" + currentDateTime);

            if (ngayDatLaiThuHang.isEqual(currentDateTime)) {
                List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
                taiKhoanRepository.resetSoLuongDonHangThanhCongAndSoTienDaChiTieuVeKhong();

                System.out.println("Thành công nhé!");
                for (TaiKhoan taiKhoan : taiKhoanList) {
//                    SimpleMailMessage message = new SimpleMailMessage();
//                    message.setTo(taiKhoan.getEmail());
//                    message.setSubject("Thông báo đặt lại thứ hạng");
//                    message.setText("Xin chào " + taiKhoan.getTen() + ",\n\nĐã đến ngày reset lại thứ hạng của bạn về mặc định. Cảm ơn vì bạn đã đông hành cùng chúng tôi.\n\nTrân trọng,\nWebsite bán áo thể thao PSG");
//                    emailSender.send(message);
                    System.out.println("Ngon!");

                }
            } else if (currentDateTime.isEqual(ngayThongBao)) {
                List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
                for (TaiKhoan taiKhoan : taiKhoanList) {
//                    SimpleMailMessage message = new SimpleMailMessage();
//                    message.setTo(taiKhoan.getEmail());
//                    message.setSubject("Thông báo đặt lại thứ hạng");
//                    message.setText("Xin chào " + taiKhoan.getTen() + ",\n\nĐến trước 1 tháng nữa, chúng ta sẽ đặt lại thứ hạng. Vui lòng chuẩn bị cho điều này.\n\nTrân trọng,\nWebsite bán áo thể thao PSG");
//                    emailSender.send(message);
                    System.out.println("Ngon2!");
                }
            } else {

                System.out.println("Ngu, chết rồi\n");

            }
        }
    }
}
