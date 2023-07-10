package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KichThuocResponse;
import com.example.website_ban_ao_the_thao_psg.service.KichThuocService;
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
@RequestMapping("/admin/psg/kich-thuoc")
public class KichThuocController {
    @Autowired
    KichThuocService kichThuocService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("kichThuoc", new KichThuoc());
        return pageKichThuocActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageKichThuocActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("kichThuoc", new KichThuoc());
        Page<KichThuocResponse> kichThuocResponsePageActive = kichThuocService.pageKichThuocActive(pageNo, 3);
        model.addAttribute("size", kichThuocResponsePageActive.getSize());
        model.addAttribute("totalPages", kichThuocResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKichThuocActive", kichThuocResponsePageActive);
        return "admin/kich_thuoc/trang_chu_kich_thuoc";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageKichThuocInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<KichThuocResponse> kichThuocResponsePageInActive = kichThuocService.pageKichThuocInActive(pageNo, 3);
        model.addAttribute("size", kichThuocResponsePageInActive.getSize());
        model.addAttribute("totalPages", kichThuocResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKichThuocInActive", kichThuocResponsePageInActive);
        return "admin/kich_thuoc/revert_kich_thuoc";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        KichThuocResponse kichThuocResponse = kichThuocService.getOne(id);
        model.addAttribute("kichThuoc", kichThuocResponse);
        return "admin/kich_thuoc/view_update_kich_thuoc";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("kichThuoc", new CreateKichThuocRequest());
        return "admin/kich_thuoc/view_add_kich_thuoc";
    }
    @PostMapping("/delete/{id}")
    public String deleteKichThuoc(@PathVariable("id") Integer id,HttpSession session) {
        kichThuocService.deleteKichThuoc(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/kich-thuoc/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertKichThuoc(@PathVariable("id") Integer id,HttpSession session) {
        kichThuocService.revertKichThuoc(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/kich-thuoc/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("kichThuoc") CreateKichThuocRequest createKichThuocRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("kichThuoc", createKichThuocRequest);
            return "admin/kich_thuoc/view_add_kich_thuoc";
        }
        kichThuocService.add(createKichThuocRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/kich-thuoc/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("kichThuoc") UpdateKichThuocRequest updateKichThuocRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("kichThuoc", updateKichThuocRequest);
            return "admin/kich_thuoc/view_update_kich_thuoc";
        }
        kichThuocService.update(updateKichThuocRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/kich-thuoc/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("kichThuoc", new KichThuoc());
        Page<KichThuocResponse> kichThuocResponsePage = kichThuocService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", kichThuocResponsePage.getSize());
        model.addAttribute("totalPages", kichThuocResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKichThuocActive", kichThuocResponsePage);
        return "admin/kich_thuoc/trang_chu_kich_thuoc";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("kichThuoc", new KichThuoc());
        Page<KichThuocResponse> kichThuocResponsePage = kichThuocService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", kichThuocResponsePage.getSize());
        model.addAttribute("totalPages", kichThuocResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKichThuocInActive", kichThuocResponsePage);
        return "admin/kich_thuoc/revert_kich_thuoc";
    }
}
