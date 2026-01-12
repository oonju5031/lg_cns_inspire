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
    private String title, content, writer;
}
