package com._anhn.services;

import com._anhn.models.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

    void sendInvitation(String email, Long projectId) throws MessagingException;

    Invitation acceptInvitation(String token, Long userId) throws Exception;

    String getTokenByUserEmail(String email);

    void deleteToken(String token);
}
