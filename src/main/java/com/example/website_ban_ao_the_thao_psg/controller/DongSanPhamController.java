package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.DongSanPham;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DongSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.DongSanPhamService;
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
@RequestMapping("/admin/psg/dong-san-pham")
public class DongSanPhamController {

    @Autowired
    DongSanPhamService dongSanPhamService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("dongSanPham", new DongSanPham());
        return pageDongSanPhamActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageDongSanPhamActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("dongSanPham", new DongSanPham());
        Page<DongSanPhamResponse> dongSanPhamResponsePageActive = dongSanPhamService.pageDongSanPhamActive(pageNo, 3);
        model.addAttribute("size", dongSanPhamResponsePageActive.getSize());
        model.addAttribute("totalPages", dongSanPhamResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDongSanPhamActive", dongSanPhamResponsePageActive);
        return "admin/dong_san_pham/trang_chu_dong_san_pham";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageDongSanPhamInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<DongSanPhamResponse> dongSanPhamResponsePageInActive = dongSanPhamService.pageDongSanPhamInActive(pageNo, 3);
        model.addAttribute("size", dongSanPhamResponsePageInActive.getSize());
        model.addAttribute("totalPages", dongSanPhamResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDongSanPhamInActive", dongSanPhamResponsePageInActive);
        return "admin/dong_san_pham/revert_dong_san_pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        DongSanPhamResponse dongSanPhamResponse = dongSanPhamService.getOne(id);
        model.addAttribute("dongSanPham", dongSanPhamResponse);
        return "admin/dong_san_pham/view_update_dong_san_pham";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("dongSanPham", new CreateDongSanPhamRequest());
        return "admin/dong_san_pham/view_add_dong_san_pham";
    }
    @PostMapping("/delete/{id}")
    public String deleteDongSanPham(@PathVariable("id") Integer id,HttpSession session) {
        dongSanPhamService.deleteDongSanPham(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/dong-san-pham/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertDongSanPham(@PathVariable("id") Integer id,HttpSession session) {
        dongSanPhamService.revertDongSanPham(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/dong-san-pham/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("dongSanPham") CreateDongSanPhamRequest createDongSanPhamRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("dongSanPham", createDongSanPhamRequest);
            return "admin/dong_san_pham/view_add_dong_san_pham";
        }
        dongSanPhamService.add(createDongSanPhamRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/dong-san-pham/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dongSanPham") UpdateDongSanPhamRequest updateDongSanPhamRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("dongSanPham", updateDongSanPhamRequest);
            return "admin/dong_san_pham/view_update_dong_san_pham";
        }
        dongSanPhamService.update(updateDongSanPhamRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/dong-san-pham/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("dongSanPham", new DongSanPham());
        Page<DongSanPhamResponse> dongSanPhamResponsePage = dongSanPhamService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", dongSanPhamResponsePage.getSize());
        model.addAttribute("totalPages", dongSanPhamResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDongSanPhamActive", dongSanPhamResponsePage);
        return "admin/dong_san_pham/trang_chu_dong_san_pham";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("dongSanPham", new DongSanPham());
        Page<DongSanPhamResponse> dongSanPhamResponsePage = dongSanPhamService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", dongSanPhamResponsePage.getSize());
        model.addAttribute("totalPages", dongSanPhamResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDongSanPhamInActive", dongSanPhamResponsePage);
        return "admin/dong_san_pham/revert_dong_san_pham";
    }
}
