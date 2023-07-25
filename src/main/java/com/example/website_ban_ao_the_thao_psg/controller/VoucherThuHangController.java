package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.DiaChi;
import com.example.website_ban_ao_the_thao_psg.entity.VoucherThuHang;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateDiaChiRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateVoucherThuHangRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.DiaChiResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.VoucherThuHangResponse;
import com.example.website_ban_ao_the_thao_psg.service.VoucherThuHangService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/psg/voucher-thu-hang")
public class VoucherThuHangController {
    @Autowired
    VoucherThuHangService voucherThuHangService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("voucherThuHang", new VoucherThuHang());

        return pageVouCherThuHangActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageVouCherThuHangActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("voucherThuHang", new VoucherThuHang());
        Page<VoucherThuHangResponse> voucherThuHangResponsePageActive = voucherThuHangService.pageVouCherThuHangActive(pageNo, 3);
        model.addAttribute("size", voucherThuHangResponsePageActive.getSize());
        model.addAttribute("totalPages", voucherThuHangResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listVoucherThuHangActive", voucherThuHangResponsePageActive);
        return "admin/voucher_thu_hang/trang_chu_voucher_thu_hang";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageVouCherThuHangInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<VoucherThuHangResponse> voucherThuHangResponsePageInActive = voucherThuHangService.pageVouCherThuHangInActive(pageNo, 3);
        model.addAttribute("size", voucherThuHangResponsePageInActive.getSize());
        model.addAttribute("totalPages", voucherThuHangResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listVoucherThuHangInActive", voucherThuHangResponsePageInActive);
        return "admin/voucher_thu_hang/revert_voucher_thu_hang";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        VoucherThuHangResponse voucherThuHangResponse = voucherThuHangService.getOne(id);
        model.addAttribute("voucherThuHang", voucherThuHangResponse);
        return "admin/voucher_thu_hang/view_update_voucher_thu_hang";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("voucherThuHang", new CreateVoucherThuHangRequest());
        return "admin/voucher_thu_hang/view_add_voucher_thu_hang";
    }
    @PostMapping("/delete/{id}")
    public String deleteVoucher(@PathVariable("id") Integer id,HttpSession session) {
        voucherThuHangService.deleteVoucherThuHang(id, LocalDateTime.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/voucher-thu-hang/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertVoucher(@PathVariable("id") Integer id,HttpSession session) {
        voucherThuHangService.revertVoucherThuHang(id, LocalDateTime.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/voucher-thu-hang/hien-thi";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("voucherThuHang") CreateVoucherThuHangRequest createVoucherThuHangRequest, BindingResult result, Model model, HttpSession session) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ngayKetThuc = createVoucherThuHangRequest.getNgayKetThuc();
        if (ngayKetThuc == null || ngayKetThuc.isBefore(now)){
            model.addAttribute("error", "Ngày kết thúc không được nhỏ hơn ngày bắt đầu!");
            return "admin/voucher_thu_hang/view_add_voucher_thu_hang";
        }
        if(result.hasErrors()){
            model.addAttribute("voucherThuHang", createVoucherThuHangRequest);
            System.out.println("vai l");
            return "admin/voucher_thu_hang/view_add_voucher_thu_hang";
        }

//        if(1==1) {
//            model.addAttribute("error", "Mã trùng");
//            return "admin/voucher_thu_hang/view_add_voucher_thu_hang";
//        }
        voucherThuHangService.add(createVoucherThuHangRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        System.out.println("loi");
        return "redirect:/admin/psg/voucher-thu-hang/hien-thi";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("voucherThuHang")UpdateVoucherThuHangRequest updateVoucherThuHangRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("voucherThuHang", updateVoucherThuHangRequest);
            return "admin/voucher_thu_hang/view_update_voucher_thu_hang";
        }
        voucherThuHangService.update(updateVoucherThuHangRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/voucher-thu-hang/hien-thi";
    }
    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("voucherThuHang", new VoucherThuHang());
        Page<VoucherThuHangResponse> voucherThuHangResponses = voucherThuHangService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", voucherThuHangResponses.getSize());
        model.addAttribute("totalPages", voucherThuHangResponses.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listVoucherThuHangActive", voucherThuHangResponses);
        return "admin/voucher_thu_hang/trang_chu_voucher_thu_hang";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("voucherThuHang", new VoucherThuHang());
        Page<VoucherThuHangResponse> voucherThuHangResponses = voucherThuHangService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", voucherThuHangResponses.getSize());
        model.addAttribute("totalPages", voucherThuHangResponses.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listVoucherThuHangInActive", voucherThuHangResponses);
        return "admin/voucher_thu_hang/revert_voucher_thu_hang";
    }


}
