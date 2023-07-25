package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.KhachHangService;
import com.example.website_ban_ao_the_thao_psg.service.VaiTroService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin/psg/khach-hang")
//@ResponseBody
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
        Page<KhachHangResponse> taiKhoanResponsePageActive = khachHangService.pageTaiKhoanActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKhachHangActive", taiKhoanResponsePageActive);
        return "admin/khach_hang/trang_chu_khach_hang";
    }
    @GetMapping("/searchActive/{pageNo}")
    public String searchTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam("search") String search) {
        model.addAttribute("khachHang", new TaiKhoan());
        Page<KhachHangResponse> taiKhoanResponsePageActive = khachHangService.pageSearchACTIVE(search,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKhachHangActive", taiKhoanResponsePageActive);
        return "admin/khach_hang/trang_chu_khach_hang";
    }

    @GetMapping("/searchTuoiMinMax/{pageNo}")
    public String searchTuoiMinMax(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam(value = "tuoiMin",required = false) Integer min,@RequestParam(value = "tuoiMax",required = false) Integer max) {
        model.addAttribute("khachHang", new TaiKhoan());
        Page<KhachHangResponse> taiKhoanResponsePageActive = khachHangService.pageSearchTuoiMinMax(min,max,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKhachHangActive", taiKhoanResponsePageActive);
        return "admin/khach_hang/trang_chu_khach_hang";
    }
    @GetMapping("/pageInActive/{pageNo}")
    public String khoiPhuc(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("khachHang", new TaiKhoan());
        Page<KhachHangResponse> taiKhoanResponsePageActive = khachHangService.pageTaiKhoanInActive(pageNo, 3);
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
    public String add(@Valid @ModelAttribute("khachHang") CreateKhachHangRequest createKhachHangRequest,
                      @RequestParam("anhKH") MultipartFile file,
                      BindingResult result,
                      Model model) throws IOException, SQLException {
        if (result.hasErrors()) {
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/khach_hang/view_add_khach_hang";
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate ngaySinh = createKhachHangRequest.getNgaySinh();
        if (ngaySinh != null && ngaySinh.isAfter(currentDate)) {
            result.rejectValue("ngaySinh", "loiNgaySinh", "Vui lòng nhập ngày sinh không lớn hơn ngày hôm nay");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/khach_hang/view_add_khach_hang";
        }

        khachHangService.add(createKhachHangRequest, file);
        return "redirect:/admin/psg/khach-hang/hien-thi";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id")Integer id,Model model) {
        KhachHangResponse tk= khachHangService.getOne(id);
        model.addAttribute("vaiTro",vaiTroService.getAll());
        model.addAttribute("khachHang",tk);
        return "admin/khach_hang/view_update_khach_hang";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("khachHang") UpdateKhachHangRequest updateKhachHangRequest,@RequestParam("idAnhSua")MultipartFile anh, BindingResult result, Model model) throws IOException, SQLException{
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/khach_hang/view_update_khach_hang";
        }
        khachHangService.update(updateKhachHangRequest.getId(),anh,updateKhachHangRequest);
        return "redirect:/admin/psg/khach-hang/hien-thi";
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("idAnh") Integer id) throws IOException, SQLException {
        TaiKhoan tk = khachHangService.viewById(id);
        byte[] imageBytes = null;
        imageBytes = tk.getAnh().getBytes(1, (int) tk.getAnh().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
