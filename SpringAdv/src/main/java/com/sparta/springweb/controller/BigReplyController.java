package com.sparta.springweb.controller;

import com.sparta.springweb.dto.BigReplyRequestDto;
import com.sparta.springweb.dto.ContentsRequestDto;
import com.sparta.springweb.dto.ReplyRequestDto;
import com.sparta.springweb.model.BigReply;
import com.sparta.springweb.model.Contents;
import com.sparta.springweb.security.UserDetailsImpl;
import com.sparta.springweb.service.BigReplyService;
import com.sparta.springweb.service.ContentsService;
import com.sparta.springweb.service.ReplyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class BigReplyController {

    private BigReplyService BigReplyService;

    // 대댓글 작성
    @PostMapping("/api/bigreply")
    public boolean createBigReply(@RequestBody BigReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        if (userDetails != null) {
            Long userId = userDetails.getUser().getId();
            BigReplyService.createBigReply(requestDto, userId);
            return true;
        }
        return false;
    }
}
