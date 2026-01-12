import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lgcns.domain.generic.Status;
import lgcns.domain.oop.sub.PersonDTO;
import lgcns.domain.oop.sub.StudentDTO;
import lgcns.domain.oop.sub.TeacherDTO;

public class CollectionApp {

    public static void main(String[] args) {

        // === Array ===
        System.out.println(">>> Array");
        int[] ary = new int[5];

        ary[0] = 10;
        ary[1] = 20;
        ary[2] = 30;
        ary[3] = 40;
        ary[4] = 50;

        System.out.println(Arrays.toString(ary));

        // === Collection(List<Integer>) ===
        System.out.println(">>> Collection: List of Integer");
        List<Integer> intList = new ArrayList<>();

        intList.add(10);
        intList.add(20);
        intList.add(30);
        intList.add(40);
        intList.add(50);

        System.out.println(intList.toString());

        // === Collection(List<DTO>) ===
        System.out.println(">>> Collection: List of DTO");
        List<PersonDTO> personList = new ArrayList<>();

        TeacherDTO teacher = TeacherDTO.builder()
                                    .name("jylee")
                                    .age(25)
                                    .address("Seoul")
                                    .subject("Java")
                                    .build();
        StudentDTO student = StudentDTO.builder()
                                    .name("jykim")
                                    .age(20)
                                    .address("Busan")
                                    .ssn("2019028")
                                    .build();
        
        personList.add(teacher);
        personList.add(student);

        for (PersonDTO p : personList) {
            System.out.println(p.personInfo());
        }

        // === Collection(Set) ===
        System.out.println(">>> Collection: Set");
        Set<String> set = new HashSet<>();

        set.add("jylee");
        set.add("inspire");
        set.add("test");
        set.add("jylee");
        set.add("lgcns");

        System.out.println(set);

        // Set to Array
        Object[] setArray = set.toArray();

        for (Object obj : setArray) {
            System.out.println(obj);
        }

        // === Map ===
        System.out.println(">>> Map");
        Map<String, List<? extends PersonDTO>> map = new HashMap<>();

        List<PersonDTO> studentList = new ArrayList<>();
        studentList.add(student);
        List<PersonDTO> teacherList = new ArrayList<>();
        studentList.add(teacher);

        map.put("students", studentList);
        map.put("teachers", teacherList);

        // === 사용자 정의 Generics 문법 ===
        Status<Integer> errCode = new Status<>();
        errCode.setCode(200);
        System.out.println(errCode.getCode());

        Status<String> errMsg = new Status<>();
        errMsg.setCode("Error message");
        System.out.println(errMsg.getCode());

    }
    
}
