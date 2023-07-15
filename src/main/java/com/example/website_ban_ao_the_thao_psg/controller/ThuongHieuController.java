package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.ThuongHieu;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuongHieuResponse;
import com.example.website_ban_ao_the_thao_psg.service.ThuongHieuService;
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
@RequestMapping("/admin/psg/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("thuongHieu", new ThuongHieu());
        return pageThuongHieuActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageThuongHieuActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("thuongHieu", new ThuongHieu());
        Page<ThuongHieuResponse> thuongHieuResponsePageActive = thuongHieuService.pageThuongHieuActive(pageNo, 3);
        model.addAttribute("size", thuongHieuResponsePageActive.getSize());
        model.addAttribute("totalPages", thuongHieuResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuongHieuActive", thuongHieuResponsePageActive);
        return "admin/thuong_hieu/trang_chu_thuong_hieu";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageThuongHieuInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<ThuongHieuResponse> thuongHieuResponsePageInActive = thuongHieuService.pageThuongHieuInActive(pageNo, 3);
        model.addAttribute("size", thuongHieuResponsePageInActive.getSize());
        model.addAttribute("totalPages", thuongHieuResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuongHieuInActive", thuongHieuResponsePageInActive);
        return "admin/thuong_hieu/revert_thuong_hieu";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        ThuongHieuResponse thuongHieuResponse = thuongHieuService.getOne(id);
        model.addAttribute("thuongHieu", thuongHieuResponse);
        return "admin/thuong_hieu/view_update_thuong_hieu";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("thuongHieu", new CreateThuongHieuRequest());
        return "admin/thuong_hieu/view_add_thuong_hieu";
    }
    @PostMapping("/delete/{id}")
    public String deleteThuongHieu(@PathVariable("id") Integer id,HttpSession session) {
        thuongHieuService.deleteThuongHieu(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/thuong-hieu/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertThuongHieu(@PathVariable("id") Integer id,HttpSession session) {
        thuongHieuService.revertThuongHieu(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/thuong-hieu/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("thuongHieu") CreateThuongHieuRequest createThuongHieuRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("thuongHieu", createThuongHieuRequest);
            return "admin/thuong_hieu/view_add_thuong_hieu";
        }
        thuongHieuService.add(createThuongHieuRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/thuong-hieu/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("thuongHieu") UpdateThuongHieuRequest updateThuongHieuRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("thuongHieu", updateThuongHieuRequest);
            return "admin/thuong_hieu/view_update_thuong_hieu";
        }
        thuongHieuService.update(updateThuongHieuRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/thuong-hieu/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("thuongHieu", new ThuongHieu());
        Page<ThuongHieuResponse> thuongHieuResponsePage = thuongHieuService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", thuongHieuResponsePage.getSize());
        model.addAttribute("totalPages", thuongHieuResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuongHieuActive", thuongHieuResponsePage);
        return "admin/thuong_hieu/trang_chu_thuong_hieu";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("thuongHieu", new ThuongHieu());
        Page<ThuongHieuResponse> thuongHieuResponsePage = thuongHieuService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", thuongHieuResponsePage.getSize());
        model.addAttribute("totalPages", thuongHieuResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuongHieuInActive", thuongHieuResponsePage);
        return "admin/thuong_hieu/revert_thuong_hieu";
    }
}
