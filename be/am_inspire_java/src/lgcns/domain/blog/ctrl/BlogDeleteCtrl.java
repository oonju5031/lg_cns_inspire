package lgcns.domain.blog.ctrl;

import lgcns.domain.blog.service.BlogService;

public class BlogDeleteCtrl {

    private BlogService service;

    public BlogDeleteCtrl() {
        service = new BlogService();
    }

    public BlogDeleteCtrl(BlogService service) {
        this.service = service;
    }
    
    public int delete(int id) {
        System.out.println(">>> Controller delete()");

        return service.delete(id);
    }
}
