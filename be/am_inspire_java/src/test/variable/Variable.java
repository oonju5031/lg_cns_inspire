package test.variable;

public class Variable {
    /*
    클래스의 구성 요소는 클래스가 아닌 인스턴스의 소유이다.
    */

    // 생성자(Constructor)
    public Variable() {

    }

    // 클래스의 구성요소 1: 변수
    public String memberVar = "클래스 Variable 전역에서 사용할 수 있는 변수";

    // 클래스의 구성요소 2: 메소드
    public void play() {
        System.out.println("나는 Variable의 메소드입니다.");
    }
    
}
