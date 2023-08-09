package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonChiTietResponse;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/gio-hang/{id}")
    public String gioHang(@PathVariable("id") Integer id, Model model) {
        List<HoaDonChiTietResponse> listGioHang = hoaDonService.getAllHoaDonChiTiet(id);
        model.addAttribute("listHoaDonCho", hoaDonService.getAllHoaDonCho());
        model.addAttribute("listGioHang", listGioHang);
        return "admin/hoa_don/gio_hang";
    }

    @GetMapping("/lich-su-hoa-don/{id}")
    public String lichSuHoaDon(@PathVariable("id") Integer id, Model model) {
        HoaDonResponse hoaDonResponse = hoaDonService.getDetailHoaDon(id);
        hoaDonResponse.setTrangThai(ApplicationConstant.TrangThaiHoaDon.PENDING);
        model.addAttribute("hoaDon", hoaDonResponse);
        model.addAttribute("listLichSuHoaDon", hoaDonService.getAllLichSuHoaDon(id));
        model.addAttribute("listGiaoDich", hoaDonService.getAllGiaoDich(id));
        model.addAttribute("listHoaDonChiTiet", hoaDonService.getAllHoaDonChiTiet(id));
        model.addAttribute("trangThaiHoaDon", hoaDonResponse.getTrangThai());
        return "admin/hoa_don/lich_su_hoa_don";
    }

    @PostMapping("/add-hoa-don-cho")
    public String addHoaDonCho() {
        hoaDonService.addHoaDon();
        return "redirect:/admin/psg/hoa-don/hoa-don-cho";
    }


    @PostMapping("/update-trang-thai-hoa-don/{id}")
    public String updateTrangThaiHoaDon(@PathVariable("id") Integer idhd, @RequestParam("trangThai") ApplicationConstant.TrangThaiHoaDon trangThaiHoaDon) {
        hoaDonService.updateTrangThaiHoaDon(trangThaiHoaDon, idhd, "OK");
        return "redirect:/admin/psg/hoa-don/lich-su-hoa-don/" + idhd;
    }

}
