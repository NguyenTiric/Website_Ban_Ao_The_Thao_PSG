package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import com.example.website_ban_ao_the_thao_psg.service.MauSacService;
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
@RequestMapping("/admin/psg/mau-sac")
public class MauSacController {

    @Autowired
    MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("mauSac", new MauSac());
        return pageMauSac(0, model);
    }

    @GetMapping("/page/{pageNo}")
    public String pageMauSac(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("mauSac", new MauSac());
        Page<MauSacResponse> mauSacResponsePage = mauSacService.pageMauSacResponse(pageNo, 3);
        model.addAttribute("size", mauSacResponsePage.getSize());
        model.addAttribute("totalPages", mauSacResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSac", mauSacResponsePage);
        return "admin/trang_chu_mau_sac";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        MauSacResponse mauSacResponse = mauSacService.getOne(id);
        model.addAttribute("mauSacUpdate", mauSacResponse);
        return "admin/trang_chu_mau_sac";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("mauSac") CreateMauSacRequest createMauSacRequest, BindingResult result, Model model) {
        model.addAttribute("mauSac", createMauSacRequest);
        mauSacService.add(createMauSacRequest);
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("mauSac") UpdateMauSacRequest updateMauSacRequest, BindingResult result, Model model) {
        model.addAttribute("mauSac", updateMauSacRequest);
        mauSacService.update(updateMauSacRequest);
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }


}
