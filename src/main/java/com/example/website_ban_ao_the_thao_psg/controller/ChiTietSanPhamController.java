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
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.AnhSanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.SanPhamResponse;
import com.example.website_ban_ao_the_thao_psg.service.AnhSanPhamService;
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
import java.util.List;

@Controller
@RequestMapping("/admin/psg/chi-tiet-san-pham")
public class  ChiTietSanPhamController {

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    AnhSanPhamService anhSanPhamService;

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

    private void newView(Model model) {
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
        model.addAttribute("kichThuoc", new CreateKichThuocRequest());
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
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        newView(model);
        return "admin/san_pham/view_add_san_pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        SanPhamResponse sanPhamResponse = chiTietSanPhamService.getOneSp(id);

        model.addAttribute("sanPham", sanPhamResponse);
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
        model.addAttribute("kichThuoc", new CreateKichThuocRequest());
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
        model.addAttribute("listCtspActive", chiTietSanPhamService.listChiTietSanPhamBySanPham(sanPhamResponse.getId()));
        model.addAttribute("listAnhSanPham", anhSanPhamService.anhSanPhamResponseList(sanPhamResponse.getId()));
        return "admin/san_pham/view_update_san_pham";
    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        return pageSanPhamActive(0, model);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") Integer id) throws IOException, SQLException {
        List<AnhSanPhamResponse> anhSanPhamResponse = anhSanPhamService.anhSanPhamResponseList(id);
        byte[] imageBytes = null;
        imageBytes = anhSanPhamResponse.get(0).getTen().getBytes(1, (int) anhSanPhamResponse.get(0).getTen().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping("/anhSp")
    public ResponseEntity<byte[]> displayAnhSp(@RequestParam("idSp") Integer id) throws IOException, SQLException {
        AnhSanPhamResponse anhSanPhamResponse = anhSanPhamService.getOne(id);
        byte[] imageBytes = null;
        imageBytes = anhSanPhamResponse.getTen().getBytes(1, (int) anhSanPhamResponse.getTen().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


    @GetMapping("/pageInActive/{pageNo}")
    public String pageSanPhamInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<SanPhamResponse> sanPhamResponsesInActive = chiTietSanPhamService.pageSanPhamInActive(pageNo, 3);
        model.addAttribute("size", sanPhamResponsesInActive.getSize());
        model.addAttribute("totalPages", sanPhamResponsesInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listSanPhamInActive", sanPhamResponsesInActive);
        return "admin/san_pham/revert_san_pham";
    }

    @PostMapping("/addSanPham")
    public String addSanPham(@Valid @ModelAttribute("sanPham") CreateSanPhamRequest createSanPhamRequest, BindingResult result, Model model, @RequestParam("kichThuoc") List<KichThuoc> kichThuocList, @RequestParam("image") MultipartFile[] files) throws IOException, SQLException {
        if (result.hasErrors()) {
            newView(model);
            return "admin/san_pham/view_add_san_pham";
        }
        chiTietSanPhamService.addCtsp(createSanPhamRequest, kichThuocList, files);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/update-so-luong-pending")
    public String updateSoLuongPending(@RequestParam("ids") List<Integer> id, @RequestParam("soLuongs") List<Integer> soLuong) {
        chiTietSanPhamService.updateSoLuongPending(id, soLuong);
        return "redirect:/admin/psg/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/delete-ctsp-update/{id}")
    public String deleteChiTietSanPhamUpdate(@PathVariable("id") Integer id) {
        chiTietSanPhamService.deleteChiTietSanPhamUpdate(id, LocalDate.now());
       Integer idSP = chiTietSanPhamService.getOneCtsp(id).getSanPham().getId();
        return "redirect:/admin/psg/chi-tiet-san-pham/view-update/" + idSP;
    }

    @PostMapping("/update-so-luong-active")
    public String updateSoLuongActive(@RequestParam("ids") List<Integer> id, @RequestParam("soLuongs") List<Integer> soLuong) {
        chiTietSanPhamService.updateSoLuongActive(id, soLuong);
        return "redirect:/admin/psg/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/insert-chi-tiet-san-pham")
    public String insertChiTietSanPham(@RequestParam("kichThuoc") List<KichThuoc> kichThuocList, @RequestParam("idSP") Integer idSP) {
        chiTietSanPhamService.insertCtsp(kichThuocList, idSP);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-update/" + idSP;
    }

    @PostMapping("/update-status")
    public String updateStatus(HttpSession session) {
        chiTietSanPhamService.updateTrangThai();
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/deleteAnhSanPham/{idAsp}")
    public String deleteAnhSanPham(@PathVariable("idAsp") Integer idAsp, HttpSession session) {
        anhSanPhamService.deleteAnhSanPham(idAsp, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/chi-tiet-san-pham/vi";
    }

    @PostMapping("/update-san-pham")
    public String updateSanPham(@Valid @ModelAttribute("sanPham") UpdateSanPhamRequest updateSanPhamRequest, BindingResult result, Model model, @RequestParam("image") MultipartFile[] files, HttpSession session) throws IOException, SQLException {
        chiTietSanPhamService.updateSp(updateSanPhamRequest, files);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/chi-tiet-san-pham/hien-thi";
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

    //    @GetMapping("/deletePending/{id}")
//    public String deletePending(@PathVariable("id") Integer id) {
//        chiTietSanPhamService.deletePending(id);
//        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
//    }
    @GetMapping("/searchActive/{pageNo}")
    public String searchActiveCtsp(@RequestParam(value = "searchNameOrMa", required = false) String search, @PathVariable("pageNo") Integer pageNo, Model model) {
        Page<SanPhamResponse> sanPhamResponsesActive = chiTietSanPhamService.searchNameOrMaActiveSp(search, pageNo, 3);
        model.addAttribute("size", sanPhamResponsesActive.getSize());
        model.addAttribute("totalPages", sanPhamResponsesActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listSanPhamActive", sanPhamResponsesActive);
        return "admin/san_pham/trang_chu_san_pham";
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageSanPhamActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<SanPhamResponse> sanPhamResponsesActive = chiTietSanPhamService.pageSanPhamActive(pageNo, 3);
        model.addAttribute("size", sanPhamResponsesActive.getSize());
        model.addAttribute("totalPages", sanPhamResponsesActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listSanPhamActive", sanPhamResponsesActive);
        return "admin/san_pham/trang_chu_san_pham";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        chiTietSanPhamService.deleteSanPham(id, LocalDate.now());
        return "redirect:/admin/psg/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/revert/{id}")
    public String revertSanPham(@PathVariable("id") Integer id) {
        chiTietSanPhamService.revertSanPham(id, LocalDate.now());
        return "redirect:/admin/psg/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/delete-chi-tiet-san-pham/{id}")
    public String deleteChiTietSanPham(@PathVariable("id") Integer id) {
        chiTietSanPhamService.deleteChiTietSanPham(id);
        return "redirect:/admin/psg/chi-tiet-san-pham/view-add";
    }



//    @GetMapping("/pageInActive/{pageNo}")
//    public String viewRevert(@PathVariable("pageNo") Integer pageNo, Model model) {
//        Page<SanPhamResponse> sanPhamResponsesActive = chiTietSanPhamService.pageSanPhamInActive(pageNo, 3);
//        model.addAttribute("size", sanPhamResponsesActive.getSize());
//        model.addAttribute("totalPages", sanPhamResponsesActive.getTotalPages());
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("listSanPhamActive", sanPhamResponsesActive);
//        return "admin/san_pham/revert_san_pham";
//    }


}
