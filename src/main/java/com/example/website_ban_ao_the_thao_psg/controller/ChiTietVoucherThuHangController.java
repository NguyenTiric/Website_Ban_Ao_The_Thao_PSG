package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.ChiTietVoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChiTietVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChiTietVoucherThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.VoucherThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietVoucherThuHangService;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import com.example.website_ban_ao_the_thao_psg.service.VoucherThuHangService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/psg/chi-tiet-voucher-thu-hang")
public class ChiTietVoucherThuHangController {

    @Autowired
    private ChiTietVoucherThuHangService chiTietVoucherThuHangService;

    @Autowired
    private ThuHangService thuHangService;

    @Autowired
    private VoucherThuHangService voucherThuHangService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("thuHang", new ThuHang());
        model.addAttribute("voucher", new VoucherThuHang());
        model.addAttribute("chiTietVoucherThuHang", new ChiTietVoucherThuHang());
        return pageChiTietVoucherThuHangActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageChiTietVoucherThuHangActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("chiTietVoucherThuHang", new ChiTietVoucherThuHang());
        Page<ChiTietVoucherThuHangResponse> chiTietVoucherThuHangResponsePageActive = chiTietVoucherThuHangService.pageChiTietVoucherThuHangActive(pageNo, 3);
        model.addAttribute("size", chiTietVoucherThuHangResponsePageActive.getSize());
        model.addAttribute("totalPages", chiTietVoucherThuHangResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChiTietVoucherThuHangActive", chiTietVoucherThuHangResponsePageActive);
        return "admin/chi_tiet_voucher_thu_hang/trang_chu_chi_tiet_voucher_thu_hang";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageChiTietVoucherThuHangInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<ChiTietVoucherThuHangResponse> chiTietVoucherThuHangResponsePageInActive = chiTietVoucherThuHangService.pageChiTietVoucherThuHangInActive(pageNo, 3);
        model.addAttribute("size", chiTietVoucherThuHangResponsePageInActive.getSize());
        model.addAttribute("totalPages", chiTietVoucherThuHangResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChiTietVoucherThuHangInActive", chiTietVoucherThuHangResponsePageInActive);
        return "admin/chi_tiet_voucher_thu_hang/revert_chi_tiet_voucher_thu_hang";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        ChiTietVoucherThuHangResponse chiTietVoucherThuHangResponse = chiTietVoucherThuHangService.getOne(id);
        model.addAttribute("chiTietVoucherThuHang", chiTietVoucherThuHangResponse);
        return "admin/chi_tiet_voucher_thu_hang/view_update_chi_tiet_voucher_thu_hang";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("thuHang", new ThuHang());
        model.addAttribute("voucher", new VoucherThuHang());
        model.addAttribute("chiTietVoucherThuHang", new CreateChiTietVoucherThuHangRequest());

        model.addAttribute("listThuHang", this.thuHangService.getAll());
        model.addAttribute("listVoucher", this.voucherThuHangService.getAll());
        return "admin/chi_tiet_voucher_thu_hang/view_add_chi_tiet_voucher_thu_hang";
    }
    @PostMapping("/delete/{id}")
    public String deleteChiTietVoucherThuHang(@PathVariable("id") Integer id,HttpSession session) {
        chiTietVoucherThuHangService.deleteChiTietVoucherThuHang(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/chi-tiet-voucher-thu-hang/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertChiTietVoucherThuHang(@PathVariable("id") Integer id,HttpSession session) {
        chiTietVoucherThuHangService.revertChiTietVoucherThuHang(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/chi-tiet-voucher-thu-hang/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("chiTietVoucherThuHang") CreateChiTietVoucherThuHangRequest createChiTietVoucherThuHangRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("chiTietVoucherThuHang", createChiTietVoucherThuHangRequest);
            return "admin/chi_tiet_voucher_thu_hang/view_add_chi_tiet_voucher_thu_hang";
        }
        chiTietVoucherThuHangService.add(createChiTietVoucherThuHangRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/chi-tiet-voucher-thu-hang/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("chiTietVoucherThuHang") UpdateChiTietVoucherThuHangRequest updateChiTietVoucherThuHangRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("chiTietVoucherThuHang", updateChiTietVoucherThuHangRequest);
            return "admin/chi_tiet_voucher_thu_hang/view_update_chi_tiet_voucher_thu_hang";
        }
        chiTietVoucherThuHangService.update(updateChiTietVoucherThuHangRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/chi-tiet-voucher-thu-hang/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("chiTietVoucherThuHang", new ChiTietVoucherThuHang());
        Page<ChiTietVoucherThuHangResponse> chiTietVoucherThuHangResponsePage = chiTietVoucherThuHangService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", chiTietVoucherThuHangResponsePage.getSize());
        model.addAttribute("totalPages", chiTietVoucherThuHangResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChiTietVoucherThuHangActive", chiTietVoucherThuHangResponsePage);
        return "admin/chi_tiet_voucher_thu_hang/trang_chu_chi_tiet_voucher_thu_hang";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("chiTietVoucherThuHang", new ChiTietVoucherThuHang());
        Page<ChiTietVoucherThuHangResponse> chiTietVoucherThuHangResponsePage = chiTietVoucherThuHangService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", chiTietVoucherThuHangResponsePage.getSize());
        model.addAttribute("totalPages", chiTietVoucherThuHangResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChiTietVoucherThuHangInActive", chiTietVoucherThuHangResponsePage);
        return "admin/chi_tiet_voucher_thu_hang/revert_chi_tiet_voucher_thu_hang";
    }
}
