package com.sparta.springweb.service;

import com.sparta.springweb.dto.ReplyRequestDto;
import com.sparta.springweb.model.Reply;
import com.sparta.springweb.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository ReplyRepository;

    // 댓글 조회
    public List<Reply> getReply(Long postId) {
        return ReplyRepository.findAllByPostidOrderByCreatedAtDesc(postId);
    }

    // 댓글 작성
    @Transactional
    public Reply createReply(ReplyRequestDto requestDto, Long userId) {
        String replyCheck = requestDto.getReply();
        if (replyCheck.contains("script")|| replyCheck.contains("<")||replyCheck.contains(">")){
            Reply reply = new Reply(requestDto, userId,"xss 안돼요,, 하지마세요ㅠㅠ");
            ReplyRepository.save(reply);
            return reply;
        }
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Reply reply = new Reply(requestDto, userId);
        ReplyRepository.save(reply);
        return reply;
    }

    // 댓글 수정
    @Transactional
    public void update(Long id, ReplyRequestDto requestDto) {
        Reply Reply = ReplyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        Reply.update(requestDto);
    }

    // 댓글 개수
    public int countReply(Long postId){
        return ReplyRepository.countByPostid(postId);
    }
}