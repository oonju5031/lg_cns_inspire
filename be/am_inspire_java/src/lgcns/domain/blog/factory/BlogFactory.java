package lgcns.domain.blog.factory;

import java.util.HashMap;
import java.util.Map;

import lgcns.domain.blog.ctrl.BlogInsertCtrl;
import lgcns.domain.blog.ctrl.BlogListCtrl;
import lgcns.domain.blog.ctrl.BlogSearchCtrl;
import lgcns.domain.blog.service.BlogService;

/*
Singleton을 사용하여 무분별한 인스턴스 생성을 방지
- 생성자의 접근 제어자를 private으로 설정
- 자기 자신의 타입을 반환하는 메소드를 static으로 생성
각 컨트롤러 객체를 생성하고 키, 값을 Map 관리
*/
public class BlogFactory {

    private static BlogFactory instance;
    private Map<String, Object> map;
    private BlogService service;

    private BlogFactory() {
        map = new HashMap<>();
        service = new BlogService();
        map.put("insert", new BlogInsertCtrl(service));
        map.put("list", new BlogListCtrl(service));
        map.put("search", new BlogSearchCtrl(service));
    }

    public static BlogFactory getInstance() {
        if (instance == null) {
            instance = new BlogFactory();
        }
        return instance;
    }
    
    public Object getBlogBean(String requestPath) {
        return map.get(requestPath);
    }
    
}
