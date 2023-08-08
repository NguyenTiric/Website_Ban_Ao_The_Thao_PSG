package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateQuyDinhRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateQuyDinhRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateQuyDinhRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateQuyDinhRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.QuyDinhResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.QuyDinhResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface QuyDinhService {
    Page<QuyDinhResponse> pageQuyDinhActive(Integer pageNo, Integer size);

    Page<QuyDinhResponse> pageQuyDinhInActive(Integer pageNo, Integer size);

    QuyDinhResponse add(CreateQuyDinhRequest createQuyDinhRequest);

    QuyDinhResponse update(UpdateQuyDinhRequest updateQuyDinhRequest);

    QuyDinhResponse getOne(Integer id);

    Page<QuyDinhResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<QuyDinhResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteQuyDinh(Integer id, LocalDate now);

    void revertQuyDinh(Integer id,LocalDate now);

}
