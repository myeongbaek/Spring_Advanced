package com.sparta.springweb.service;

import com.sparta.springweb.dto.ContentsRequestDto;
import com.sparta.springweb.dto.ContentsResponseDto;
import com.sparta.springweb.model.Contents;
import com.sparta.springweb.repository.ContentsRepository;
import com.sparta.springweb.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContentsService {

    private final ContentsRepository ContentsRepository;
    private final ReplyRepository ReplyRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Contents createContents(ContentsRequestDto requestDto, String username) {
        String contentsCheck = requestDto.getContents();
        String titleCheck = requestDto.getTitle();
        if (contentsCheck.contains("script")||contentsCheck.contains("<")||contentsCheck.contains(">")){
            Contents contents = new Contents(requestDto,username,"xss 안돼요,,하지마세요ㅠㅠ");
            ContentsRepository.save(contents);
            return contents;
        }
        if (titleCheck.contains("script")||titleCheck.contains("<")||titleCheck.contains(">")) {
            Contents contents = new Contents("xss 안돼요,,하지마세요ㅠㅠ", username, "xss 안돼요,,하지마세요ㅠㅠ");
            ContentsRepository.save(contents);
            return contents;
        }
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Contents contents = new Contents(requestDto, username);
        ContentsRepository.save(contents);
        return contents;
    }

    @Transactional
    public Long update(Long id, ContentsRequestDto requestDto) {
        Contents Contents = ContentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Contents.update(requestDto);
        return Contents.getId();
    }

//    public List<Contents> getContents() {
//        List<Contents> contents =  ContentsRepository.findAllByOrderByCreatedAtDesc();
//        return contents;
//
//    }

    // 게시글 조회
    public List<ContentsResponseDto> getContents() {
        List<Contents> contents = ContentsRepository.findAllByOrderByCreatedAtDesc();
        List<ContentsResponseDto> listContents = new ArrayList<>();
        for (Contents content : contents){
            int countReply =  ReplyRepository.countByPostid(content.getId());
            ContentsResponseDto contentsResponseDto = ContentsResponseDto.builder()
                    .content(content)
                    .countReply(countReply)
                    .build();
            listContents.add(contentsResponseDto);
        }
        return listContents;
    }
}