package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.QuyDinh;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateQuyDinhRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateQuyDinhRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.QuyDinhResponse;
import com.example.website_ban_ao_the_thao_psg.service.QuyDinhService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/psg/quy-dinh")
public class QuyDinhController {
    @Autowired
    private QuyDinhService quyDinhService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("quyDinh", new QuyDinh());
        return pageQuyDinhActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageQuyDinhActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("quyDinh", new QuyDinh());
        Page<QuyDinhResponse> quyDinhResponsePageActive = quyDinhService.pageQuyDinhActive(pageNo, 3);
        model.addAttribute("size", quyDinhResponsePageActive.getSize());
        model.addAttribute("totalPages", quyDinhResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listquyDinhActive", quyDinhResponsePageActive);
        return "admin/quy_dinh/trang_chu_quy_dinh";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageQuyDinhInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<QuyDinhResponse> quyDinhResponsePageInActive = quyDinhService.pageQuyDinhInActive(pageNo, 3);
        model.addAttribute("size", quyDinhResponsePageInActive.getSize());
        model.addAttribute("totalPages", quyDinhResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listQuyDinhInActive", quyDinhResponsePageInActive);
        return "admin/quy_dinh/revert_quy_dinh";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        QuyDinhResponse quyDinhResponse = quyDinhService.getOne(id);
        model.addAttribute("quyDinh", quyDinhResponse);
        return "admin/quy_dinh/view_update_quy_dinh";
    }
    @GetMapping("/view-add")
    public String viewAdd(@Valid @ModelAttribute("quyDinh") CreateQuyDinhRequest createQuyDinhRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("quyDinh", createQuyDinhRequest);
            return "admin/quy_dinh/view_add_quy_dinh";
        }
        model.addAttribute("quyDinh", new CreateQuyDinhRequest());
        return "admin/quy_dinh/view_add_quy_dinh";
    }
    @PostMapping("/delete/{id}")
    public String deletequyDinh(@PathVariable("id") Integer id,HttpSession session) {
        quyDinhService.deleteQuyDinh(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/quy-dinh/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertQuyDinh(@PathVariable("id") Integer id,HttpSession session) {
        quyDinhService.revertQuyDinh(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/quy-dinh/hien-thi";
    }

    @GetMapping("/add")
    public String add(@Valid @ModelAttribute("quyDinh") CreateQuyDinhRequest createQuyDinhRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("quyDinh", createQuyDinhRequest);
            return "admin/quy_dinh/view_add_quy_dinh";
        }
        quyDinhService.add(createQuyDinhRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/quy-dinh/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("quyDinh") UpdateQuyDinhRequest updateQuyDinhRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("quyDinh", updateQuyDinhRequest);
            return "admin/quy_dinh/view_update_quy_dinh";
        }
        quyDinhService.update(updateQuyDinhRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/quy-dinh/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("quyDinh", new QuyDinh());
        Page<QuyDinhResponse> quyDinhResponsePage = quyDinhService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", quyDinhResponsePage.getSize());
        model.addAttribute("totalPages", quyDinhResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listQuyDinhActive", quyDinhResponsePage);
        return "admin/quy_dinh/trang_chu_quy_dinh";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("quyDinh", new QuyDinh());
        Page<QuyDinhResponse> quyDinhResponsePage = quyDinhService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", quyDinhResponsePage.getSize());
        model.addAttribute("totalPages", quyDinhResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listQuyDinhInActive", quyDinhResponsePage);
        return "admin/quy_dinh/revert_quy_dinh";
    }
}
