package lgcns.domain.blog.ctrl;

import lgcns.domain.blog.service.BlogService;
import lombok.Builder;

@Builder
public class BlogDeleteCtrl {
    
    public int delete(int id) {
        System.out.println(">>> Controller delete()");
        
        BlogService service = BlogService.builder().build();

        return service.delete(id);
    }
}
