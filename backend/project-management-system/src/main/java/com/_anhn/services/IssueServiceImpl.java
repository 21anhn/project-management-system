package com._anhn.services;

import com._anhn.models.Issue;
import com._anhn.models.Project;
import com._anhn.models.User;
import com._anhn.repositories.IssueRepository;
import com._anhn.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()) {
            return issue.get();
        }
        throw new Exception("No issues find by issueId: " + issueId);

    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issue, User user) throws Exception {
        Project project = projectService.getProjectById(issue.getProjectId());
        Issue newIssue = new Issue();
        newIssue.setTitle(issue.getTitle());
        newIssue.setDescription(issue.getDescription());
        newIssue.setStatus(issue.getStatus());
        newIssue.setProject(project);
        newIssue.setPriority(issue.getPriority());
        newIssue.setDueDate(issue.getDueDate());

        return issueRepository.save(newIssue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        getIssueById(issueId);
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findByUserId(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issuedId, String status) throws Exception {
        Issue issue = getIssueById(issuedId);
        issue.setStatus(status);
        return issueRepository.save(issue);
    }
}
