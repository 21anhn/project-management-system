package com._anhn.services;

import com._anhn.models.Chat;
import com._anhn.models.Message;
import com._anhn.models.User;
import com._anhn.repositories.MessageRepository;
import com._anhn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Message sendMessage(Long userId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found with id: " + userId));
        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setSender(sender);
        message.setChat(chat);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getProjectById(projectId).getChat();
        return chat.getMessages();
    }
}
