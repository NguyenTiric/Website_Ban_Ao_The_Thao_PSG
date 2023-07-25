package com.example.website_ban_ao_the_thao_psg.model.mapper;

import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuongHieuResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.VoucherThuHangResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoucherThuHangMapper {

    VoucherThuHang voucherThuHangResponseToVoucherThuHangEntity(VoucherThuHangResponse voucherThuHangResponse);

    VoucherThuHangResponse voucherThuHangEntityToVoucherThuHangResponse(VoucherThuHang voucherThuHang);

    VoucherThuHang createVoucherThuHangRequestToVoucherThuHangEntity(CreateVoucherThuHangRequest createVoucherThuHangRequest);

    VoucherThuHang updateVoucherThuHangRequestToVoucherThuHangEntity(UpdateVoucherThuHangRequest updateVoucherThuHangRequest);

    List<VoucherThuHangResponse> listVoucherThuHangEntityToVoucherThuHangResponse(List<VoucherThuHang> voucherThuHangList);
}
