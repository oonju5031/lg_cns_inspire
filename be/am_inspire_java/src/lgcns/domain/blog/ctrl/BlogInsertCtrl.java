package lgcns.domain.blog.ctrl;

import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lgcns.domain.blog.service.BlogService;
import lombok.Builder;

@Builder
public class BlogInsertCtrl {
    
    public int insert(BlogRequestDTO request) {
        System.out.println(">>> Controller insert()");

        BlogService service = BlogService.builder().build();

        return service.insert(request);
    }
}
