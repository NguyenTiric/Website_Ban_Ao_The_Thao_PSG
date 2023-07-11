package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.CongNghe;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.CongNgheResponse;
import com.example.website_ban_ao_the_thao_psg.service.CongNgheService;
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
@RequestMapping("/admin/psg/cong-nghe")
public class CongNgheController {


    @Autowired
    CongNgheService congNgheService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("congNghe", new CongNghe());
        return pageCongNgheActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageCongNgheActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("congNghe", new CongNghe());
        Page<CongNgheResponse> congNgheResponsePageActive = congNgheService.pageCongNgheActive(pageNo, 3);
        model.addAttribute("size", congNgheResponsePageActive.getSize());
        model.addAttribute("totalPages", congNgheResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCongNgheActive", congNgheResponsePageActive);
        return "admin/cong_nghe/trang_chu_cong_nghe";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageCongNgheInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<CongNgheResponse> congNgheResponsePageInActive = congNgheService.pageCongNgheInActive(pageNo, 3);
        model.addAttribute("size", congNgheResponsePageInActive.getSize());
        model.addAttribute("totalPages", congNgheResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCongNgheInActive", congNgheResponsePageInActive);
        return "admin/cong_nghe/revert_cong_nghe";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        CongNgheResponse congNgheResponse = congNgheService.getOne(id);
        model.addAttribute("congNghe", congNgheResponse);
        return "admin/cong_nghe/view_update_cong_nghe";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("congNghe", new CreateCongNgheRequest());
        return "admin/cong_nghe/view_add_cong_nghe";
    }
    @PostMapping("/delete/{id}")
    public String deleteCongNghe(@PathVariable("id") Integer id,HttpSession session) {
        congNgheService.deleteCongNghe(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/cong-nghe/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertCongNghe(@PathVariable("id") Integer id,HttpSession session) {
        congNgheService.revertCongNghe(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/cong-nghe/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("congNghe") CreateCongNgheRequest createCongNgheRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("congNghe", createCongNgheRequest);
            return "admin/cong_nghe/view_add_cong_nghe";
        }
        congNgheService.add(createCongNgheRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/cong-nghe/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("congNghe") UpdateCongNgheRequest updateCongNgheRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("congNghe", updateCongNgheRequest);
            return "admin/cong_nghe/view_update_cong_nghe";
        }
        congNgheService.update(updateCongNgheRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/cong-nghe/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("congNghe", new CongNghe());
        Page<CongNgheResponse> congNgheResponsePage = congNgheService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", congNgheResponsePage.getSize());
        model.addAttribute("totalPages", congNgheResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCongNgheActive", congNgheResponsePage);
        return "admin/cong_nghe/trang_chu_cong_nghe";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("congNghe", new CongNghe());
        Page<CongNgheResponse> congNgheResponsePage = congNgheService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", congNgheResponsePage.getSize());
        model.addAttribute("totalPages", congNgheResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCongNgheInActive", congNgheResponsePage);
        return "admin/cong_nghe/revert_cong_nghe";
    }
}
