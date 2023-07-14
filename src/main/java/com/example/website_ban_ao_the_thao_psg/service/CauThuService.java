package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CauThuResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CauThuService {
    Page<CauThuResponse> pageCauThuResponse(Integer pageNo, Integer size);

    CauThuResponse add(CreateCauThuRequest createCauThuRequest);

    CauThuResponse update(UpdateCauThuRequest updateCauThuRequest);

    CauThuResponse getOne(Integer id);

    CauThuResponse delete(UpdateCauThuRequest updateCauThuRequest, Integer id);
}
