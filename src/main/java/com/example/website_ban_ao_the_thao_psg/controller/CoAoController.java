package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.CoAo;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CoAoResponse;
import com.example.website_ban_ao_the_thao_psg.service.CoAoService;
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
@RequestMapping("/admin/psg/co-ao")
public class CoAoController {
    @Autowired
    CoAoService coAoService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("coAo", new CoAo());
        return pageCoAoActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageCoAoActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("coAo", new CoAo());
        Page<CoAoResponse> coAoResponsePageActive = coAoService.pageCoAoActive(pageNo, 3);
        model.addAttribute("size", coAoResponsePageActive.getSize());
        model.addAttribute("totalPages", coAoResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCoAoActive", coAoResponsePageActive);
        return "admin/co_ao/trang_chu_co_ao";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageCoAoInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<CoAoResponse> coAoResponsePageInActive = coAoService.pageCoAoInActive(pageNo, 3);
        model.addAttribute("size", coAoResponsePageInActive.getSize());
        model.addAttribute("totalPages", coAoResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCoAoInActive", coAoResponsePageInActive);
        return "admin/co_ao/revert_co_ao";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        CoAoResponse coAoResponse = coAoService.getOne(id);
        model.addAttribute("coAo", coAoResponse);
        return "admin/co_ao/view_update_co_ao";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("coAo", new CreateCoAoRequest());
        return "admin/co_ao/view_add_co_ao";
    }
    @PostMapping("/delete/{id}")
    public String deleteCoAo(@PathVariable("id") Integer id,HttpSession session) {
        coAoService.deleteCoAo(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/co-ao/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertCoAo(@PathVariable("id") Integer id,HttpSession session) {
        coAoService.revertCoAo(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/co-ao/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("coAo") CreateCoAoRequest createCoAoRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("coAo", createCoAoRequest);
            return "admin/co_ao/view_add_co_ao";
        }
        coAoService.add(createCoAoRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/co-ao/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("coAo") UpdateCoAoRequest updateCoAoRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("coAo", updateCoAoRequest);
            return "admin/co_ao/view_update_co_ao";
        }
        coAoService.update(updateCoAoRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/co-ao/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("coAo", new CoAo());
        Page<CoAoResponse> coAoResponsePage = coAoService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", coAoResponsePage.getSize());
        model.addAttribute("totalPages", coAoResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCoAoActive", coAoResponsePage);
        return "admin/co_ao/trang_chu_co_ao";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("coAo", new CoAo());
        Page<CoAoResponse> coAoResponsePage = coAoService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", coAoResponsePage.getSize());
        model.addAttribute("totalPages", coAoResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCoAoInActive", coAoResponsePage);
        return "admin/co_ao/revert_co_ao";
    }
}
