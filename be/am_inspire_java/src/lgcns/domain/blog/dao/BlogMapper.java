package lgcns.domain.blog.dao;

import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lombok.Builder;

/*
Mybatis를 이용하여 DB 작업을 전담하는 객체
@Mapper
*/
@Builder
public class BlogMapper {
    public int insert(BlogRequestDTO request) {
        System.out.println("BlogMapper insert()");

        return 0;
    }
}
