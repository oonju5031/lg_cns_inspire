package lgcns.domain.blog.ctrl.front;

import java.util.List;
import java.util.Optional;

import lgcns.domain.blog.ctrl.BlogDeleteCtrl;
import lgcns.domain.blog.ctrl.BlogInsertCtrl;
import lgcns.domain.blog.ctrl.BlogListCtrl;
import lgcns.domain.blog.ctrl.BlogReadCtrl;
import lgcns.domain.blog.ctrl.BlogSearchCtrl;
import lgcns.domain.blog.ctrl.BlogUpdateCtrl;
import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lgcns.domain.blog.domain.dto.BlogResponseDTO;
import lgcns.domain.blog.factory.BlogFactory;

public class FrontCtrl {

    private BlogFactory factory;

    public FrontCtrl() {
        factory = BlogFactory.getInstance();
    }

    public int insert(String requestPath, String title, String content, String writer) {
        System.out.println(">>> Factory Insert");

        BlogInsertCtrl ctrl = (BlogInsertCtrl) factory.getBlogBean(requestPath);

        BlogRequestDTO request = BlogRequestDTO.builder()
                                                .title(title)
                                                .content(content)
                                                .writer(writer)
                                                .build();

        return ctrl.insert(request);
    }

    public List<BlogResponseDTO> list(String requestPath) {
        BlogListCtrl ctrl = (BlogListCtrl) factory.getBlogBean(requestPath);
        
        return ctrl.list();
    }

    public List<BlogResponseDTO> search(String requestPath, String writer) {
        BlogSearchCtrl ctrl = (BlogSearchCtrl) factory.getBlogBean(requestPath);

        return ctrl.search(writer);
    }

    public Optional<BlogResponseDTO> read(String requestPath, int id) {
        BlogReadCtrl ctrl = (BlogReadCtrl) factory.getBlogBean(requestPath);

        return ctrl.read(id);
    }

    public int delete(String requestPath, int id) {
        BlogDeleteCtrl ctrl = (BlogDeleteCtrl) factory.getBlogBean(requestPath);

        return ctrl.delete(id);
    }

    public int update(String requestPath, String title, String content, int id) {
        BlogUpdateCtrl ctrl = (BlogUpdateCtrl) factory.getBlogBean(requestPath);

        return ctrl.update(title, content, id);
    }
    
}
