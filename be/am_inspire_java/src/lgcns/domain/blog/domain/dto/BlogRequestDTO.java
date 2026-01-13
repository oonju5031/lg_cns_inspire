package lgcns.domain.blog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class BlogRequestDTO {
    private int id;
    private String title, content, writer;

    // 정적 팩토리 메소드 패턴
    public static BlogResponseDTO toResponseDTO(BlogRequestDTO request) {

        return BlogResponseDTO.builder()
                            .id(request.getId())
                            .title(request.getTitle())
                            .content(request.getContent())
                            .writer(request.getWriter())
                            .build();
    }
    
}
