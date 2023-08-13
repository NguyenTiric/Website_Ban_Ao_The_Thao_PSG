package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.ViVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViVoucherRepository extends JpaRepository<ViVoucher,Integer> {

    List<ViVoucher> getViVouchersByKhachHang(KhachHang khachHang);
}
