package lgcns.domain.blog.service;

import lgcns.domain.blog.dao.BlogMapper;
import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lombok.Builder;

@Builder
public class BlogService {

    public int insert(BlogRequestDTO request) {
        System.out.println(">>> BlogService insert()");

        BlogMapper mapper = BlogMapper.builder().build();
        int result = mapper.insert(request);

        return result;
    }
    
}
