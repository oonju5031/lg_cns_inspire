import lgcns.domain.oop.service.OopService;
import lgcns.domain.oop.sub.PersonDTO;
import lgcns.domain.oop.util.DivisionFlag;

public class OopFrontApp {

    public static void main(String[] args) {
        OopService service = new OopService();

        service.makePerson(DivisionFlag.STUDENT, "jylee", 20, "Seoul", "2019028");
        service.makePerson(DivisionFlag.TEACHER, "jyKim", 25, "Busan", "Java");

        // 전체 출력
        System.out.println();
        PersonDTO[] ary = service.getAry();
        for (PersonDTO p : ary) {
            if (p == null) {
                break;
            }
            System.out.println(p.personInfo());
        }

        // 이름으로 찾기
        PersonDTO findPerson = service.findPerson("jylee");
        if (findPerson != null) {
            System.out.println(findPerson.personInfo());
        } else {
            System.out.println("Not Found.");
        }
    }
}
