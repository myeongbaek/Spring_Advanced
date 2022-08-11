package com.sparta.springweb.model;

import com.sparta.springweb.dto.BigReplyRequestDto;
import com.sparta.springweb.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class BigReply extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long replyid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String bigreply;

    @Column(nullable = false)
    private Long userId;

    public BigReply(BigReplyRequestDto requestDto, Long userId, String reply) {
        this.replyid = requestDto.getReplyid();
        this.bigreply = bigreply;
        this.username = requestDto.getUsername();
        this.userId = userId;
    }
    public BigReply(BigReplyRequestDto requestDto, Long userId) {
        this.replyid = requestDto.getReplyid();
        this.bigreply = requestDto.getBigreply();
        this.username = requestDto.getUsername();
        this.userId = userId;
    }
}
