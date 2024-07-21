package com._anhn.services;

import com._anhn.models.Invitation;
import com._anhn.models.User;
import com._anhn.repositories.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {
        String invitationToken = UUID.randomUUID().toString();
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);

        String invitationLink = "http://localhost:5173/accept_invitation?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);

        if (invitation == null) {
            throw new Exception("Invalid invitation token!");
        }

        User user = userService.findUserByEmail(invitation.getEmail());

        if (user == null) {
            throw new Exception("User not found!");
        }

        projectService.addUserToProject(invitation.getProjectId(), user.getId());

        return invitation;
    }

    @Override
    public String getTokenByUserEmail(String email) {
        Invitation invitation = invitationRepository.findByEmail(email);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepository.findByToken(token);
        invitationRepository.delete(invitation);
    }
}
