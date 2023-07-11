package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.CauThu;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CauThuResponse;
import com.example.website_ban_ao_the_thao_psg.service.CauThuService;
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
@RequestMapping("/admin/psg/cau-thu")
public class CauThuController {
    @Autowired
    CauThuService cauThuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("cauThu", new CauThu());
        return pageCauThuActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageCauThuActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("cauThu", new CauThu());
        Page<CauThuResponse> cauThuResponsePageActive = cauThuService.pageCauThuActive(pageNo, 3);
        model.addAttribute("size", cauThuResponsePageActive.getSize());
        model.addAttribute("totalPages", cauThuResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCauThuActive", cauThuResponsePageActive);
        return "admin/cau_thu/trang_chu_cau_thu";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageCauThuInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<CauThuResponse> cauThuResponsePageInActive = cauThuService.pageCauThuInActive(pageNo, 3);
        model.addAttribute("size", cauThuResponsePageInActive.getSize());
        model.addAttribute("totalPages", cauThuResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCauThuInActive", cauThuResponsePageInActive);
        return "admin/cau_thu/revert_cau_thu";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        CauThuResponse cauThuResponse = cauThuService.getOne(id);
        model.addAttribute("cauThu", cauThuResponse);
        return "admin/cau_thu/view_update_cau_thu";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("cauThu", new CreateCauThuRequest());
        return "admin/cau_thu/view_add_cau_thu";
    }
    @PostMapping("/delete/{id}")
    public String deleteCauThu(@PathVariable("id") Integer id,HttpSession session) {
        cauThuService.deleteCauThu(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/cau-thu/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertCauThu(@PathVariable("id") Integer id,HttpSession session) {
        cauThuService.revertCauThu(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/cau-thu/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("cauThu") CreateCauThuRequest createCauThuRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("cauThu", createCauThuRequest);
            return "admin/cau_thu/view_add_cau_thu";
        }
        cauThuService.add(createCauThuRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/cau-thu/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("cauThu") UpdateCauThuRequest updateCauThuRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("cauThu", updateCauThuRequest);
            return "admin/cau_thu/view_update_cau_thu";
        }
        cauThuService.update(updateCauThuRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/cau-thu/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("cauThu", new CauThu());
        Page<CauThuResponse> cauThuResponsePage = cauThuService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", cauThuResponsePage.getSize());
        model.addAttribute("totalPages", cauThuResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCauThuActive", cauThuResponsePage);
        return "admin/cau_thu/trang_chu_cau_thu";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("cauThu", new CauThu());
        Page<CauThuResponse> cauThuResponsePage = cauThuService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", cauThuResponsePage.getSize());
        model.addAttribute("totalPages", cauThuResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCauThuInActive", cauThuResponsePage);
        return "admin/cau_thu/revert_cau_thu";
    }
}
