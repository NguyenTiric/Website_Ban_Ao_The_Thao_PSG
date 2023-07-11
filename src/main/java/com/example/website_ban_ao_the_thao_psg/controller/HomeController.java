package com.example.website_ban_ao_the_thao_psg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/psg")
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "admin/san_pham";
    }
    @GetMapping("/hoa-don")
    public String hoaDon(){
        return "admin/hoa_don";
    }

    @GetMapping("/thu-hang")
    public String thuHang() {
        return "admin/thu_hang";
    }
    @GetMapping("/voucher")
    public String voucher(){
        return "admin/voucher";
    }

    @GetMapping("/viewadd")
    public String viewAdd(){
        return "admin/san_pham/view_add_san_pham";
    }



}
