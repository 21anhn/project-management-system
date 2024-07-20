package com._anhn.services;

import com._anhn.models.Comment;
import com._anhn.models.Issue;
import com._anhn.models.User;
import com._anhn.repositories.CommentRepository;
import com._anhn.repositories.IssueRepository;
import com._anhn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        Optional<User> user = userRepository.findById(userId);

        if (issue.isEmpty()) {
            throw new Exception("Issue not found with id: " + issueId);
        }
        if (user.isEmpty()) {
            throw new Exception("User not found with id: " + userId);
        }

        Issue issueObj = issue.get();
        User userObj = user.get();

        Comment comment = new Comment();
        comment.setIssue(issueObj);
        comment.setUser(userObj);
        comment.setContent(content);
        comment.setCreatedDateTime(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        issue.get().getComments().add(savedComment);
        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);
        Optional<User> user = userRepository.findById(userId);

        if (comment.isEmpty()) {
            throw new Exception("Comment not found with id: " + commentId);
        }
        if (user.isEmpty()) {
            throw new Exception("User not found with id: " + userId);
        }

        Comment commentObj = comment.get();
        User userObj = user.get();

        if (commentObj.getUser().equals(userObj)) {
            commentRepository.delete(commentObj);
        } else {
            throw new Exception("User does not have permission to delete this comment!");
        }
    }

    @Override
    public List<Comment> findCommentsByIssueId(Long issueId) throws Exception {
        return commentRepository.findByIssueId(issueId);
    }


}
