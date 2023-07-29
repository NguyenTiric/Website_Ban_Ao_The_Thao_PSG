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

    @GetMapping("/san-pham")
    public String sanPham() {
        return "admin/san_pham/trang_chu_san_pham";
    }
    @GetMapping("/voucher")
    public String voucher(){
        return "admin/voucher";
    }

    @GetMapping("/viewadd")
    public String viewAdd(){
        return "admin/san_pham/view_add_san_pham";
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
