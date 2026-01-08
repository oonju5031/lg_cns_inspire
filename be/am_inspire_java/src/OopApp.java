import lgcns.domain.oop.sub.PersonDTO;
import lgcns.domain.oop.sub.StudentDTO;
import lgcns.domain.oop.sub.TeacherDTO;

public class OopApp {
    
    public static void main(String[] args) {
        StudentDTO student = StudentDTO.builder()
                                    .ssn("2026")
                                    .name("jylee")  // super class의 변수에 접근(@SuperBuilder)
                                    .age(20)
                                    .address("Seoul")
                                    .build();
        
        System.out.println(student.studentInfo());
        System.out.println(student.personInfo());  // super class의 method에 접근

        TeacherDTO teacher = TeacherDTO.builder()
                                    .subject("English")
                                    .name("mjkim")
                                    .age(21)
                                    .address("Busan")
                                    .build();
        
        System.out.println(teacher.teacherInfo());
        System.out.println(teacher.personInfo());

        PersonDTO[] personAry = new PersonDTO[10];

        personAry[0] = student;
        personAry[1] = teacher;

        for (PersonDTO p : personAry) {
            if (p == null) {
                break;
            } else if (p instanceof StudentDTO) {
                System.out.println(((StudentDTO)p).studentInfo());
            } else if (p instanceof TeacherDTO) {
                System.out.println(((TeacherDTO)p).teacherInfo());
            }
        }

    }

}
