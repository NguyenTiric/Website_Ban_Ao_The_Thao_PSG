package com.example.website_ban_ao_the_thao_psg.repository;

import com.example.website_ban_ao_the_thao_psg.entity.NhaSanXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaSanXuatRepository extends JpaRepository<NhaSanXuat,Integer> {
}
