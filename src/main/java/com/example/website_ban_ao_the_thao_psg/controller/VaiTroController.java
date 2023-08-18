package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVaiTroRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVaiTroRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.VaiTroResponse;
import com.example.website_ban_ao_the_thao_psg.service.MauSacService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
@Controller
@RequestMapping("/admin/psg/vai-tro")
public class VaiTroController {
    @Autowired
    VaiTroService vaiTroService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("vaiTro", new VaiTro());
        return pageVaiTroActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageVaiTroActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("vaiTro", new VaiTro());
        Page<VaiTroResponse> vaiTroResponsePageActive = vaiTroService.pageVaiTroActive(pageNo, 3);
        model.addAttribute("size", vaiTroResponsePageActive.getSize());
        model.addAttribute("totalPages", vaiTroResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacActive", vaiTroResponsePageActive);
        return "admin/vai_tro/trang_chu_vai_tro";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageMauSacInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<VaiTroResponse> vaiTroResponsePageInActive = vaiTroService.pageVaiTroInActive(pageNo, 3);
        model.addAttribute("size", vaiTroResponsePageInActive.getSize());
        model.addAttribute("totalPages", vaiTroResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listVaiTroInActive", vaiTroResponsePageInActive);
        return "admin/vai_tro/revert_vai_tro";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        VaiTroResponse vaiTroResponse = vaiTroService.getOne(id);
        model.addAttribute("vaiTro", vaiTroResponse);
        return "admin/vai_tro/view_update_vai_tro";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("vaiTro", new CreateVaiTroRequest());
        return "admin/vai_tro/view_add_vai_tro";
    }
    @PostMapping("/delete/{id}")
    public String deleteMauSac(@PathVariable("id") Integer id,HttpSession session) {
        vaiTroService.deleteVaiTro(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/vai-tro/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertMauSac(@PathVariable("id") Integer id,HttpSession session) {
        vaiTroService.revertVaiTro(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/vai-tro/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("vaiTro") CreateVaiTroRequest createVaiTroRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("vaiTro", createVaiTroRequest);
            return "admin/vai_tro/view_add_vai_tro";
        }
        vaiTroService.add(createVaiTroRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/vai-tro/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("vaiTro") UpdateVaiTroRequest updateVaiTroRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("vaiTro", updateVaiTroRequest);
            return "admin/vai_tro/view_update_vai_tro";
        }
        vaiTroService.update(updateVaiTroRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/vai-tro/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("vaiTro", new VaiTro());
        Page<VaiTroResponse> vaiTroResponsePage = vaiTroService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", vaiTroResponsePage.getSize());
        model.addAttribute("totalPages", vaiTroResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacActive", vaiTroResponsePage);
        return "admin/vai_tro/trang_chu_vai_tro";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("vaiTro", new VaiTro());
        Page<VaiTroResponse> vaiTroResponsePage = vaiTroService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", vaiTroResponsePage.getSize());
        model.addAttribute("totalPages", vaiTroResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacInActive", vaiTroResponsePage);
        return "admin/vai_tro/revert_vai_tro";
    }
}
