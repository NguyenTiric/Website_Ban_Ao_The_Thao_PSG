package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.service.HoaDonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/admin/psg/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/hien-thi/{page}")
    public String paginationHoaDon(@PathVariable(name = "page", required = false) Integer pageNo, Model model) {
        Page<HoaDonResponse> hoaDonResponsePage = hoaDonService.pageAllHoaDon(pageNo, 5);
        model.addAttribute("size", hoaDonResponsePage.getSize());
        model.addAttribute("totalPages", hoaDonResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listHoaDon", hoaDonResponsePage);
        return "admin/hoa_don";
    }

    @GetMapping("/hien-thi")
    public String viewAllHoaDon(Model model) {
        Page<HoaDonResponse> hoaDonResponsePage = hoaDonService.pageAllHoaDon(0, 5);
        model.addAttribute("size", hoaDonResponsePage.getSize());
        model.addAttribute("totalPages", hoaDonResponsePage.getTotalPages());
        model.addAttribute("currentPage", 0);
        model.addAttribute("listHoaDon", hoaDonResponsePage);
        return "admin/hoa_don";
    }

    @GetMapping("/search")
    public String searchHoaDon(@RequestParam(name = "pathSearch", required = false) String pathName, Model model) {
        Page<HoaDonResponse> hoaDonResponsePage = hoaDonService.searchByAllRange(pathName, 0, 5);
        model.addAttribute("size", hoaDonResponsePage.getSize());
        model.addAttribute("totalPages", hoaDonResponsePage.getTotalPages());
        model.addAttribute("currentPage", 0);
        model.addAttribute("listHoaDon", hoaDonResponsePage);
        return "admin/hoa_don";
    }

    @GetMapping("/search-by-date")
    public String searchDateHoaDon(@RequestParam(name = "beginDate") String beginDate, @RequestParam(name = "endDate") String endDate, Model model) throws ParseException {
        Page<HoaDonResponse> hoaDonResponsePage = hoaDonService.searchByDate(beginDate, endDate, 0, 5);
        model.addAttribute("size", hoaDonResponsePage.getSize());
        model.addAttribute("totalPages", hoaDonResponsePage.getTotalPages());
        model.addAttribute("currentPage", 0);
        model.addAttribute("listHoaDon", hoaDonResponsePage);
        return "admin/hoa_don";
    }

    @GetMapping("/cho/hien-thi")
    public String viewHoaDonCho(Model model) {
        Page<HoaDonResponse> hoaDonResponsePage = hoaDonService.pageHoaDonCho(0, 5);
        model.addAttribute("size", hoaDonResponsePage.getSize());
        model.addAttribute("totalPages", hoaDonResponsePage.getTotalPages());
        model.addAttribute("currentPage", 0);
        model.addAttribute("listHoaDonCho", hoaDonResponsePage);
        return "admin/hoa_don_cho";
    }

    @GetMapping("/detail/{id}")
    public String detailHoaDon(@PathVariable("id") Integer id, Model model) {
        HoaDonResponse hoaDonResponse = hoaDonService.getDetailHoaDon(id);
        model.addAttribute("hoaDon",hoaDonResponse);
        return "admin/don_hang";
    }
}
