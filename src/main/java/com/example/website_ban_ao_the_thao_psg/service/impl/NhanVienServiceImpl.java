package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.mapper.TaiKhoanMapper;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import com.example.website_ban_ao_the_thao_psg.repository.NhanVienRepository;
import com.example.website_ban_ao_the_thao_psg.repository.VaiTroRepository;
import com.example.website_ban_ao_the_thao_psg.service.NhanVienService;
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
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private TaiKhoanMapper taiKhoanMapper;

    @Autowired
    private VaiTroRepository vaiTroRepository;
    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage= nhanVienRepository.pageACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public Page<TaiKhoanResponse> pageTaiKhoanInActive(Integer pageNo, Integer size) {
        Pageable pageable= PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage= nhanVienRepository.pageINACTIVE(pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public TaiKhoanResponse add(CreateNhanVienRequest createTaiKhoanRequest) {
        try {
            Path path = Paths.get("src/main/resources/static/image");
            String originalFilename = createTaiKhoanRequest.getAnh().getOriginalFilename().toLowerCase();
            Path imagePath = path.resolve(originalFilename);
            Files.copy(createTaiKhoanRequest.getAnh().getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println(originalFilename);
            // upload
            TaiKhoan taiKhoan=taiKhoanMapper.createTaiKhoanRequestToTaiKhoanEntity(createTaiKhoanRequest);
            taiKhoan.setNgayTao(LocalDate.now());
            taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
            taiKhoan.setAnh(originalFilename);
            return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(nhanVienRepository.save(taiKhoan));

        }catch (Exception x){
            x.printStackTrace();
            return null;
        }


    }

    @Override
    public TaiKhoanResponse update(UpdateNhanVienRequest updateNhanVienRequest) {
        TaiKhoan taiKhoan=taiKhoanMapper.updateTaiKhoanRequestToTaiKhoanEntity(updateNhanVienRequest);
        taiKhoan.setNgayCapNhat(LocalDate.now());
        taiKhoan.setTrangThai(ApplicationConstant.TrangThaiTaiKhoan.ACTIVE);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(nhanVienRepository.save(taiKhoan));
    }

    @Override
    public TaiKhoanResponse getOne(Integer id) {
        Optional<TaiKhoan>optionalTaiKhoan= nhanVienRepository.findById(id);
        return taiKhoanMapper.taiKhoanEntityToTaiKhoanResponse(optionalTaiKhoan.get());
    }

    @Override
    public void delete(Integer id, LocalDate now) {
       nhanVienRepository.delete(id,now);
    }

    @Override
    public void revertTaiKhoan(Integer id, LocalDate now) {
    nhanVienRepository.revert(id, now);
    }

    @Override
    public Page<TaiKhoanResponse> pageSearchACTIVE(String search, Integer pageNo, Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<TaiKhoan> taiKhoanPage= nhanVienRepository.pageSearchACTIVE(search,pageable);
        return taiKhoanPage.map(taiKhoanMapper ::taiKhoanEntityToTaiKhoanResponse);
    }

    @Override
    public Page<TaiKhoanResponse> pageSearchTuoiMinMax(Integer min, Integer max, Integer pageNo,Integer size) {
        Pageable pageable=PageRequest.of(pageNo,size);
        Page<TaiKhoan>taiKhoanPage=nhanVienRepository.pageSearchTuoiMinMax(min,max,pageable);
        return taiKhoanPage.map(taiKhoanMapper::taiKhoanEntityToTaiKhoanResponse);
    }
}
