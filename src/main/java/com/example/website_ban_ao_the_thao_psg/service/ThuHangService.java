package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ThuHangService {

    Page<ThuHangResponse> pageThuHangActive(Integer pageNo, Integer size);

    Page<ThuHangResponse> pageThuHangInActive(Integer pageNo, Integer size);

    ThuHangResponse add(CreateThuHangRequest createThuHangRequest);

    ThuHangResponse update(UpdateThuHangRequest updateThuHangRequest);

    ThuHangResponse getOne(Integer id);
    Page<ThuHangResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<ThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteThuHang(Integer id, LocalDate now);

    void revertThuHang(Integer id,LocalDate now);
}
