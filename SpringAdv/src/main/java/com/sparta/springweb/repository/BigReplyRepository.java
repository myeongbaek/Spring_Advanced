package com.sparta.springweb.repository;

import com.sparta.springweb.model.BigReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BigReplyRepository extends JpaRepository<BigReply, Long> {
    List<BigReply> findAllByReplyidOrderByCreatedAtDesc(Long replyId);

    int countByReplyid(Long replyId);
}
