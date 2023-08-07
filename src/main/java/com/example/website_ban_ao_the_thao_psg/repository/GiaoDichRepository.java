package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.GiaoDich;
import com.example.website_ban_ao_the_thao_psg.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich, Integer> {
    List<GiaoDich> getGiaoDichByHoaDon(HoaDon hoaDon);
}
