package lgcns.domain.blog.domain.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class BlogResponseDTO {
    private int             id;  // PK
    private String          title;
    private String          content;
    private String          writer;
    private LocalDateTime   regDate;
    private int             viewCnt;
}
