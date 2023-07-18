package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.mapper.TaiKhoanMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.NhanVienRepository;
import com.example.website_ban_ao_the_thao_psg.repository.VaiTroRepository;
import com.example.website_ban_ao_the_thao_psg.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private TaiKhoanMapper taiKhoanMapper;

    @Autowired
    private VaiTroRepository vaiTroRepository;
    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage= khachHangRepository.pageACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage= khachHangRepository.pageINACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public TaiKhoanResponse add(CreateKhachHangRequest createKhachHangRequest) {
        try {
            Path path = Paths.get("src/main/resources/static/image");
            String originalFilename = createKhachHangRequest.getAnh().getOriginalFilename().toLowerCase();
            Path imagePath = path.resolve(originalFilename);
            Files.copy(createKhachHangRequest.getAnh().getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println(originalFilename);
            // upload
            TaiKhoan taiKhoan=taiKhoanMapper.createTaiKhoanRequestToTaiKhoanEntity(createKhachHangRequest);
            taiKhoan.setNgayTao(LocalDate.now());
            taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
            taiKhoan.setAnh(originalFilename);
            return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(khachHangRepository.save(taiKhoan));

        }catch (Exception x){
            x.printStackTrace();
            return null;
        }


    }

    @Override
    public TaiKhoanResponse update(UpdateKhachHangRequest updateKhachHangRequest) {
        TaiKhoan taiKhoan=taiKhoanMapper.updateTaiKhoanRequestToTaiKhoanEntity(updateKhachHangRequest);
        taiKhoan.setNgayCapNhat(LocalDate.now());
        taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(khachHangRepository.save(taiKhoan));
    }

    @Override
    public TaiKhoanResponse getOne(Integer id) {
        Optional<TaiKhoan> optionalTaiKhoan= khachHangRepository.findById(id);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(optionalTaiKhoan.get());
    }

    @Override
    public void delete(Integer id, LocalDate now) {
        khachHangRepository.delete(id,now);
    }

    @Override
    public void revertTaiKhoan(Integer id, LocalDate now) {
        khachHangRepository.revert(id, now);
    }

    @Override
    public Page<TaiKhoanResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<TaiKhoan> taiKhoanPage= khachHangRepository.pageSearchACTIVE(search,pageable);
        return taiKhoanPage.map(taiKhoanMapper ::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public Page<TaiKhoanResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo,Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage=khachHangRepository.pageSearchTuoiMinMax(min,max,pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }
}
