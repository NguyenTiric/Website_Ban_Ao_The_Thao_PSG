package com.example.website_ban_ao_the_thao_psg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/psg")
public class HomeController {

    @GetMapping("/hoa-don")
    public String hoaDon(){
        return "admin/hoa_don/hoa_don_detail";
    }

    @GetMapping("/san-pham")
    public String sanPham() {
        return "admin/san_pham/trang_chu_san_pham";
    }

    @GetMapping("/viewadd")
    public String viewAdd(){
        return "admin/san_pham/view_add_san_pham";
    }
}
