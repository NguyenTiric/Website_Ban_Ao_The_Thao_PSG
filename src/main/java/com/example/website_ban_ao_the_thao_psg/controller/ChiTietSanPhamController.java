package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.KichThuoc;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateAnhSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCauThuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChatLieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChiTietSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCoAoRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateCongNgheRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateDongSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateKichThuocRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNhaSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateThuongHieuRequest;
import com.example.website_ban_ao_the_thao_psg.service.CauThuService;
import com.example.website_ban_ao_the_thao_psg.service.ChatLieuService;
import com.example.website_ban_ao_the_thao_psg.service.ChiTietSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.CoAoService;
import com.example.website_ban_ao_the_thao_psg.service.CongNgheService;
import com.example.website_ban_ao_the_thao_psg.service.DongSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.KichThuocService;
import com.example.website_ban_ao_the_thao_psg.service.LoaiSanPhamService;
import com.example.website_ban_ao_the_thao_psg.service.MauSacService;
import com.example.website_ban_ao_the_thao_psg.service.NhaSanXuatService;
import com.example.website_ban_ao_the_thao_psg.service.NuocSanXuatService;
import com.example.website_ban_ao_the_thao_psg.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/psg/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    KichThuocService kichThuocService;

    @Autowired
    MauSacService mauSacService;

    @Autowired
    LoaiSanPhamService loaiSanPhamService;

    @Autowired
    ChatLieuService chatLieuService;

    @Autowired
    ThuongHieuService thuongHieuService;

    @Autowired
    CoAoService coAoService;

    @Autowired
    DongSanPhamService dongSanPhamService;

    @Autowired
    CauThuService cauThuService;

    @Autowired
    NhaSanXuatService nhaSanXuatService;

    @Autowired
    NuocSanXuatService nuocSanXuatService;

    @Autowired
    CongNgheService congNgheService;

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("sanPham", new CreateSanPhamRequest());
        model.addAttribute("congNghe", new CreateCongNgheRequest());
        model.addAttribute("cauThu", new CreateCauThuRequest());
        model.addAttribute("coAo", new CreateCoAoRequest());
        model.addAttribute("mauSac", new CreateMauSacRequest());
        model.addAttribute("chatLieu", new CreateChatLieuRequest());
        model.addAttribute("thuongHieu", new CreateThuongHieuRequest());
        model.addAttribute("dongSanPham", new CreateDongSanPhamRequest());
        model.addAttribute("loaiSanPham", new CreateLoaiSanPhamRequest());
        model.addAttribute("nhaSanXuat", new CreateNhaSanXuatRequest());
        model.addAttribute("nuocSanXuat", new CreateNuocSanXuatRequest());
        model.addAttribute("sanPham", new CreateSanPhamRequest());
        model.addAttribute("anhSanPham", new CreateAnhSanPhamRequest());
        model.addAttribute("chiTietSanPham", new CreateChiTietSanPhamRequest());

        model.addAttribute("listMauSac", mauSacService.getAll());
        model.addAttribute("listLoaiSanPham", loaiSanPhamService.getAll());
        model.addAttribute("listChatLieu", chatLieuService.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.getAll());
        model.addAttribute("listDongSanPham", dongSanPhamService.getAll());
        model.addAttribute("listCauThu", cauThuService.getAll());
        model.addAttribute("listNhaSanXuat", nhaSanXuatService.getAll());
        model.addAttribute("listNuocSanXuat", nuocSanXuatService.getAll());
        model.addAttribute("listCongNghe", congNgheService.getAll());
        model.addAttribute("listKichThuoc", kichThuocService.getALl());
        model.addAttribute("listCoAo", coAoService.getAll());
        model.addAttribute("listCtspPending", chiTietSanPhamService.getAllPending());

        return "admin/san_pham/view_add_san_pham";
    }

    @PostMapping("/addSanPham")
    public String addSanPham(@ModelAttribute("sanPham") CreateSanPhamRequest createSanPhamRequest,@ModelAttribute("ctsp") CreateChiTietSanPhamRequest createChiTietSanPhamRequest,@RequestParam("kichThuoc") List<KichThuoc> kichThuocList) {
        chiTietSanPhamService.addCtsp(createChiTietSanPhamRequest,createSanPhamRequest,kichThuocList);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }


    @PostMapping("/addCauThu")
    public String addCauThu(@Valid @ModelAttribute("cauThu") CreateCauThuRequest createCauThuRequest) {
        cauThuService.add(createCauThuRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addCongNghe")
    public String addCongNghe(@ModelAttribute("congNghe") CreateCongNgheRequest createCongNgheRequest) {
        congNgheService.add(createCongNgheRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addCoAo")
    public String addCoAo(@ModelAttribute("coAo") CreateCoAoRequest createCoAoRequest) {
        coAoService.add(createCoAoRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addMauSac")
    public String addMauSac(@ModelAttribute("mauSac") CreateMauSacRequest createMauSacRequest) {
        mauSacService.add(createMauSacRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addChatLieu")
    public String addChatLieu(@Valid @ModelAttribute("chatLieu") CreateChatLieuRequest createChatLieuRequest) {
        chatLieuService.add(createChatLieuRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addThuongHieu")
    public String addThuongHieu(@ModelAttribute("thuongHieu") CreateThuongHieuRequest createThuongHieuRequest) {
        thuongHieuService.add(createThuongHieuRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addDongSanPham")
    public String addDongSanPham(@ModelAttribute("dongSanPham") CreateDongSanPhamRequest createDongSanPhamRequest) {
        dongSanPhamService.add(createDongSanPhamRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addLoaiSanPham")
    public String addLoaiSanPham(@ModelAttribute("loaiSanPham") CreateLoaiSanPhamRequest createLoaiSanPhamRequest) {
        loaiSanPhamService.add(createLoaiSanPhamRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addNhaSanXuat")
    public String addNhaSanXuat(@Valid @ModelAttribute("nhaSanXuat") CreateNhaSanXuatRequest createNhaSanXuatRequest) {
        nhaSanXuatService.add(createNhaSanXuatRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addNuocSanXuat")
    public String addNuocSanXuat(@Valid @ModelAttribute("nuocSanXuat") CreateNuocSanXuatRequest createNuocSanXuatRequest) {
        nuocSanXuatService.add(createNuocSanXuatRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/addKichThuoc")
    public String addKichThuoc(@ModelAttribute("kichThuoc") CreateKichThuocRequest createKichThuocRequest) {
        kichThuocService.add(createKichThuocRequest);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @GetMapping("/deletePending/{id}")
    public String deletePending(@PathVariable("id") Integer id) {
        chiTietSanPhamService.deletePending(id);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }
}
