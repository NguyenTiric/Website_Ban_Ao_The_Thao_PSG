package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonChiTietResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.HoaDonResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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
        model.addAttribute("listSanPhamActive",chiTietSanPhamService.getAllSanPhamActive());
        return "admin/hoa_don/hoa_don_cho";
    }

    @GetMapping("/detail-hoa-don/{id}")
    public String hoaDonDetail(@PathVariable("id") Integer id, Model model) {
        HoaDonResponse hoaDonResponse = hoaDonService.getDetailHoaDon(id);
        List<SanPhamResponse> sanPhamResponseList = chiTietSanPhamService.getAllSP();
        model.addAttribute("hoaDon", hoaDonResponse);
        model.addAttribute("listSanPham", sanPhamResponseList);
        model.addAttribute("listSanPhamActive",chiTietSanPhamService.getAllSanPhamActive());
        return "admin/hoa_don/hoa_don_detail";
    }
    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        return pageHoaDonActive(0, model);
    }
    @GetMapping("/pageActive/{pageNo}")
    public String pageHoaDonActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<HoaDonResponse> hoaDonResponsePageActive = hoaDonService.pageHoaDon(pageNo, 10);

        // Định dạng tiền tệ Việt Nam
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        for (HoaDonResponse hd : hoaDonResponsePageActive) {
            BigDecimal thanhTien = hd.getThanhTien();
            String formattedThanhTien = currencyFormat.format(thanhTien);
            hd.setFormattedThanhTien((formattedThanhTien));
        }

        model.addAttribute("size", hoaDonResponsePageActive.getSize());
        model.addAttribute("totalPages", hoaDonResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listHoaDon", hoaDonResponsePageActive);

        return "admin/hoa_don/hoa_don";
    }

    @GetMapping("/gio-hang/{id}")
    public String gioHang(@PathVariable("id") Integer id, Model model) {
        List<HoaDonChiTietResponse> listGioHang = hoaDonService.getAllHoaDonChiTiet(id);
        model.addAttribute("listHoaDonCho", hoaDonService.getAllHoaDonCho());
        model.addAttribute("listGioHang", listGioHang);
        model.addAttribute("listSanPhamActive",chiTietSanPhamService.getAllSanPhamActive());
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


    @GetMapping("/searchHoaDon/{pageNo}")
    public String searchHoaDon(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam("pathSearch") String search) {
        Page<HoaDonResponse> hoaDonResponsePageActive = hoaDonService.pageSearchHoaDon(pageNo, 10,search);

        // Định dạng tiền tệ Việt Nam
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        for (HoaDonResponse hd : hoaDonResponsePageActive) {
            BigDecimal thanhTien = hd.getThanhTien();
            String formattedThanhTien = currencyFormat.format(thanhTien);
            hd.setFormattedThanhTien((formattedThanhTien));
        }

        model.addAttribute("size", hoaDonResponsePageActive.getSize());
        model.addAttribute("totalPages", hoaDonResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listHoaDon", hoaDonResponsePageActive);

        return "admin/hoa_don/hoa_don";
    }

    @GetMapping("/searchDate/{pageNo}")
    public String searchDate(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam(value = "beginDate",required = false) LocalDate beginDate
       ,@RequestParam(value = "endDate",required = false) LocalDate endDate) {
        Page<HoaDonResponse> hoaDonResponsePageActive = hoaDonService.pageSearchHoaDonBetweenDates(pageNo, 10,beginDate,endDate);

        // Định dạng tiền tệ Việt Nam
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        for (HoaDonResponse hd : hoaDonResponsePageActive) {
            BigDecimal thanhTien = hd.getThanhTien();
            String formattedThanhTien = currencyFormat.format(thanhTien);
            hd.setFormattedThanhTien((formattedThanhTien));
        }

        model.addAttribute("size", hoaDonResponsePageActive.getSize());
        model.addAttribute("totalPages", hoaDonResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listHoaDon", hoaDonResponsePageActive);

        return "admin/hoa_don/hoa_don";
    }
    @PostMapping("/update-trang-thai-hoa-don/{id}")
    public String updateTrangThaiHoaDon(@PathVariable("id") Integer idhd, @RequestParam("trangThai") ApplicationConstant.TrangThaiHoaDon trangThaiHoaDon) {
        hoaDonService.updateTrangThaiHoaDon(trangThaiHoaDon, idhd, "OK");
        return "redirect:/admin/psg/hoa-don/lich-su-hoa-don/" + idhd;
    }

}
