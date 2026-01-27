package com.example.blog.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.blog.repository.BlogMapper;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import com.example.blog.comment.repository.CommentMapper;
import com.example.blog.comment.domain.dto.CommentRequestDTO;
import com.example.blog.comment.domain.dto.CommentResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    // 의존성주입을 필요로 함(CommentMapper or CommentRepository)
    private final BlogMapper blogMapper ;
    private final CommentMapper commentMapper;
    
    /*
    데이터베이스 작업의 논리적인 단위로 트랜잭션처리가 필요
    commit, rollback 
    */
    @Transactional
    public List<CommentResponseDTO> write(CommentRequestDTO request) {
        System.out.println(">>>> blog/comment service write"); 
        List<CommentResponseDTO> list = null ; 
        BlogResponseDTO blog = 
            blogMapper.readRow(request.getBlogId());
        if(blog != null) {
            int flag = commentMapper.insertRow(request);  
            System.out.println(">>>> blog/comment service write flag : "+flag); 
            System.out.println(">>>> blog/comment service request id : "+request.getCommentId()); 
            if(flag == 1) {
                list = 
                    commentMapper.selectRow(request.getBlogId());
                return list ;    
            }
        } else {
            throw new RuntimeException("Blog not Found!!"); 
        }
        return list ;   
    }

    @Transactional
    public int delete(Integer commentId) {
        System.out.println(">>>> blog/comment service delete");
        return commentMapper.deleteRow(commentId) ; 
    }

}
