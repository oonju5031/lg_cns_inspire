package lgcns.domain.blog.ctrl;

import lgcns.domain.blog.service.BlogService;

public class BlogUpdateCtrl {

    private BlogService service;

    public BlogUpdateCtrl() {
        service = new BlogService();
    }

    public BlogUpdateCtrl(BlogService service) {
        this.service = service;
    }
    
    public int update(String title, String content, int id) {
        System.out.println(">>> Controller update()");

        return service.update(title, content, id);
    }
}
