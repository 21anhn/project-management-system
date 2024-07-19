package com._anhn.controllers;

import com._anhn.dtos.IssueDTO;
import com._anhn.models.Issue;
import com._anhn.models.User;
import com._anhn.request.IssueRequest;
import com._anhn.response.MessageResponse;
import com._anhn.services.IssueService;
import com._anhn.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{issueId}")
    public ResponseEntity<?> getIssueById(@PathVariable("issueId") Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getIssueByProjectId(@PathVariable("projectId") Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<?> createIssue(@RequestBody IssueRequest issueRequest, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findByUserId(tokenUser.getId());

        Issue createdIssue = issueService.createIssue(issueRequest, tokenUser);
        IssueDTO issueDTO = new IssueDTO();
        modelMapper.map(createdIssue, issueDTO);
        return ResponseEntity.ok(issueDTO);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<?> deleteIssue(@PathVariable("issueId") Long issueId, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, tokenUser.getId());

        MessageResponse messageResponse = new MessageResponse("Issue deleted");
        return ResponseEntity.ok(messageResponse);
    }


    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<?> addUserToIssue(@PathVariable("issueId") Long issueId, @PathVariable("userId") Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issuedId}/status/{status}")
    public ResponseEntity<?> updateIssueStatus(@PathVariable Long issuedId, @PathVariable String status) throws Exception {
        Issue issue = issueService.updateStatus(issuedId, status);
        return ResponseEntity.ok(issue);
    }
}
