package lgcns.domain.oop.sub;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class TeacherDTO extends PersonDTO {
    private String subject;
    
    public String teacherInfo() {
        return super.personInfo() + ", subject: " + subject;
    }

    @Override
    public String personInfo() {
        return super.personInfo() + ", subject: " + subject;
    }

}
