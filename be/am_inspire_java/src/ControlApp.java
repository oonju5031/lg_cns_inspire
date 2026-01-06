import java.util.Scanner;

import lgcns.domain.control.ControlDemo;
import lgcns.domain.user.UserRequestDTO;

public class ControlApp {

    public static void main(String[] args) {

        ControlDemo controlDemo = new ControlDemo();
        controlDemo.operator();

        Scanner sc = new Scanner(System.in);
        
        System.out.println(">>> 이메일 입력: ");
        String email = sc.nextLine();

        System.out.println(">>> 비밀번호 입력: ");
        String password = sc.nextLine();

        System.out.println(">>> 이름 입력: ");
        String name = sc.nextLine();

        // 방법 1: 변수별로 전달
        //boolean flag = controlDemo.register(email, password, name);

        // 방법 2: DTO 사용
        UserRequestDTO user = new UserRequestDTO(email, password, name);
        boolean flag = controlDemo.register(user);

        if (flag) {
            System.out.println(">>> 가입 성공");
        } else {
            System.out.println(">>> 가입 실패");
        }

        // ====================

        int answer = sc.nextInt();

        //String woodMan = controlDemo.woodMan(answer);
        String woodMan = controlDemo.woodManTernary(answer);
        System.out.println(woodMan);

        // ====================

        System.out.println("===점수 입력===");
        System.out.println("국어: ");
        int kor = sc.nextInt();
        System.out.println("영어: ");
        int eng = sc.nextInt();
        System.out.println("수학: ");
        int math = sc.nextInt();

        System.out.println(controlDemo.passOrFail(kor, eng, math));


        sc.close();

    }

}
