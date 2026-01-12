package lgcns.domain.blog.ctrl;

import java.util.List;

import lgcns.domain.blog.domain.dto.BlogResponseDTO;
import lgcns.domain.blog.service.BlogService;

public class BlogListCtrl {

    private BlogService service;

    public BlogListCtrl() {
        service = new BlogService();
    }

    public BlogListCtrl(BlogService service) {
        this.service = service;
    }

    public List<BlogResponseDTO> list() {
        System.out.println(">>> Controller list()");
        return service.list();
    }
}
