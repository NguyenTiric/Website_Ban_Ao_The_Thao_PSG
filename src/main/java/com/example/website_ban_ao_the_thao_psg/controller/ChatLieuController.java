package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.ChatLieu;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChatLieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChatLieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChatLieuResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChatLieuService;
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

@Controller
@RequestMapping("/admin/psg/chat-lieu")
public class ChatLieuController {

    @Autowired
    ChatLieuService chatLieuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("chatLieu", new ChatLieu());
        return pageChatLieuActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageChatLieuActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("chatLieu", new ChatLieu());
        Page<ChatLieuResponse> chatLieuResponsePageActive = chatLieuService.pageChatLieuActive(pageNo, 3);
        model.addAttribute("size", chatLieuResponsePageActive.getSize());
        model.addAttribute("totalPages", chatLieuResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChatLieuActive", chatLieuResponsePageActive);
        return "admin/chat_lieu/trang_chu_chat_lieu";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageChatLieuInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<ChatLieuResponse> chatLieuResponsePageInActive = chatLieuService.pageChatLieuInActive(pageNo, 3);
        model.addAttribute("size", chatLieuResponsePageInActive.getSize());
        model.addAttribute("totalPages", chatLieuResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChatLieuInActive", chatLieuResponsePageInActive);
        return "admin/chat_lieu/revert_chat_lieu";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        ChatLieuResponse chatLieuResponse = chatLieuService.getOne(id);
        model.addAttribute("chatLieu", chatLieuResponse);
        return "admin/chat_lieu/view_update_chat_lieu";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("chatLieu", new CreateChatLieuRequest());
        return "admin/chat_lieu/view_add_chat_lieu";
    }
    @PostMapping("/delete/{id}")
    public String deleteChatLieu(@PathVariable("id") Integer id,HttpSession session) {
        chatLieuService.deleteChatLieu(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/chat-lieu/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertChatLieu(@PathVariable("id") Integer id,HttpSession session) {
        chatLieuService.revertChatLieu(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/chat-lieu/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("chatLieu") CreateChatLieuRequest createChatLieuRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("chatLieu", createChatLieuRequest);
            return "admin/chat_lieu/view_add_chat_lieu";
        }
        chatLieuService.add(createChatLieuRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/chat-lieu/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("chatLieu") UpdateChatLieuRequest updateChatLieuRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("chatLieu", updateChatLieuRequest);
            return "admin/chat_lieu/view_update_chat_lieu";
        }
        chatLieuService.update(updateChatLieuRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/chat-lieu/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("chatLieu", new ChatLieu());
        Page<ChatLieuResponse> chatLieuResponsePage = chatLieuService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", chatLieuResponsePage.getSize());
        model.addAttribute("totalPages", chatLieuResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChatLieuActive", chatLieuResponsePage);
        return "admin/chat_lieu/trang_chu_chat_lieu";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("chatLieu", new ChatLieu());
        Page<ChatLieuResponse> chatLieuResponsePage = chatLieuService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", chatLieuResponsePage.getSize());
        model.addAttribute("totalPages", chatLieuResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listChatLieuInActive", chatLieuResponsePage);
        return "admin/chat_lieu/revert_chat_lieu";
    }
}
