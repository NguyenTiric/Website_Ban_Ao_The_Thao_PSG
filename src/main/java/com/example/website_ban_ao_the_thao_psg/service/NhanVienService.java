package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.NhanVien;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NhanVienResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public interface NhanVienService {
    Page<NhanVienResponse>pageNhanVienActive(Integer pageNo, Integer size);
    Page<NhanVienResponse>pageTaiKhoanInActive(Integer pageNo, Integer size);
//
    void add(CreateNhanVienRequest createNhanVienRequest, MultipartFile file) throws IOException, SQLException;
    void update(Integer id, MultipartFile file, UpdateNhanVienRequest updateNhanVienRequest)throws IOException, SQLException;
    NhanVienResponse getOne(Integer id);
//
    void delete(Integer id, LocalDate now);
//
    void revertTaiKhoan(Integer id,LocalDate now);
//
    Page<NhanVienResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size);
    Page<NhanVienResponse>pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo, Integer size);
    public NhanVien viewById(Integer id);
    Boolean existsBySdtNhanVien(String sdt);
    Boolean existsByEmailNhanVien(String email);
    Boolean existsBySdtNhanVienWithDifferentId(String sdt, Integer id);
    Boolean existsByEmailNhanVienWithDifferentId(String sdt, Integer id);

}
