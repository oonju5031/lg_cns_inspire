package lgcns.domain.blog.ctrl;

import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lgcns.domain.blog.service.BlogService;
import lombok.Builder;

@Builder
public class BlogUpdateCtrl {
    
    public int update(BlogRequestDTO request) {
        System.out.println(">>> Controller update()");

        BlogService service = BlogService.builder().build();

        return service.update(request);
    }
}
