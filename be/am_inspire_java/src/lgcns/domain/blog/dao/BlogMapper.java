package lgcns.domain.blog.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

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
        request.setId(blogs.size() + 1);
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

    public Optional<BlogResponseDTO> findById(int id) {
        System.out.println(">>> Mapper read()");

        return blogs.stream()
                    .filter(obj -> obj.getId() == id)
                    .findFirst()
                    .map(BlogRequestDTO::toResponseDTO);
    }

    public int deleteById(int id) {
        System.out.println(">>> Mapper delete()");

        boolean flag = blogs.removeIf(obj -> obj.getId() == id);

        if (flag) {
            return 1;
        } else {
            return 0;
        }
    }

    public int update(String title, String content, int id) {
        System.out.println(">>> Mapper update()");

        /* setter를 사용할 수 없는 경우*/
        // int targetId = IntStream.range(0, blogs.size())
        //                         .filter(i -> blogs.get(i).getId() == id)
        //                         .findFirst()
        //                         .orElseThrow();

        // BlogRequestDTO newDTO = BlogRequestDTO.builder()
        //                                     .id(targetId + 1)  // 1부터 시작
        //                                     .title(title)
        //                                     .content(content)
        //                                     .writer(blogs.get(targetId).getWriter())
        //                                     .build();

        // blogs.set(targetId, newDTO);

        // return 1;

        /* setter를 사용할 수 있는 경우 -> 함수 매개변수도 따로 사용하는 대신 FrontCtrl에서 BlogRequestDTO로 build해주는 것이 더 적절함 */
        return blogs.stream()
                    .filter(obj -> obj.getId() == id)
                    .findFirst()
                    .map(obj -> {
                        obj.setTitle(title);
                        obj.setContent(content);
                        return 1;
                    })
                    .orElse(0);
    }
}
