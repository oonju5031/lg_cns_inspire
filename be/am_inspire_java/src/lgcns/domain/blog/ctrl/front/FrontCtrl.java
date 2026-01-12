package lgcns.domain.blog.ctrl.front;

import java.util.List;

import lgcns.domain.blog.ctrl.BlogInsertCtrl;
import lgcns.domain.blog.ctrl.BlogListCtrl;
import lgcns.domain.blog.ctrl.BlogSearchCtrl;
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
    
}
