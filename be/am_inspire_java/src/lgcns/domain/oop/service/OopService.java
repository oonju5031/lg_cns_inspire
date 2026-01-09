package lgcns.domain.oop.service;

import lgcns.domain.oop.sub.PersonDTO;
import lgcns.domain.oop.sub.StudentDTO;
import lgcns.domain.oop.sub.TeacherDTO;
import lgcns.domain.oop.util.DivisionFlag;

/*
배열: PersonDTO[]
- 해당 배열에 사용자가 요구하는 StudentDTO, TeacherDTO 객체를 담고자 한다.
*/
public class OopService {
    
    private PersonDTO[] personAry;
    private int idx;

    public OopService() {
        personAry = new PersonDTO[10];
    }
    
    // flag가 1인 경우 student, 2인 경우 teacher
    public void makePerson(DivisionFlag flag, String name, int age, String address, String comm) {
        PersonDTO person = null;
        person = (flag.getDivision().equals("학생"))
                        ? StudentDTO.builder()
                                .name(name).age(age).address(address).ssn(comm)
                                .build()
                        : TeacherDTO.builder()
                                .name(name).age(age).address(address).subject(comm)
                                .build();
        setAry(person);
    }

    /**
     * 이름 기반으로 Person을 찾는다.
     * @param name 찾을 이름
     * @return 해당 이름의 PersonDTO
     */
    public PersonDTO findPerson(String name) {
        PersonDTO person = null;

        for (PersonDTO p : personAry) {
            if (p == null) {
                break;
            }
            if (p.getName().equals(name)) {
                person = p;
                break;
            }
        }

        return person;
    }

    public void setAry(PersonDTO person) {
        personAry[idx++] = person;
    }
    public PersonDTO[] getAry() {
        return personAry;
    }
}
