package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateTaiKhoanRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import com.example.website_ban_ao_the_thao_psg.service.TaiKhoanService;
import com.example.website_ban_ao_the_thao_psg.service.VaiTroService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/psg/nhan-vien")
public class NhanVienController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    VaiTroService vaiTroService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("nhanVien", new TaiKhoan());
        return pageTaiKhoanActive(0, model);
    }
    @GetMapping("/pageActive/{pageNo}")
    public String pageTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = taiKhoanService.pageTaiKhoanActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("nhanVien", new CreateTaiKhoanRequest());
        model.addAttribute("vaiTro",vaiTroService.getAll());
        return "admin/nhan_vien/view_add_nhan_vien";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nhanVien") CreateTaiKhoanRequest createTaiKhoanRequest, BindingResult result,Model model){
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }
        taiKhoanService.add(createTaiKhoanRequest);
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id")Integer id,Model model) {
                TaiKhoanResponse tk=taiKhoanService.getOne(id);
        model.addAttribute("vaiTro",vaiTroService.getAll());
        model.addAttribute("nhanVien",tk);
        return "admin/nhan_vien/view_update_nhan_vien";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nhanVien") UpdateTaiKhoanRequest updateTaiKhoanRequest, BindingResult result,Model model){
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/nhan_vien/view_update_nhan_vien";
        }
        taiKhoanService.update(updateTaiKhoanRequest);
        System.out.println(updateTaiKhoanRequest.getNgaySinh());
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }
}
