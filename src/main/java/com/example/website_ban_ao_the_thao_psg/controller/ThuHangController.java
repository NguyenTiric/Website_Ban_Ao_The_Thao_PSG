package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.ChiTietVoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietVoucherThuHangService;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import com.example.website_ban_ao_the_thao_psg.service.VoucherThuHangService;
import groovyjarjarpicocli.CommandLine;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin/psg/thu-hang")
public class ThuHangController {

    @Autowired
    private ThuHangService thuHangService;

    @Autowired
    private VoucherThuHangService voucherThuHangService;

    @Autowired
    private ChiTietVoucherThuHangService chiTietVoucherThuHangService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("thuHang", new ThuHang());
        return pageThuHangActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageThuHangActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("thuHang", new ThuHang());
        Page<ThuHangResponse> thuHangResponsePageActive = thuHangService.pageThuHangActive(pageNo, 4);
        model.addAttribute("size", thuHangResponsePageActive.getSize());
        model.addAttribute("totalPages", thuHangResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuHangActive", thuHangResponsePageActive);
        return "admin/thu_hang/trang_chu_thu_hang";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageThuHangInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<ThuHangResponse> thuHangResponsePageInActive = thuHangService.pageThuHangInActive(pageNo, 4);
        model.addAttribute("size", thuHangResponsePageInActive.getSize());
        model.addAttribute("totalPages", thuHangResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuHangInActive", thuHangResponsePageInActive);
        return "admin/thu_hang/revert_thu_hang";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        ThuHangResponse thuHangResponse = thuHangService.getOne(id);
        model.addAttribute("thuHang", thuHangResponse);
        model.addAttribute("voucherThuHang", new VoucherThuHang());
        model.addAttribute("listVoucher", this.voucherThuHangService.getAll());
        model.addAttribute("chiTietVoucher", this.chiTietVoucherThuHangService.getTheoIdThuHang(id));
        return "admin/thu_hang/view_update_thu_hang";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("thuHang", new CreateThuHangRequest());
        model.addAttribute("voucherThuHang", new VoucherThuHang());
        model.addAttribute("listVoucher", this.voucherThuHangService.getAll());
        model.addAttribute("getPending", this.chiTietVoucherThuHangService.getByTrangThaiPending());
        return "admin/thu_hang/view_add_thu_hang";
    }

    @PostMapping("/delete/{id}")
    public String deleteThuHang(@PathVariable("id") Integer id, HttpSession session, Model model) {
        try {
            thuHangService.deleteThuHang(id, LocalDate.now());
            session.setAttribute("successMessage", "Xóa thành công!");
            return "redirect:/admin/psg/thu-hang/hien-thi";
        } catch (RuntimeException rt) {
            model.addAttribute("errorMessage", rt.getMessage());
            return pageThuHangActive(0, model);
        }

    }

    @PostMapping("/revert/{id}")
    public String revertThuHang(@PathVariable("id") Integer id, HttpSession session, Model model) {
        try {
            thuHangService.checkDuplicateName(id);
            thuHangService.revertThuHang(id, LocalDate.now());
            session.setAttribute("successMessage", "Khôi phục thành công!");
        } catch (CommandLine.DuplicateNameException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return pageThuHangInActive(0, model);
        }
        return "redirect:/admin/psg/thu-hang/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("thuHang") CreateThuHangRequest createThuHangRequest, BindingResult result, @RequestParam("voucherThuHang") List<VoucherThuHang> listVoucherThuHang, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("listVoucher", this.voucherThuHangService.getAll());
            model.addAttribute("thuHang", createThuHangRequest);
            return "admin/thu_hang/view_add_thu_hang";
        }
        try {
//            thuHangService.add(createThuHangRequest);
            this.chiTietVoucherThuHangService.addChiTietVoucher(createThuHangRequest, listVoucherThuHang);
            session.setAttribute("successMessage", "Thêm thành công!");
            return "redirect:/admin/psg/thu-hang/view-add";
        } catch (NullPointerException e) {
            model.addAttribute("errorMessage", "Looxi");
            return "admin/thu_hang/view_add_thu_hang";
        } catch (NumberFormatException ex) {
            model.addAttribute("errorMessage", "Lỗi" + ex.getMessage());
            System.out.println(ex.getMessage());
            return "admin/thu_hang/view_add_thu_hang";
        } catch (CommandLine.DuplicateNameException er) {
            model.addAttribute("errorMessage", er.getMessage());
            return "admin/thu_hang/view_add_thu_hang";
        } catch (RuntimeException rt) {
            model.addAttribute("errorMessage", rt.getMessage());
            return "admin/thu_hang/view_add_thu_hang";
        }
    }

    @PostMapping("/updateSoLuong")
    public String updateSoLuong(@RequestParam("ids") List<Integer> id, @RequestParam("soLuongs") List<Integer> soLuong) {
        this.chiTietVoucherThuHangService.updateSoLuongVoucherThuHang(id, soLuong);
        return "redirect:/admin/psg/thu-hang/hien-thi";
    }

    @PostMapping("/update-so-luong-active")
    public String updateSoLuongActive(@RequestParam("ids") List<Integer> id, @RequestParam("soLuongs") List<Integer> soLuong) {
        this.chiTietVoucherThuHangService.updateSoLuongVoucherThuHang(id, soLuong);
        return "redirect:/admin/psg/thu-hang/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("thuHang") UpdateThuHangRequest updateThuHangRequest, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("thuHang", updateThuHangRequest);
            return "admin/thu_hang/view_update_thu_hang";
        }
        try {
            thuHangService.update(updateThuHangRequest);
            session.setAttribute("successMessage", "Cập nhập thành công!");
            return "redirect:/admin/psg/thu-hang/hien-thi";
        } catch (RuntimeException rt) {
            model.addAttribute("errorMessage", rt.getMessage());
            return "admin/thu_hang/view_add_thu_hang";
        }
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchNameOrMaActive(Model model, @PathVariable("pageNo") Integer pageNo, @RequestParam("searchNameOrMa") String searchNameOrMa) {
        model.addAttribute("thuHang", new ThuHang());
        Page<ThuHangResponse> thuHangResponsePage = thuHangService.searchNameOrMaActive(searchNameOrMa, pageNo, 4);
        model.addAttribute("size", thuHangResponsePage.getSize());
        model.addAttribute("totalPages", thuHangResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuHangActive", thuHangResponsePage);
        return "admin/thu_hang/trang_chu_thu_hang";
    }

    @GetMapping("/searchSoLuongDonHangToiThieuActive/{pageNo}")
    public String searchSoLuongDonHangToiThieuActive(Model model, @PathVariable("pageNo") Integer pageNo, @RequestParam("searchSoLuongDonHangToiThieu") String searchSoLuongDonHangToiThieu) {
        try {
            model.addAttribute("thuHang", new ThuHang());
            Page<ThuHangResponse> thuHangResponsePage = thuHangService.searchSoLuongDonHangToiThieuActive(Integer.valueOf(searchSoLuongDonHangToiThieu), pageNo, 4);
            model.addAttribute("size", thuHangResponsePage.getSize());
            model.addAttribute("totalPages", thuHangResponsePage.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listThuHangActive", thuHangResponsePage);
            return "admin/thu_hang/trang_chu_thu_hang";

        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage1", "Tìm kiếm không được bỏ trống!");
            return pageThuHangActive(0, model);
        }
    }

    @GetMapping("/searchMinMaxSoTien/{pageNo}")
    public String searchMinMaxSoTienActive(@PathVariable("pageNo") Integer pageNo,
                                           @RequestParam(value = "minAmount", required = false) BigDecimal minAmount,
                                           @RequestParam(value = "maxAmount", required = false) BigDecimal maxAmount,
                                           Model model) {
        try {
            model.addAttribute("thuHang", new ThuHang());
            Page<ThuHangResponse> thuHangResponsePageActive = thuHangService.searchMinMaxSoTien(minAmount, maxAmount, pageNo, 4);
            model.addAttribute("size", thuHangResponsePageActive.getSize());
            model.addAttribute("totalPages", thuHangResponsePageActive.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listThuHangActive", thuHangResponsePageActive);
            return "admin/thu_hang/trang_chu_thu_hang";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return pageThuHangActive(0, model);
        }
    }

    @GetMapping("/searchMinMaxDonHang/{pageNo}")
    public String searchMinMaxDonHangActive(@PathVariable("pageNo") Integer pageNo,
                                            @RequestParam(value = "minAmount", required = false) Integer minAmount,
                                            @RequestParam(value = "maxAmount", required = false) Integer maxAmount,
                                            Model model) {
        try {
            model.addAttribute("thuHang", new ThuHang());
            Page<ThuHangResponse> thuHangResponsePageActive = thuHangService.searchMinMaxDonHang(minAmount, maxAmount, pageNo, 4);
            model.addAttribute("size", thuHangResponsePageActive.getSize());
            model.addAttribute("totalPages", thuHangResponsePageActive.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listThuHangActive", thuHangResponsePageActive);
            return "admin/thu_hang/trang_chu_thu_hang";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return pageThuHangActive(0, model);
        }
    }

    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo, @RequestParam("searchNameOrMaInActive") String searchNameOrMa) {
        model.addAttribute("thuHang", new ThuHang());
        Page<ThuHangResponse> thuHangResponsePage = thuHangService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", thuHangResponsePage.getSize());
        model.addAttribute("totalPages", thuHangResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuHangInActive", thuHangResponsePage);
        return "admin/thu_hang/revert_thu_hang";
    }

    @GetMapping("/searchSoTienHoacDonHangInActive/{pageNo}")
    public String searchSoTienHoacDonHangInActive(Model model, @PathVariable("pageNo") Integer pageNo, @RequestParam("searchSoTienHoacDonHangInActive") String searchNameOrMa) {
        model.addAttribute("thuHang", new ThuHang());
        Page<ThuHangResponse> thuHangResponsePage = thuHangService.searchSoLuongDonHangOrSoTienInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", thuHangResponsePage.getSize());
        model.addAttribute("totalPages", thuHangResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuHangInActive", thuHangResponsePage);
        return "admin/thu_hang/revert_thu_hang";
    }

    @GetMapping("/searchMinMaxSoTienInActive/{pageNo}")
    public String searchMinMaxSoTienInActive(@PathVariable("pageNo") Integer pageNo,
                                             @RequestParam(value = "minAmount", required = false) BigDecimal minAmount,
                                             @RequestParam(value = "maxAmount", required = false) BigDecimal maxAmount,
                                             Model model) {
        try {
            model.addAttribute("thuHang", new ThuHang());
            Page<ThuHangResponse> thuHangResponsePageActive = thuHangService.searchMinMaxSoTienInActive(minAmount, maxAmount, pageNo, 4);
            model.addAttribute("size", thuHangResponsePageActive.getSize());
            model.addAttribute("totalPages", thuHangResponsePageActive.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listThuHangActive", thuHangResponsePageActive);
            return "admin/thu_hang/revert_thu_hang";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return pageThuHangInActive(0, model);
        }
    }

    @GetMapping("/searchMinMaxDonHangInActive/{pageNo}")
    public String searchMinMaxDonHangInActive(@PathVariable("pageNo") Integer pageNo,
                                              @RequestParam(value = "minAmount", required = false) Integer minAmount,
                                              @RequestParam(value = "maxAmount", required = false) Integer maxAmount,
                                              Model model) {
        try {
            model.addAttribute("thuHang", new ThuHang());
            Page<ThuHangResponse> thuHangResponsePageActive = thuHangService.searchMinMaxDonHangInActive(minAmount, maxAmount, pageNo, 4);
            model.addAttribute("size", thuHangResponsePageActive.getSize());
            model.addAttribute("totalPages", thuHangResponsePageActive.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listThuHangActive", thuHangResponsePageActive);
            return "admin/thu_hang/revert_thu_hang";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return pageThuHangInActive(0, model);
        }
    }

    @GetMapping("delete-one-row-chi-tiet-thu-hang/{id}")
    public String deleteOneRowChiTietThuHang(@PathVariable("id") Integer id) {
        this.chiTietVoucherThuHangService.delete(id);
        return "redirect:/admin/psg/thu-hang/view-add";
    }

    @GetMapping("delete-one-row-chi-tiet-thu-hang-active/{id}")
    public String deleteOneRowChiTietThuHangActive(@PathVariable("id") Integer id) {
        this.chiTietVoucherThuHangService.delete(id);
        Integer ids = this.chiTietVoucherThuHangService.getOne(id).getThuHang().getId();
        return "redirect:/admin/psg/thu-hang/view-update/" + ids;
    }
}
