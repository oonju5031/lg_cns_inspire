package lgcns.domain.oop.sub;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class PersonDTO {
    private String name;
    private int age;
    private String address;
    
    public String personInfo() {
        return "name: " + name + ", age: " + age + ", address: " + address;
    }

}
