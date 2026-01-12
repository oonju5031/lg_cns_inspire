package lgcns.domain.blog.ctrl;

import java.util.List;

import lgcns.domain.blog.domain.dto.BlogResponseDTO;
import lgcns.domain.blog.service.BlogService;

public class BlogSearchCtrl {

    private BlogService service;

    public BlogSearchCtrl() {
        service = new BlogService();
    }

    public BlogSearchCtrl(BlogService service) {
        this.service = service;
    }
    
    public List<BlogResponseDTO> search(String writer) {
        System.out.println(">>> Controller search()");

        return service.search(writer);
    }
}
