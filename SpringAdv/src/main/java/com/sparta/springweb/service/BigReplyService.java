package com.sparta.springweb.service;

import com.sparta.springweb.dto.BigReplyRequestDto;
import com.sparta.springweb.dto.ReplyRequestDto;
import com.sparta.springweb.model.BigReply;
import com.sparta.springweb.model.Reply;
import com.sparta.springweb.repository.BigReplyRepository;
import com.sparta.springweb.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BigReplyService {

    private final BigReplyRepository BigReplyRepository;

    public List<BigReply> getBigReply(Long replyId) {
        return BigReplyRepository.findAllByReplyidOrderByCreatedAtDesc(replyId);
    }
    @Transactional
    public BigReply createBigReply(BigReplyRequestDto requestDto, Long userId) {
        String bigreplyCheck = requestDto.getBigreply();
        if (bigreplyCheck.contains("script")|| bigreplyCheck.contains("<")||bigreplyCheck.contains(">")){
            BigReply bigreply = new BigReply(requestDto, userId,"xss 안돼요,, 하지마세요ㅠㅠ");
            BigReplyRepository.save(bigreply);
            return bigreply;
        }
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        BigReply bigreply = new BigReply(requestDto, userId);
        BigReplyRepository.save(bigreply);
        return bigreply;
    }

}
