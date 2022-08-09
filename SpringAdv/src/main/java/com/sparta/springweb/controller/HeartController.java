package com.sparta.springweb.controller;

import com.sparta.springweb.repository.ContentsRepository;
import com.sparta.springweb.repository.ReplyRepository;
import com.sparta.springweb.service.ContentsService;
import com.sparta.springweb.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HeartController {
    private final ContentsRepository contentsRepository;
    private final ContentsService contentsService;
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;


    @PutMapping("/api/heartplus/{id}")
    public Long heartUp(@PathVariable Long id){
        contentsService.updateHeart(id, 1);
        return id;
    }

    @PutMapping("/api/heartminus/{id}")
    public Long heartDown(@PathVariable Long id){
        contentsService.updateHeart(id, -1);
        return id;
    }

}
