package com.example.blog.comment.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.blog.comment.domain.dto.CommentRequestDTO;
import com.example.blog.comment.domain.dto.CommentResponseDTO;

@Mapper
public interface CommentMapper {

    public int                      insertRow(CommentRequestDTO request) ;
    public List<CommentResponseDTO> selectRow(Integer blogId) ; 

    public int                      deleteRow(Integer commentId) ;
    

}
