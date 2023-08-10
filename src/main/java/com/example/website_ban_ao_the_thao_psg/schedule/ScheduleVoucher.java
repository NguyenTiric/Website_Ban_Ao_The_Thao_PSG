package com.example.website_ban_ao_the_thao_psg.schedule;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.repository.VoucherThuHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleVoucher {

    @Autowired
    private VoucherThuHangRepository voucherThuHangRepository;
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    LocalDateTime dateTime = LocalDateTime.of(date, time);

    @Scheduled(fixedRate = 1000)
    public void updateVoucherStatus(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<VoucherThuHang> voucherThuHangs = voucherThuHangRepository.findByNgayKetThucBeforeAndTrangThaiNot(currentDateTime, ApplicationConstant.TrangThaiVoucher.INACTIVE);

        for (VoucherThuHang voucherThuHang : voucherThuHangs) {
            voucherThuHang.setTrangThai(ApplicationConstant.TrangThaiVoucher.INACTIVE);
        }

        voucherThuHangRepository.saveAll(voucherThuHangs);
    }
}
