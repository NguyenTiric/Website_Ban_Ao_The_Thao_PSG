package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
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
@RequestMapping("/admin/psg/thu-hang")
public class ThuHangController {

    @Autowired
    ThuHangService thuHangService;

    @GetMapping("/view-all")
    public String viewAll(Model model
    ) {
        return hienThi(0, model);
    }

    @GetMapping("/view-all/{pageNo}")
    public String hienThi(@PathVariable("pageNo") Integer pageNo,
                          Model model
    ) {
        Page<ThuHangResponse> page = this.thuHangService.pageFindAll(pageNo, 1);
        model.addAttribute("size", page.getSize());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("th", new ThuHang());
        model.addAttribute("listTH", page);
        return "admin/thu_hang";
    }

    @PostMapping("/view-all/add")
    public String add(@Valid
                      @ModelAttribute("th") CreateThuHangRequest createThuHangRequest,
                      BindingResult bindingResult,
                      Model model
    ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("th", createThuHangRequest);
            return "admin/thu_hang";
        }
        this.thuHangService.add(createThuHangRequest);
        return "redirect:/admin/psg/thu-hang/view-all";
    }
}
