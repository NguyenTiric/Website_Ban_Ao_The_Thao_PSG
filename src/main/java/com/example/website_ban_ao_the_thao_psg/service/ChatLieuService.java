package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateChatLieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateChatLieuRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.ChatLieuResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ChatLieuService {
    Page<ChatLieuResponse> pageChatLieuResponse(Integer pageNo, Integer size);

    ChatLieuResponse add(CreateChatLieuRequest createChatLieuRequest);

    ChatLieuResponse update(UpdateChatLieuRequest updateChatLieuRequest);

    ChatLieuResponse getOne(Integer id);

    ChatLieuResponse delete(UpdateChatLieuRequest updateChatLieuRequest, Integer id);
}
