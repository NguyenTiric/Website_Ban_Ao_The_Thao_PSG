package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.common.GenCode;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietVoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ChiTietVoucherThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.mapper.ThuHangMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietVoucherThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.ChiTietVoucherThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietVoucherThuHangService;
import groovyjarjarpicocli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ChiTietVoucherThuHangServiceImpl implements ChiTietVoucherThuHangService {

    @Autowired
    private ChiTietVoucherThuHangRepository chiTietVoucherThuHangRepository;

    @Autowired
    private ThuHangRepository thuHangRepository;

    @Autowired
    private ThuHangMapper thuHangMapper;

    @Autowired
    private ChiTietVoucherThuHangMapper chiTietVoucherThuHangMapper;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageACTIVE(pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);
    }

    @Override
    public List<ChiTietVoucherThuHangResponse> getAll() {
        List<ChiTietVoucherThuHang> chiTietVoucherThuHangList = chiTietVoucherThuHangRepository.getAll();
        return chiTietVoucherThuHangMapper.listChiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangList);
    }

    @Override
    public Page<ChiTietVoucherThuHangResponse> pageChiTietVoucherThuHangInActive(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageINACTIVE(pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);
    }

    @Override
    public void addChiTietVoucher(CreateThuHangRequest createThuHangRequest, List<VoucherThuHang> thuHangList) {
        ThuHang thuHang = thuHangMapper.createThuHangRequestToThuHangEntity(createThuHangRequest);
        thuHang.setNgayTao(LocalDate.now());
        thuHang.setMa(GenCode.generateThuHangCode());
        thuHang.setTrangThai(ApplicationConstant.TrangThaiThuHang.ACTIVE);


        String tenThuHang = createThuHangRequest.getTen();

        if (this.thuHangRepository.existsByTenAndTrangThai(tenThuHang, ApplicationConstant.TrangThaiThuHang.ACTIVE)){
            throw new CommandLine.DuplicateNameException("Thứ hạng đã tồn tại, vui lòng đặt thứ hạng khác!");
        }

        List<KhachHang> allKhachHang = khachHangRepository.findAll();
        for (KhachHang khachHang : allKhachHang) {
            if (!khachHang.getThuHang().getTen().equalsIgnoreCase("Thành viên")) {
                throw new RuntimeException("Vẫn còn tài khoản chưa về mặc định, hãy kiểm tra lại!");
            }
        }
        ThuHang th = this.thuHangRepository.save(thuHang);
        for (VoucherThuHang vcth : thuHangList) {
            ChiTietVoucherThuHang chiTietVoucherThuHang = chiTietVoucherThuHangMapper.createChiTietVoucherThuHangRequestToChiTietVouCherThuHangEntity(new CreateChiTietVoucherThuHangRequest());
            chiTietVoucherThuHang.setNgayTao(LocalDate.now());
            chiTietVoucherThuHang.setThuHang(th);
            chiTietVoucherThuHang.setVoucherThuHang(vcth);
            chiTietVoucherThuHang.setSoLuong(1);
            chiTietVoucherThuHang.setTrangThai(ApplicationConstant.TrangThaiChiTietVouCherThuHang.PENDING);
            chiTietVoucherThuHangMapper.chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangRepository.save(chiTietVoucherThuHang));
        }
    }

    @Override
    public ChiTietVoucherThuHangResponse add(CreateChiTietVoucherThuHangRequest createChiTietVoucherThuHangRequest) {
        ChiTietVoucherThuHang chiTietVoucherThuHang = chiTietVoucherThuHangMapper.createChiTietVoucherThuHangRequestToChiTietVouCherThuHangEntity(createChiTietVoucherThuHangRequest);
        chiTietVoucherThuHang.setNgayTao(LocalDate.now());
        chiTietVoucherThuHang.setTrangThai(ApplicationConstant.TrangThaiChiTietVouCherThuHang.ACTIVE);
        return chiTietVoucherThuHangMapper.chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangRepository.save(chiTietVoucherThuHang));
    }

    @Override
    public ChiTietVoucherThuHangResponse update(UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest) {
        ChiTietVoucherThuHang chiTietVoucherThuHang = chiTietVoucherThuHangMapper.updateChiTietVoucherThuHangRequestToChiTietVouCherThuHangEntity(updateChiTietVoucherThuHangRequest);
        chiTietVoucherThuHang.setNgayCapNhat(LocalDate.now());
        chiTietVoucherThuHang.setTrangThai(ApplicationConstant.TrangThaiChiTietVouCherThuHang.PENDING);
        return chiTietVoucherThuHangMapper.chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangRepository.save(chiTietVoucherThuHang));
    }

    @Override
    public List<ChiTietVoucherThuHangResponse> getTheoIdThuHang(Integer id) {
        List<ChiTietVoucherThuHang> list = this.chiTietVoucherThuHangRepository.getChiTietVoucherThuHangByThuHang(this.thuHangRepository.findById(id).get());
        return this.chiTietVoucherThuHangMapper.listChiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(list);
    }

    @Override
    public ChiTietVoucherThuHangResponse getOne(Integer id) {
        Optional<ChiTietVoucherThuHang> chiTietVoucherThuHangOptional = chiTietVoucherThuHangRepository.findById(id);
        return chiTietVoucherThuHangMapper.chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(chiTietVoucherThuHangOptional.get());
    }

    @Override
    public Page<ChiTietVoucherThuHangResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageSearchActive(searchName, pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);
    }

    @Override
    public Page<ChiTietVoucherThuHangResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ChiTietVoucherThuHang> chiTietVoucherThuHangPage = chiTietVoucherThuHangRepository.pageSearchIvActive(searchName, pageable);
        return chiTietVoucherThuHangPage.map(chiTietVoucherThuHangMapper::chiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse);
    }


    @Override
    public void deleteChiTietVoucherThuHang(Integer id, LocalDate now) {
        chiTietVoucherThuHangRepository.delete(id, LocalDate.now());
    }

    @Override
    public void revertChiTietVoucherThuHang(Integer id, LocalDate now) {
        chiTietVoucherThuHangRepository.revert(id, LocalDate.now());
    }

    @Override
    public List<ChiTietVoucherThuHang> getByTrangThaiPending() {
        return this.chiTietVoucherThuHangRepository.getChiTietVoucherThuHangPending();
    }

    @Override
    public List<ChiTietVoucherThuHang> getMaxidThuHang() {
        return this.chiTietVoucherThuHangRepository.findLatestChiTietVoucherThuHang();
    }

    @Override
    public void updateSoLuongVoucherThuHang(List<Integer> id, List<Integer> soLuong) {
        for (int i = 0; i < id.size(); i++){
            Integer ids = id.get(i);
            Integer soLuongs = soLuong.get(i);
            Optional<ChiTietVoucherThuHang> list = this.chiTietVoucherThuHangRepository.findById(ids);
            if (list.isPresent()){
                ChiTietVoucherThuHang chiTietVoucherThuHang = list.get();
                chiTietVoucherThuHang.setSoLuong(soLuongs);
                chiTietVoucherThuHang.setNgayTao(LocalDate.now());
                chiTietVoucherThuHang.setTrangThai(ApplicationConstant.TrangThaiChiTietVouCherThuHang.ACTIVE);
                this.chiTietVoucherThuHangRepository.save(chiTietVoucherThuHang);
            }
        }
    }

    @Override
    public void updateSoLuongVoucherThuHangActive(List<Integer> id, List<Integer> soLuong){
        for (int i = 0; i < id.size(); i++){
            Optional<ChiTietVoucherThuHang> list = this.chiTietVoucherThuHangRepository.findById(id.get(i));
            if (list.isPresent()){
                ChiTietVoucherThuHang chiTietVoucherThuHang = list.get();
                chiTietVoucherThuHang.setSoLuong(soLuong.get(i));
                chiTietVoucherThuHang.setNgayCapNhat(LocalDate.now());
                this.chiTietVoucherThuHangRepository.save(chiTietVoucherThuHang);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        this.chiTietVoucherThuHangRepository.deleteById(id);
    }

    @Override
    public void updateListVoucherThuHangInUpdateChiTietVoucherThuHang(List<VoucherThuHang> voucherThuHangList, Integer id) {
        for (VoucherThuHang list : voucherThuHangList) {
            List<ChiTietVoucherThuHang> chiTietVoucherThuHangList = this.chiTietVoucherThuHangRepository.getChiTietVoucherThuHangByThuHang(this.thuHangRepository.findById(id).get());

            boolean voucherExist = false;

            for (ChiTietVoucherThuHang chiTietVoucherThuHang : chiTietVoucherThuHangList) {
                if (list.getId().equals(chiTietVoucherThuHang.getId())){
                    voucherExist = true;
                    break;
                }
            }

            if (voucherExist){
                continue;
            }

            ChiTietVoucherThuHang ctvth = chiTietVoucherThuHangMapper.createChiTietVoucherThuHangRequestToChiTietVouCherThuHangEntity(new CreateChiTietVoucherThuHangRequest());
            ThuHang th = this.thuHangRepository.findById(id).get();
            ctvth.setThuHang(th);
            ctvth.setSoLuong(1);
            ctvth.setNgayTao(LocalDate.now());
            ctvth.setTrangThai(ApplicationConstant.TrangThaiChiTietVouCherThuHang.ACTIVE);
            this.chiTietVoucherThuHangMapper.listChiTietVoucherThuHangEntityToChiTietVoucherThuHangResponse(Collections.singletonList(this.chiTietVoucherThuHangRepository.save(ctvth)));
        }
    }
}
