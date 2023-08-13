package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.ViVoucher;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateViVoucherRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateViVoucherRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ViVoucherResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViVoucherMapper {

    ViVoucher viVoucherResponseToViVoucherEntity(ViVoucherResponse viVoucherResponse);

    ViVoucherResponse viVoucherEntityToViVoucherResponse(ViVoucher viVoucher);

    ViVoucher createViVoucherRequestToViVoucherEntity(CreateViVoucherRequest createViVoucherRequest);

    ViVoucher updateViVoucherRequestToViVoucherEntity(UpdateViVoucherRequest updateViVoucherRequest);

    List<ViVoucherResponse> listViVoucherEntityToViVoucherResponse(List<ViVoucher> viVoucherList);
}
