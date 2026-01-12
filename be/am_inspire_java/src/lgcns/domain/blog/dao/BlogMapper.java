package lgcns.domain.blog.dao;

import java.util.ArrayList;
import java.util.List;

import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lgcns.domain.blog.domain.dto.BlogResponseDTO;

/*
Mybatis를 이용하여 DB 작업을 전담하는 객체
@Mapper
*/
public class BlogMapper {

    private List<BlogRequestDTO> blogs;

    public BlogMapper() {
        blogs = new ArrayList<>();
    }

    public int insert(BlogRequestDTO request) {
        System.out.println(">>> Mapper insert()");
        blogs.add(request);
        return 1;
    }

    public List<BlogResponseDTO> list() {
        System.out.println(">>> Mapper list()");

        // BlogRequestDTO의 List를 BlogResponseDTO의 List로 변경
        // List<BlogResponseDTO> responseList = new ArrayList<>();

        // for (BlogRequestDTO requestItem : blogs) {
        //     BlogResponseDTO responseDTO = BlogRequestDTO.toResponseDTO(requestItem);
        //     responseList.add(responseDTO);
        // }

        // return responseList;

        // Stream을 사용해 위 내용을 간소화
        return blogs.stream()
                    .map(BlogRequestDTO::toResponseDTO)
                    .toList();
    }

    public List<BlogResponseDTO> search(String writer) {
        System.out.println(">>> Mapper search()");

        return blogs.stream()
                    .filter(dto -> dto.getWriter().equals(writer))
                    .map(BlogRequestDTO::toResponseDTO)
                    .toList();
    }
}
