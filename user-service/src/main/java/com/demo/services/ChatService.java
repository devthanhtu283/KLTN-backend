package com.demo.services;

import com.demo.dtos.ChatDTO;
import com.demo.entities.Chat;

import java.util.List;

public interface ChatService {
    public List<Integer> getReceiverIdsByUserId(Integer userId);
    public List<ChatDTO> getMessagesBetweenUsers(Integer senderId, Integer receiverId);
    public ChatDTO save(ChatDTO chatDTO);

}
