package lgcns.domain.control;

import lgcns.domain.user.UserRequestDTO;

public class ControlDemo {
    
    public void operator() {
        System.out.println(">>> 산술 연산자 _, -, *, /, %, ...");
        System.out.println(">>> 증감 연산자 ++, --");
        System.out.println(">>> 삼항연산자 (조건식) ? true : false");
        System.out.println(">>> 논리 연산자 &, |, !, &&, ||");
        System.out.println(">>> 관계 연산자 >, >=, <, <=, ==, !=");
    }

    public boolean register(String email, String password, String name) {
        return !(email.isEmpty() || password.isEmpty() || name.isEmpty());
    }

    public boolean register(UserRequestDTO user) {
        return !(user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty());
    }

    // ====================

    public String woodMan(int number) {
        switch (number) {
            case 1:  // 금도끼
                return "거짓말";
            case 2:  // 은도끼
                return "거짓말";
            case 3:  // 쇠도끼
                return "진실";
            default:
                return "올바르지 않은 값";
        }
    }

    // 삼항 연산자를 이용한 조건문
    public String woodManTernary(int number) {
        return (number == 1) ? "거짓말"
             : (number == 2) ? "거짓말"
             : (number == 3) ? "정직하다"
             : "올바르지 않은 값";
    }

    // ====================

    // 세 과목의 점수가 각각 40점 이상, 평균이 60점 이상이면 합격, 아니면 불합격
    public String passOrFail(int kor, int eng, int math) {

        int avg = (kor + eng + math) / 3;

        if (kor >= 40 && eng >= 40 && math >= 40 && avg >= 60) {
            return "합격";
        }

        return "불합격";
    }

}
