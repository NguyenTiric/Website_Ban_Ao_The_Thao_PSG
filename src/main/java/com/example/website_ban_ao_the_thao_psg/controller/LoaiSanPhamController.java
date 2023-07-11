package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.LoaiSanPham;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LoaiSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.LoaiSanPhamService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/psg/loai-san-pham")
public class LoaiSanPhamController {
    @Autowired
    LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("loaiSanPham", new LoaiSanPham());
        return pageLoaiSanPhamActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageLoaiSanPhamActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("loaiSanPham", new LoaiSanPham());
        Page<LoaiSanPhamResponse> loaiSanPhamResponsePageActive = loaiSanPhamService.pageLoaiSanPhamActive(pageNo, 3);
        model.addAttribute("size", loaiSanPhamResponsePageActive.getSize());
        model.addAttribute("totalPages", loaiSanPhamResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listLoaiSanPhamActive", loaiSanPhamResponsePageActive);
        return "admin/loai_san_pham/trang_chu_loai_san_pham";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageLoaiSanPhamInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<LoaiSanPhamResponse> loaiSanPhamResponsePageInActive = loaiSanPhamService.pageLoaiSanPhamInActive(pageNo, 3);
        model.addAttribute("size", loaiSanPhamResponsePageInActive.getSize());
        model.addAttribute("totalPages", loaiSanPhamResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listLoaiSanPhamInActive", loaiSanPhamResponsePageInActive);
        return "admin/loai_san_pham/revert_loai_san_pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        LoaiSanPhamResponse loaiSanPhamResponse = loaiSanPhamService.getOne(id);
        model.addAttribute("loaiSanPham", loaiSanPhamResponse);
        return "admin/loai_san_pham/view_update_loai_san_pham";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("loaiSanPham", new CreateLoaiSanPhamRequest());
        return "admin/loai_san_pham/view_add_loai_san_pham";
    }
    @PostMapping("/delete/{id}")
    public String deleteLoaiSanPham(@PathVariable("id") Integer id,HttpSession session) {
        loaiSanPhamService.deleteLoaiSanPham(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/loai-san-pham/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertLoaiSanPham(@PathVariable("id") Integer id,HttpSession session) {
        loaiSanPhamService.revertLoaiSanPham(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/loai-san-pham/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("loaiSanPham") CreateLoaiSanPhamRequest createLoaiSanPhamRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("loaiSanPham", createLoaiSanPhamRequest);
            return "admin/loai_san_pham/view_add_loai_san_pham";
        }
        loaiSanPhamService.add(createLoaiSanPhamRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/loai-san-pham/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("loaiSanPham") UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("loaiSanPham", updateLoaiSanPhamRequest);
            return "admin/loai_san_pham/view_update_loai_san_pham";
        }
        loaiSanPhamService.update(updateLoaiSanPhamRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/loai-san-pham/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("loaiSanPham", new LoaiSanPham());
        Page<LoaiSanPhamResponse> loaiSanPhamResponsePage = loaiSanPhamService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", loaiSanPhamResponsePage.getSize());
        model.addAttribute("totalPages", loaiSanPhamResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listLoaiSanPhamActive", loaiSanPhamResponsePage);
        return "admin/loai_san_pham/trang_chu_loai_san_pham";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("loaiSanPham", new LoaiSanPham());
        Page<LoaiSanPhamResponse> loaiSanPhamResponsePage = loaiSanPhamService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", loaiSanPhamResponsePage.getSize());
        model.addAttribute("totalPages", loaiSanPhamResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listLoaiSanPhamInActive", loaiSanPhamResponsePage);
        return "admin/loai_san_pham/revert_loai_san_pham";
    }
}
