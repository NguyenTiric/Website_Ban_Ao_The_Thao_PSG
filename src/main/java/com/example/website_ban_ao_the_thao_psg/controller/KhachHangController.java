package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
import com.example.website_ban_ao_the_thao_psg.service.KhachHangService;
import com.example.website_ban_ao_the_thao_psg.service.NhanVienService;
import com.example.website_ban_ao_the_thao_psg.service.VaiTroService;
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
import java.time.Period;

@Controller
@RequestMapping("/admin/psg/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    VaiTroService vaiTroService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("khachHang", new TaiKhoan());
        return pageTaiKhoanActive(0, model);
    }
    @GetMapping("/pageActive/{pageNo}")
    public String pageTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("khachHang", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = khachHangService.pageTaiKhoanActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKhachHangActive", taiKhoanResponsePageActive);
        return "admin/khach_hang/trang_chu_khach_hang";
    }
    @GetMapping("/searchActive/{pageNo}")
    public String searchTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam("search") String search) {
        model.addAttribute("khachHang", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = khachHangService.pageSearchACTIVE(search,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKhachHangActive", taiKhoanResponsePageActive);
        return "admin/khach_hang/trang_chu_khach_hang";
    }

    @GetMapping("/searchTuoiMinMax/{pageNo}")
    public String searchTuoiMinMax(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam(value = "tuoiMin",required = false) Integer min,@RequestParam(value = "tuoiMax",required = false) Integer max) {
        model.addAttribute("khachHang", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = khachHangService.pageSearchTuoiMinMax(min,max,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKhachHangActive", taiKhoanResponsePageActive);
        return "admin/khach_hang/trang_chu_khach_hang";
    }
    @GetMapping("/pageInActive/{pageNo}")
    public String khoiPhuc(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("khachHang", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = khachHangService.pageTaiKhoanInActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKhachHangInActive", taiKhoanResponsePageActive);
        return "admin/khach_hang/revert_khach_hang";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("khachHang", new CreateKhachHangRequest());
        model.addAttribute("vaiTro",vaiTroService.getAll());
        return "admin/khach_hang/view_add_khach_hang";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id,Model model) {
        khachHangService.delete(id, LocalDate.now());
        return "redirect:/admin/psg/khach-hang/hien-thi";
    }

    @GetMapping("/revert/{id}")
    public String revet(@PathVariable("id")Integer id,Model model) {
        khachHangService.revertTaiKhoan(id, LocalDate.now());
        return "redirect:/admin/psg/khach-hang/hien-thi";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("khachHang") CreateKhachHangRequest createKhachHangRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/khach_hang/view_add_khach_hang";
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = createKhachHangRequest.getNgaySinh();
        int age = Period.between(dateOfBirth, currentDate).getYears();
        if (age < 18) {
            result.rejectValue("ngaySinh", "loiNgaySinh", "Vai  Lòng nhập Ngày Sinh Phải Lớn Hơn 18 Tuổi");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/khach_hang/view_add_khach_hang";
        }
        System.out.println("anh-------"+createKhachHangRequest.getAnh());
        khachHangService.add(createKhachHangRequest);
        return "redirect:/admin/psg/khach-hang/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id")Integer id,Model model) {
        TaiKhoanResponse tk= khachHangService.getOne(id);
        model.addAttribute("vaiTro",vaiTroService.getAll());
        model.addAttribute("khachHang",tk);
        return "admin/khach_hang/view_update_khach_hang";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nhanVien") UpdateKhachHangRequest updateKhachHangRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/khach_hang/view_update_khach_hang";
        }
        khachHangService.update(updateKhachHangRequest);
        return "redirect:/admin/psg/khach-hang/hien-thi";
    }
}
