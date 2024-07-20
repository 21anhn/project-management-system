package com._anhn.controllers;

import com._anhn.models.Comment;
import com._anhn.models.User;
import com._anhn.request.CommentRequest;
import com._anhn.response.MessageResponse;
import com._anhn.services.CommentService;
import com._anhn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        Comment comment = commentService.createComment(commentRequest.getIssueId(), tokenUser.getId(), commentRequest.getContent());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        commentService.deleteComment(commentId, tokenUser.getId());
        MessageResponse messageResponse = new MessageResponse("Comment deleted successfully!");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/{issuedId}")
    public ResponseEntity<?> getCommentByIssueId(@PathVariable Long issuedId) throws Exception {
        List<Comment> commentList = commentService.findCommentsByIssueId(issuedId);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }


}
