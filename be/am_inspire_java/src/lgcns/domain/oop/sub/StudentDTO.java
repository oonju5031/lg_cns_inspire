package lgcns.domain.oop.sub;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class StudentDTO extends PersonDTO {
    private String ssn;
    
    public String studentInfo() {
        return super.personInfo() + ", ssn: " + ssn;
    }

    @Override
    public String personInfo() {
        return super.personInfo() + ", ssn: " + ssn;
    }

}
