package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.NhanVien;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateKhachHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNhanVienRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.NhanVienResponse;
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
    private VaiTroService vaiTroService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("nhanVien", new NhanVien());
        return pageNhanVienActive(0, model);
    }
    @GetMapping("/pageActive/{pageNo}")
    public String pageNhanVienActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        Page<NhanVienResponse> taiKhoanResponsePageActive = nhanVienService.pageNhanVienActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }
    @GetMapping("/searchActive/{pageNo}")
    public String searchTaiKhoanActive(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam("search") String search) {
        model.addAttribute("nhanVien", new NhanVien());
        Page<NhanVienResponse> taiKhoanResponsePageActive = nhanVienService.pageSearchACTIVE(search,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }

    @GetMapping("/searchTuoiMinMax/{pageNo}")
    public String searchTuoiMinMax(@PathVariable("pageNo") Integer pageNo, Model model, @RequestParam(value = "tuoiMin",required = false) Integer min,@RequestParam(value = "tuoiMax",required = false) Integer max) {
        model.addAttribute("nhanVien", new NhanVien());
        Page<NhanVienResponse> taiKhoanResponsePageActive = nhanVienService.pageSearchTuoiMinMax(min,max,pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/trang_chu_nhan_vien";
    }
    @GetMapping("/pageInActive/{pageNo}")
    public String khoiPhuc(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        Page<NhanVienResponse> taiKhoanResponsePageActive = nhanVienService.pageTaiKhoanInActive(pageNo, 3);
        model.addAttribute("size", taiKhoanResponsePageActive.getSize());
        model.addAttribute("totalPages", taiKhoanResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listNhanVienInActive", taiKhoanResponsePageActive);
        return "admin/nhan_vien/revert_nhan_vien";
    }
//
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
    public String add(@Valid @ModelAttribute("nhanVien") CreateNhanVienRequest createNhanVienRequest, BindingResult result,
                      @RequestParam("anhNV1") MultipartFile file,
                      Model model) throws IOException, SQLException {
        if (result.hasErrors()) {
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }

        if (file == null || file.isEmpty()) {
            result.rejectValue("anhNV", "anhNV", "Vui lòng tải lên một tệp tin ảnh");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }

        if (nhanVienService.existsBySdtNhanVien(createNhanVienRequest.getSdt())){
            result.rejectValue("sdt", "sdt", "Số Điện Thoại này đã tồn tại ");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }
        if (nhanVienService.existsByEmailNhanVien(createNhanVienRequest.getEmail())){
            result.rejectValue("email", "email", "Email này đã tồn tại ");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate ngaySinh = createNhanVienRequest.getNgaySinh();

        if (ngaySinh.plusYears(18).isAfter(currentDate)) {
            result.rejectValue("ngaySinh", "loiNgaySinh", "Vui lòng nhập ngày sinh không lớn hơn " +
                    "ngày hôm nay hoặc không đủ 18 tuổi");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_add_nhan_vien";
        }

        nhanVienService.add(createNhanVienRequest, file);
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id")Integer id,Model model) {
                NhanVienResponse tk= nhanVienService.getOne(id);
        model.addAttribute("vaiTro",vaiTroService.getAll());
        model.addAttribute("nhanVien",tk);
        return "admin/nhan_vien/view_update_nhan_vien";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nhanVien") UpdateNhanVienRequest updateNhanVienRequest, BindingResult result,@RequestParam("idAnhNV")MultipartFile anh,  Model model) throws IOException, SQLException{
        if (result.hasErrors()){
            model.addAttribute("vaiTro",vaiTroService.getAll());
            return "admin/nhan_vien/view_update_nhan_vien";
        }
        if (anh == null || anh.isEmpty()) {
            result.rejectValue("anhNV", "anhNV", "Vui lòng tải lên một tệp tin ảnh");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_update_nhan_vien";
        }



        // Kiểm tra nếu số điện thoại mới (nếu có) khác với số điện thoại cũ
        if (nhanVienService.existsBySdtNhanVienWithDifferentId(updateNhanVienRequest.getSdt(), updateNhanVienRequest.getId())) {
            result.rejectValue("sdt", "sdt", "Số điện thoại này đã tồn tại");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_update_nhan_vien";
        }
        if (nhanVienService.existsByEmailNhanVienWithDifferentId(updateNhanVienRequest.getEmail(), updateNhanVienRequest.getId())) {
            result.rejectValue("email", "email", "Email này đã tồn tại");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_update_nhan_vien";
        }
        model.addAttribute("htAnh",updateNhanVienRequest.getAnhNV());

        LocalDate currentDate = LocalDate.now();
        LocalDate ngaySinh = updateNhanVienRequest.getNgaySinh();

        if (ngaySinh.plusYears(18).isAfter(currentDate)) {
            result.rejectValue("ngaySinh", "loiNgaySinh", "Vui lòng nhập ngày sinh không lớn hơn " +
                    "ngày hôm nay hoặc không đủ 18 tuổi");
            model.addAttribute("vaiTro", vaiTroService.getAll());
            return "admin/nhan_vien/view_update_nhan_vien";
        }
        nhanVienService.update(updateNhanVienRequest.getId(),anh,updateNhanVienRequest);
        return "redirect:/admin/psg/nhan-vien/hien-thi";
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("idAnhNV") Integer id) throws IOException, SQLException {
        NhanVien tk = nhanVienService.viewById(id);
        byte[] imageBytes = null;
        imageBytes = tk.getAnhNV().getBytes(1, (int) tk.getAnhNV().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
