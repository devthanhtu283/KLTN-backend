package com.demo.services;

import com.demo.dtos.ChatDTO;
import com.demo.entities.Chat;
import com.demo.entities.User;
import com.demo.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public List<Integer> getReceiverIdsByUserId(Integer userId) {
        return chatRepository.findAllReceiverIdsByUserId(userId);    }

    @Override
    public List<ChatDTO> getMessagesBetweenUsers(Integer senderId, Integer receiverId) {
        List<Chat> messages = chatRepository.findMessagesBetweenUsers(senderId, receiverId);
        return messages.stream()
                .map(ChatDTO::new) // Chuyển từ Chat sang ChatDTO
                .collect(Collectors.toList());    }

    @Override
    public ChatDTO save(ChatDTO chatDTO) {
        Chat chat = new Chat();
        User user1 = new User();
        user1.setId(chatDTO.getSenderId());
        chat.setUserBySenderId(user1);
        User user2 = new User();
        user2.setId(chatDTO.getReceiverId());
        chat.setUserByReceiverId(user2);
        chat.setSenderRole(chatDTO.getSenderRole());
        chat.setReceiverRole(chatDTO.getReceiverRole());
        chat.setMessage(chatDTO.getMessage());
        chat.setTime(new Date());
        chat.setStatus(chatDTO.isStatus());
        chat = chatRepository.save(chat);
        return new ChatDTO(chat);
    }

    @Override
    public List<ChatDTO> getRecentMessages(Integer userId) {
        List<Chat> messages = chatRepository.findRecentMessagesByUserId(userId);
        return messages.stream()
                .map(ChatDTO::new) // Chuyển từ Chat sang ChatDTO
                .collect(Collectors.toList());
    }
}
