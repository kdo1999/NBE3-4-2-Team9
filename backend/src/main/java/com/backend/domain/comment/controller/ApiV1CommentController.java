package com.backend.domain.comment.controller;

import com.backend.domain.comment.dto.request.CommentRequestDto;
import com.backend.domain.comment.dto.response.CommentCreateResponseDto;
import com.backend.domain.comment.dto.response.CommentModifyResponseDto;
import com.backend.domain.comment.dto.response.CommentResponseDto;
import com.backend.domain.comment.service.CommentService;
import com.backend.global.response.GenericResponse;
import com.backend.global.security.custom.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class ApiV1CommentController {

    private final CommentService commentService;

    @GetMapping
    public GenericResponse<Page<CommentResponseDto>> getComments(
            @PathVariable("postId") Long postId,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Page<CommentResponseDto> commentPage = commentService
                .getComments(postId, page, size, customUserDetails.getSiteUser());

        return GenericResponse.of(
                true,
                HttpStatus.OK.value(),
                commentPage,
                "댓글을 조회합니다."
        );


    }

    @PostMapping
    public GenericResponse<CommentCreateResponseDto> createComment(
            @PathVariable("postId") Long postId,
            @Valid @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails user) {

        CommentCreateResponseDto commentResponseDto = commentService.createComment(requestDto, postId, user);

        return GenericResponse.of(
                true,
                HttpStatus.CREATED.value(),
                commentResponseDto,
                "댓글이 정상적으로 생성되었습니다."
        );
    }

    @PatchMapping("/{id}")
    public GenericResponse<CommentModifyResponseDto> modifyComment(
            @PathVariable("postId") Long postId,
            @PathVariable("id") Long commentId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal CustomUserDetails user
    ) {

        CommentModifyResponseDto commentModifyResponseDto = commentService.modifyComment(postId,
                commentId, commentRequestDto, user);

        return GenericResponse.of(
                true,
                HttpStatus.OK.value(),
                commentModifyResponseDto,
                "댓글 수정에 성공하였습니다."
        );
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Void> deleteComment(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal CustomUserDetails user) {

        commentService.deleteComment(id, user.getSiteUser());

        return GenericResponse.of(
                true,
                HttpStatus.OK.value(),
                "댓글이 삭제되었습니다."
        );
    }


}