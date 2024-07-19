package com._anhn.services;

import com._anhn.models.Issue;
import com._anhn.models.User;
import com._anhn.request.IssueRequest;

import java.util.List;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issuedId, String status) throws Exception;
}
