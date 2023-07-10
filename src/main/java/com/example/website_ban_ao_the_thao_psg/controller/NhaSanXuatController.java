package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.NhaSanXuat;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NhaSanXuatResponse;
import com.example.website_ban_ao_the_thao_psg.service.NhaSanXuatService;
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
@RequestMapping("/admin/psg/nha-san-xuat")
public class NhaSanXuatController {
    @Autowired
    NhaSanXuatService nhaSanXuatService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("nhaSanXuat", new NhaSanXuat());
        return pageNhaSanXuatActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageNhaSanXuatActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nhaSanXuat", new NhaSanXuat());
        Page<NhaSanXuatResponse> nhaSanXuatResponsePageActive = nhaSanXuatService.pageNhaSanXuatActive(pageNo, 3);
        model.addAttribute("size", nhaSanXuatResponsePageActive.getSize());
        model.addAttribute("totalPages", nhaSanXuatResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhaSanXuatActive", nhaSanXuatResponsePageActive);
        return "admin/nha_san_xuat/trang_chu_nha_san_xuat";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageNhaSanXuatInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<NhaSanXuatResponse> nhaSanXuatResponsePageInActive = nhaSanXuatService.pageNhaSanXuatInActive(pageNo, 3);
        model.addAttribute("size", nhaSanXuatResponsePageInActive.getSize());
        model.addAttribute("totalPages", nhaSanXuatResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhaSanXuatInActive", nhaSanXuatResponsePageInActive);
        return "admin/nha_san_xuat/revert_nha_san_xuat";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        NhaSanXuatResponse nhaSanXuatResponse = nhaSanXuatService.getOne(id);
        model.addAttribute("nhaSanXuat", nhaSanXuatResponse);
        return "admin/nha_san_xuat/view_update_nha_san_xuat";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("nhaSanXuat", new CreateNhaSanXuatRequest());
        return "admin/nha_san_xuat/view_add_nha_san_xuat";
    }
    @PostMapping("/delete/{id}")
    public String deleteNhaSanXuat(@PathVariable("id") Integer id,HttpSession session) {
        nhaSanXuatService.deleteNhaSanXuat(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/nha-san-xuat/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertNhaSanXuat(@PathVariable("id") Integer id,HttpSession session) {
        nhaSanXuatService.revertNhaSanXuat(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/nha-san-xuat/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nhaSanXuat") CreateNhaSanXuatRequest createNhaSanXuatRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("nhaSanXuat", createNhaSanXuatRequest);
            return "admin/nha_san_xuat/view_add_nha_san_xuat";
        }
        nhaSanXuatService.add(createNhaSanXuatRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/nha-san-xuat/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nhaSanXuat") UpdateNhaSanXuatRequest updateNhaSanXuatRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("nhaSanXuat", updateNhaSanXuatRequest);
            return "admin/nha_san_xuat/view_update_nha_san_xuat";
        }
        nhaSanXuatService.update(updateNhaSanXuatRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/nha-san-xuat/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("nhaSanXuat", new NhaSanXuat());
        Page<NhaSanXuatResponse> nhaSanXuatResponsePage = nhaSanXuatService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", nhaSanXuatResponsePage.getSize());
        model.addAttribute("totalPages", nhaSanXuatResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhaSanXuatActive", nhaSanXuatResponsePage);
        return "admin/nha_san_xuat/trang_chu_nha_san_xuat";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("nhaSanXuat", new NhaSanXuat());
        Page<NhaSanXuatResponse> nhaSanXuatResponsePage = nhaSanXuatService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", nhaSanXuatResponsePage.getSize());
        model.addAttribute("totalPages", nhaSanXuatResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhaSanXuatInActive", nhaSanXuatResponsePage);
        return "admin/nha_san_xuat/revert_nha_san_xuat";
    }
}
