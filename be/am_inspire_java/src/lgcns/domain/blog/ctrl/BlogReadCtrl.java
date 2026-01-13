package lgcns.domain.blog.ctrl;

import java.util.Optional;

import lgcns.domain.blog.domain.dto.BlogResponseDTO;
import lgcns.domain.blog.service.BlogService;

public class BlogReadCtrl {

    private BlogService service;

    public BlogReadCtrl() {
        service = new BlogService();
    }

    public BlogReadCtrl(BlogService service) {
        this.service = service;
    }
    
    public Optional<BlogResponseDTO> read(int id) {
        System.out.println(">>> Controller read()");

        return service.read(id);
    }
    
}
