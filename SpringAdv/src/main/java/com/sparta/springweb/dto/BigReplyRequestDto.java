package com.sparta.springweb.dto;

import lombok.Getter;

@Getter
public class BigReplyRequestDto {
    private Long postid;
    private Long replyid;
    private String username;
    private String bigreply;
    private Long userId;
}
