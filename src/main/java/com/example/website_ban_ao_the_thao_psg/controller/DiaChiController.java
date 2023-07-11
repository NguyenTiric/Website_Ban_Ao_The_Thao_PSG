package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.DiaChi;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DiaChiResponse;
import com.example.website_ban_ao_the_thao_psg.service.DiaChiService;
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
@RequestMapping("/admin/psg/dia-chi")
public class DiaChiController {

    @Autowired
    DiaChiService diaChiService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("diaChi", new DiaChi());
        return pageDiaChiActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageDiaChiActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("diaChi", new DiaChi());
        Page<DiaChiResponse> diaChiResponsePageActive = diaChiService.pageDiaChiActive(pageNo, 3);
        model.addAttribute("size", diaChiResponsePageActive.getSize());
        model.addAttribute("totalPages", diaChiResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDiaChiActive", diaChiResponsePageActive);
        return "admin/trang_chu_dia_chi";
    }
    @GetMapping("/pageInActive/{pageNo}")
    public String pageDiaChiInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("diaChi", new DiaChi());
        Page<DiaChiResponse> diaChiResponsePageInActive = diaChiService.pageDiaChiInActive(pageNo, 3);
        model.addAttribute("size", diaChiResponsePageInActive.getSize());
        model.addAttribute("totalPages", diaChiResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDiaChiInActive", diaChiResponsePageInActive);
        return "admin/trang_chu_dia_chi";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        DiaChiResponse diaChiResponse = diaChiService.getOne(id);
        model.addAttribute("diaChiUpdate", diaChiResponse);
        return "admin/trang_chu_dia_chi";
    }
    @PostMapping("/delete/{id}")
    public String deleteDiaChi(@PathVariable("id") Integer id) {
        diaChiService.deleteDiaChi(id, LocalDate.now());
        return "redirect:/admin/psg/dia-chi/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertDiaChi(@PathVariable("id") Integer id) {
        diaChiService.revertDiaChi(id, LocalDate.now());
        return "redirect:/admin/psg/dia-chi/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("diaChi") CreateDiaChiRequest createDiaChiRequest, BindingResult result, Model model) {
        model.addAttribute("diaChi", createDiaChiRequest);
        diaChiService.add(createDiaChiRequest);
        return "redirect:/admin/psg/dia-chi/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("diaChi") UpdateDiaChiRequest updateDiaChiRequest, BindingResult result, Model model) {
        model.addAttribute("diaChi", updateDiaChiRequest);
        diaChiService.update(updateDiaChiRequest);
        return "redirect:/admin/psg/dia-chi/hien-thi";
    }

    @GetMapping("/search/{pageNo}")
    public String search(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("diaChi", new DiaChi());
        Page<DiaChiResponse> diaChiResponsePage = diaChiService.searchNameOrMa(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", diaChiResponsePage.getSize());
        model.addAttribute("totalPages", diaChiResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDiaChi", diaChiResponsePage);
        return "admin/trang_chu_dia_chi";
    }


}
