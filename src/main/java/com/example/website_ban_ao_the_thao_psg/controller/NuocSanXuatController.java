package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.NuocSanXuat;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NuocSanXuatResponse;
import com.example.website_ban_ao_the_thao_psg.service.NuocSanXuatService;
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
@RequestMapping("/admin/psg/nuoc-san-xuat")
public class NuocSanXuatController {
    @Autowired
    NuocSanXuatService nuocSanXuatService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("nuocSanXuat", new NuocSanXuat());
        return pageNuocSanXuatActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageNuocSanXuatActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nuocSanXuat", new NuocSanXuat());
        Page<NuocSanXuatResponse> nuocSanXuatResponsePageActive = nuocSanXuatService.pageNuocSanXuatActive(pageNo, 3);
        model.addAttribute("size", nuocSanXuatResponsePageActive.getSize());
        model.addAttribute("totalPages", nuocSanXuatResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNuocSanXuatActive", nuocSanXuatResponsePageActive);
        return "admin/nuoc_san_xuat/trang_chu_nuoc_san_xuat";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageNuocSanXuatInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<NuocSanXuatResponse> nuocSanXuatResponsePageInActive = nuocSanXuatService.pageNuocSanXuatInActive(pageNo, 3);
        model.addAttribute("size", nuocSanXuatResponsePageInActive.getSize());
        model.addAttribute("totalPages", nuocSanXuatResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNuocSanXuatInActive", nuocSanXuatResponsePageInActive);
        return "admin/nuoc_san_xuat/revert_nuoc_san_xuat";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        NuocSanXuatResponse nuocSanXuatResponse = nuocSanXuatService.getOne(id);
        model.addAttribute("nuocSanXuat", nuocSanXuatResponse);
        return "admin/nuoc_san_xuat/view_update_nuoc_san_xuat";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("nuocSanXuat", new CreateNuocSanXuatRequest());
        return "admin/nuoc_san_xuat/view_add_nuoc_san_xuat";
    }
    @PostMapping("/delete/{id}")
    public String deleteNuocSanXuat(@PathVariable("id") Integer id,HttpSession session) {
        nuocSanXuatService.deleteNuocSanXuat(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/nuoc-san-xuat/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertNuocSanXuat(@PathVariable("id") Integer id,HttpSession session) {
        nuocSanXuatService.revertNuocSanXuat(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/nuoc-san-xuat/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nuocSanXuat") CreateNuocSanXuatRequest createNuocSanXuatRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("nuocSanXuat", createNuocSanXuatRequest);
            return "admin/nuoc_san_xuat/view_add_nuoc_san_xuat";
        }
        nuocSanXuatService.add(createNuocSanXuatRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/nuoc-san-xuat/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nuocSanXuat") UpdateNuocSanXuatRequest updateNuocSanXuatRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("nuocSanXuat", updateNuocSanXuatRequest);
            return "admin/nuoc_san_xuat/view_update_nuoc_san_xuat";
        }
        nuocSanXuatService.update(updateNuocSanXuatRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/nuoc-san-xuat/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("nuocSanXuat", new NuocSanXuat());
        Page<NuocSanXuatResponse> nuocSanXuatResponsePage = nuocSanXuatService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", nuocSanXuatResponsePage.getSize());
        model.addAttribute("totalPages", nuocSanXuatResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNuocSanXuatActive", nuocSanXuatResponsePage);
        return "admin/nuoc_san_xuat/trang_chu_nuoc_san_xuat";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("nuocSanXuat", new NuocSanXuat());
        Page<NuocSanXuatResponse> nuocSanXuatResponsePage = nuocSanXuatService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", nuocSanXuatResponsePage.getSize());
        model.addAttribute("totalPages", nuocSanXuatResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNuocSanXuatInActive", nuocSanXuatResponsePage);
        return "admin/nuoc_san_xuat/revert_nuoc_san_xuat";
    }

}
