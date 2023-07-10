package com.example.website_ban_ao_the_thao_psg.service.impl;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChatLieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChatLieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChatLieuResponse;
import com.example.website_ban_ao_the_thao_psg.service.ChatLieuService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ChatLieuServiceImpl implements ChatLieuService {
    @Override
    public Page<ChatLieuResponse> pageChatLieuResponse(Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public ChatLieuResponse add(CreateChatLieuRequest createChatLieuRequest) {
        return null;
    }

    @Override
    public ChatLieuResponse update(UpdateChatLieuRequest updateChatLieuRequest) {
        return null;
    }

    @Override
    public ChatLieuResponse getOne(Integer id) {
        return null;
    }

    @Override
    public ChatLieuResponse delete(UpdateChatLieuRequest updateChatLieuRequest, Integer id) {
        return null;
    }
}
