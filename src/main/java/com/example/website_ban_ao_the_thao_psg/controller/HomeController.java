package com.example.website_ban_ao_the_thao_psg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/psg")
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "fragments/fragments_layout_admin";
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


    @GetMapping("/nhan-vien")
    public String nhanVien(){
        return "admin/nhan_vien";
    }

    @GetMapping("/khach-hang")
    public String khachHang(){
        return "admin/khach_hang";
    }
}
