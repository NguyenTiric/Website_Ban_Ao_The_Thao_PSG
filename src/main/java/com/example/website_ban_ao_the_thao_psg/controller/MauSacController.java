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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/psg/mau-sac")
public class MauSacController {

    @Autowired
    MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("mauSac", new MauSac());
        return pageMauSacActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageMauSacActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("mauSac", new MauSac());
        Page<MauSacResponse> mauSacResponsePageActive = mauSacService.pageMauSacActive(pageNo, 3);
        model.addAttribute("size", mauSacResponsePageActive.getSize());
        model.addAttribute("totalPages", mauSacResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacActive", mauSacResponsePageActive);
        return "admin/mau_sac/trang_chu_mau_sac";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageMauSacInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<MauSacResponse> mauSacResponsePageInActive = mauSacService.pageMauSacInActive(pageNo, 3);
        model.addAttribute("size", mauSacResponsePageInActive.getSize());
        model.addAttribute("totalPages", mauSacResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacInActive", mauSacResponsePageInActive);
        return "admin/mau_sac/revert_mau_sac";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        MauSacResponse mauSacResponse = mauSacService.getOne(id);
        model.addAttribute("mauSac", mauSacResponse);
        return "admin/mau_sac/view_update_mau_sac";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("mauSac", new CreateMauSacRequest());
        return "admin/mau_sac/view_add_mau_sac";
    }
    @PostMapping("/delete/{id}")
    public String deleteMauSac(@PathVariable("id") Integer id) {
        mauSacService.deleteMauSac(id, LocalDate.now());
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertMauSac(@PathVariable("id") Integer id) {
        mauSacService.revertMauSac(id, LocalDate.now());
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("mauSac") CreateMauSacRequest createMauSacRequest, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("mauSac", createMauSacRequest);
            return "admin/mau_sac/view_add_mau_sac";
        }
        model.addAttribute("mauSac", createMauSacRequest);
        mauSacService.add(createMauSacRequest);
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("mauSac") UpdateMauSacRequest updateMauSacRequest, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("mauSac", updateMauSacRequest);
            return "admin/mau_sac/view_update_mau_sac";
        }
        model.addAttribute("mauSac", updateMauSacRequest);
        mauSacService.update(updateMauSacRequest);
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("mauSac", new MauSac());
        Page<MauSacResponse> mauSacResponsePage = mauSacService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", mauSacResponsePage.getSize());
        model.addAttribute("totalPages", mauSacResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacActive", mauSacResponsePage);
        return "admin/mau_sac/trang_chu_mau_sac";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("mauSac", new MauSac());
        Page<MauSacResponse> mauSacResponsePage = mauSacService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", mauSacResponsePage.getSize());
        model.addAttribute("totalPages", mauSacResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacInActive", mauSacResponsePage);
        return "admin/mau_sac/revert_mau_sac";
    }

}
