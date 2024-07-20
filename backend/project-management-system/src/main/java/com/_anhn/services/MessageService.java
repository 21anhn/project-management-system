package com._anhn.services;

import com._anhn.models.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long userId, Long projectId, String content) throws Exception;

    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
