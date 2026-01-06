import test.variable.Variable;

public class VariableApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // 변수 선언
        int year = 2026;
        String message = "문자열";
        char gender = 'm';
        boolean isArrived = true;
        double height = 25.52;

        // 변수 사용
        System.out.println(year);
        System.out.println(message);
        System.out.println(gender);
        System.out.println(isArrived);
        System.out.println(height);

        /*
        객체(instance) 생성
        - new 연산자를 이용해 생성자(constructor)를 호출
        - 생성된 객체는 변수에 담아 객체의 구성 요소에 접근 가능
        Variable 타입(참조 타입)의 변수 var에 인스턴스 Variable을 생성하여 할당
        이 때 var는 생성된 인스턴스 Variable의 주소값을 가진다
        */
        Variable var = new Variable();

        System.out.println("address: " + var);
        System.out.println("생성된 인스턴스 소유의 변수: " + var.memberVar);
        System.out.println("생성된 인스턴스 소유의 메서드: ");
        var.play();

    }
}