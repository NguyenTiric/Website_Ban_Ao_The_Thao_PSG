package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.CauThu;
import com.example.website_ban_ao_the_thao_psg.entity.ChiTietSanPham;
import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.entity.SanPham;
import com.example.website_ban_ao_the_thao_psg.entity.ThuongHieu;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.CauThuResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.KichThuocResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuongHieuResponse;
import com.example.website_ban_ao_the_thao_psg.service.AnhSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.CauThuService;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.KichThuocService;
import com.example.website_ban_ao_the_thao_psg.service.TrangChuService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user/psg/trang-chu")
public class TrangChuController {
    @Autowired
    private TrangChuService trangChuService;

    @Autowired
    private KichThuocService kichThuocService;

    @Autowired
    private CauThuService cauThuService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) throws SQLException {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        return cauThiHienThi( model);
    }

    @GetMapping("")
    public String cauThiHienThi( Model model) throws SQLException {
//       model.addAttribute("listCauThu",anhSanPhamService.getAll());
        return "user/trang_chu";
    }

//    @GetMapping("/anhSp")
//    public ResponseEntity<byte[]> displayAnhSp(@RequestParam("anhCt") Integer id) throws IOException, SQLException {
//        List<AnhSanPhamResponse> anhSanPhamResponse = anhSanPhamService.anhSanPhamResponseList(id);
//
//        if (!anhSanPhamResponse.isEmpty()) {
//            AnhSanPhamResponse firstAnhSanPham = anhSanPhamResponse.get(0);
//            byte[] imageBytes = firstAnhSanPham.getTen().getBytes(1, (int) firstAnhSanPham.getTen().length());
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//        } else {
//            // Xử lý trường hợp không có ảnh hoặc id không hợp lệ
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping("/cau-thu/{id}")
    public String listCauThuSanPham(@PathVariable("id") Integer id,Model model){

       List<SanPhamResponse> listSanPham=trangChuService.danhSachSanPhamCauThu(id);
       model.addAttribute("listSanPham",listSanPham);
        return "user/san_pham";
    }
    @GetMapping("/chi-tiet-san-pham/{idSP}")
    public String layRaChiTietSanPham(@PathVariable("idSP") Integer id,Model model){
        // lấy ra sản phẩm
        List<ChiTietSanPhamResponse> listChiTietSanPham = trangChuService.getChiTietSanPhamBySanPham(id);
        model.addAttribute("listChiTietSanPham",listChiTietSanPham);

        List<KichThuocResponse> kichThuocResponses=kichThuocService.getALl();
        model.addAttribute("listKichThuoc",kichThuocResponses);
        return "user/chi_tiet_san_pham";
    }


}
