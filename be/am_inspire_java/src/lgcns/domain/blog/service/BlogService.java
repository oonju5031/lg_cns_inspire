package lgcns.domain.blog.service;

import java.util.List;

import lgcns.domain.blog.dao.BlogMapper;
import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lgcns.domain.blog.domain.dto.BlogResponseDTO;

public class BlogService {

    private BlogMapper dao;

    public BlogService() {
        dao = new BlogMapper();
    }

    public int insert(BlogRequestDTO request) {
        System.out.println(">>> Service insert()");

        return dao.insert(request);
    }

    public List<BlogResponseDTO> list() {
        System.out.println(">>> Service list()");
        
        return dao.list();
    }

    public List<BlogResponseDTO> search(String writer) {
        System.out.println(">>> Service search()");

        return dao.search(writer);
    }
    
}
