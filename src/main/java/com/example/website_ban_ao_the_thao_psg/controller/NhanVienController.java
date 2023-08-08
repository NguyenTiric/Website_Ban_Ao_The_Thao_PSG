package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.TaiKhoanResponse;
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
@RequestMapping("/admin/psg/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    VaiTroService vaiTroService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("nhanVien", new TaiKhoan());
        return pageTaiKhoanActive(0, model);
    }
    @GetMapping("/pageActive/{pageNo}")
    public String pageTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = nhanVienService.pageTaiKhoanActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }
    @GetMapping("/searchActive/{pageNo}")
    public String searchTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam("search") String search) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = nhanVienService.pageSearchACTIVE(search,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }

    @GetMapping("/searchTuoiMinMax/{pageNo}")
    public String searchTuoiMinMax(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam(value = "tuoiMin",required = false) Integer min,@RequestParam(value = "tuoiMax",required = false) Integer max) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = nhanVienService.pageSearchTuoiMinMax(min,max,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }
    @GetMapping("/pageInActive/{pageNo}")
    public String khoiPhuc(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<TaiKhoanResponse> taiKhoanResponsePageActive = nhanVienService.pageTaiKhoanInActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienInActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/revert_nhan_vien";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("nhanVien", new CreateNhanVienRequest());
        model.addAttribute("vaiTro",vaiTroService.getAll());
        return "admin/nhan_vien/view_add_nhan_vien";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id,Model model) {
       nhanVienService.delete(id, LocalDate.now());
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }

    @GetMapping("/revert/{id}")
    public String revet(@PathVariable("id")Integer id,Model model) {
        nhanVienService.revertTaiKhoan(id, LocalDate.now());
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nhanVien") CreateNhanVienRequest createTaiKhoanRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = createTaiKhoanRequest.getNgaySinh();
        int age = Period.between(dateOfBirth, currentDate).getYears();
        if (age < 18) {
            result.rejectValue("ngaySinh", "loiNgaySinh", "Vai  Lòng nhập Ngày Sinh Phải Lớn Hơn 18 Tuổi");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }
        System.out.println("anh-------"+createTaiKhoanRequest.getAnh());
        nhanVienService.add(createTaiKhoanRequest);
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id")Integer id,Model model) {
                TaiKhoanResponse tk= nhanVienService.getOne(id);
        model.addAttribute("vaiTro",vaiTroService.getAll());
        model.addAttribute("nhanVien",tk);
        return "admin/nhan_vien/view_update_nhan_vien";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nhanVien") UpdateNhanVienRequest updateNhanVienRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/nhan_vien/view_update_nhan_vien";
        }
        nhanVienService.update(updateNhanVienRequest);
        System.out.println(updateNhanVienRequest.getNgaySinh());
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }
}
