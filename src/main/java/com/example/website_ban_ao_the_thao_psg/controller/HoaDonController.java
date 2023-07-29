package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/psg/hoa-don")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/hoa-don-cho")
    public String getAllHoaDonCho(Model model) {
        model.addAttribute("listHoaDonCho", hoaDonService.getAllHoaDonCho());
        return "admin/hoa_don/hoa_don_cho";
    }


    @GetMapping("/detail-hoa-don/{id}")
    public String hoaDonDetail(@PathVariable("id") Integer id, Model model) {
        HoaDonResponse hoaDonResponse = hoaDonService.getDetailHoaDon(id);
        List<SanPhamResponse> sanPhamResponseList = chiTietSanPhamService.getAllSP();
        model.addAttribute("hoaDon", hoaDonResponse);
        model.addAttribute("listSanPham", sanPhamResponseList);
        return "admin/hoa_don/hoa_don_detail";
    }

    @PostMapping("/add-hoa-don-cho")
    public String addHoaDonCho() {
        hoaDonService.addHoaDon();
        return "redirect:/admin/psg/hoa-don/hoa-don-cho";
    }

}
