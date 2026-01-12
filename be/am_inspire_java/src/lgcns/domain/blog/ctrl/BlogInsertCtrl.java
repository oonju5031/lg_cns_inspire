package lgcns.domain.blog.ctrl;

import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lgcns.domain.blog.service.BlogService;

public class BlogInsertCtrl {

    private BlogService service;

    public BlogInsertCtrl() {
        service = new BlogService();
    }

    public BlogInsertCtrl(BlogService service) {
        this.service = service;
    }
    
    public int insert(BlogRequestDTO request) {
        System.out.println(">>> Controller insert()");

        return service.insert(request);
    }
}
