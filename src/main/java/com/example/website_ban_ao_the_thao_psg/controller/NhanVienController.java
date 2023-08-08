package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.NhanVienService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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
        Page<KhachHangResponse> taiKhoanResponsePageActive = nhanVienService.pageTaiKhoanActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }
    @GetMapping("/searchActive/{pageNo}")
    public String searchTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam("search") String search) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<KhachHangResponse> taiKhoanResponsePageActive = nhanVienService.pageSearchACTIVE(search,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }

    @GetMapping("/searchTuoiMinMax/{pageNo}")
    public String searchTuoiMinMax(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam(value = "tuoiMin",required = false) Integer min,@RequestParam(value = "tuoiMax",required = false) Integer max) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<KhachHangResponse> taiKhoanResponsePageActive = nhanVienService.pageSearchTuoiMinMax(min,max,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }
    @GetMapping("/pageInActive/{pageNo}")
    public String khoiPhuc(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nhanVien", new TaiKhoan());
        Page<KhachHangResponse> taiKhoanResponsePageActive = nhanVienService.pageTaiKhoanInActive(pageNo, 3);
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
    public String add(@Valid @ModelAttribute("nhanVien") CreateNhanVienRequest createNhanVienRequest,
                      @RequestParam("anhNV") MultipartFile file,
                      BindingResult result,
                      Model model) throws IOException, SQLException {
        if (result.hasErrors()) {
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan-vien";
        }
        if (nhanVienService.existsBySdtNhanVien(createNhanVienRequest.getSdt())){
            result.rejectValue("sdt", "checkSdt", "Số Điện Thoại này đã tồn tại ");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan-vien";
        }
        if (nhanVienService.existsByEmailNhanVien(createNhanVienRequest.getEmail())){
            result.rejectValue("email", "email", "Email này đã tồn tại ");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan-vien";
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate ngaySinh = createNhanVienRequest.getNgaySinh();

        if (ngaySinh != null && ngaySinh.plusYears(18).isAfter(currentDate)) {
            result.rejectValue("ngaySinh", "loiNgaySinh", "Vui lòng nhập ngày sinh không lớn hơn " +
                    "ngày hôm nay hoặc không đủ 18 tuổi");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/khach_hang/view_add_khach_hang";
        }

        nhanVienService.add(createNhanVienRequest, file);
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id")Integer id,Model model) {
                KhachHangResponse tk= nhanVienService.getOne(id);
        model.addAttribute("vaiTro",vaiTroService.getAll());
        model.addAttribute("nhanVien",tk);
        return "admin/nhan_vien/view_update_nhan_vien";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nhanVien") UpdateNhanVienRequest updateNhanVienRequest, @RequestParam("idAnhNV")MultipartFile anh, BindingResult result, Model model) throws IOException, SQLException{
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/nhan-vien/view_update_khach_hang";
        }
        nhanVienService.update(updateNhanVienRequest.getId(),anh,updateNhanVienRequest);
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("idAnhNV") Integer id) throws IOException, SQLException {
        TaiKhoan tk = nhanVienService.viewById(id);
        byte[] imageBytes = null;
        imageBytes = tk.getAnh().getBytes(1, (int) tk.getAnh().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
