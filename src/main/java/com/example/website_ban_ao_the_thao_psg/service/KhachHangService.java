package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.NhanVienResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public interface KhachHangService {
    Page<KhachHangResponse> pageTaiKhoanActive(Integer pageNo, Integer size);
    Page<KhachHangResponse>pageTaiKhoanInActive(Integer pageNo, Integer size);
//
  void add(CreateKhachHangRequest createKhachHangRequest, MultipartFile file) throws IOException, SQLException;
    void update(Integer id,MultipartFile file,UpdateKhachHangRequest updateKhachHangRequest)throws IOException, SQLException;

    KhachHangResponse getOne(Integer id);

    void delete(Integer id, LocalDate now);

    void revertTaiKhoan(Integer id,LocalDate now);

    Page<KhachHangResponse>pageSearchACTIVE(String search, Integer pageNo, Integer size);
    Page<KhachHangResponse>pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo, Integer size);
//
    public KhachHang viewById(Integer id);
    Boolean existsBySdtKhachHang(String sdt);
    Boolean existsByEmailKhachHang(String email);

    Boolean existsBySdtKhachHangWithDifferentId(String sdt, Integer id);
    Boolean existsByEmailKhachHangWithDifferentId(String sdt, Integer id);
}
