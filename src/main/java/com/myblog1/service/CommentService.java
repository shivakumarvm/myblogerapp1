package com.myblog1.service;

import com.myblog1.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(Long postId, Long commentId);

    public CommentDto updateComment(long postId, long id, CommentDto commentDto);
    void deleteComment(Long postId, Long commentId);


}

