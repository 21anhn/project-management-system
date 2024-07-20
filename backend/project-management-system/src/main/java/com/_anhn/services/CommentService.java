package com._anhn.services;

import com._anhn.models.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String content) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentsByIssueId(Long issueId) throws Exception;
}
