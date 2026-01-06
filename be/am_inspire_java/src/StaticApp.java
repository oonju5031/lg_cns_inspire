import lgcns.domain.stat.StaticDemo;

public class StaticApp {

    public static void main(String[] args) {
        // arrayPrint();

        // static method의 경우 아무 인스턴스의 소유도 아니며 메모리에 이미 올라가 있음
        // => 객체 생성 없이 클래스명으로만 접근 가능
        StaticDemo.commonUtils();

        // non-static method의 경우 객체 생선 없이는 불가능
        // StaticDemo.instanceUtils();
    }

    public void arrayPrint() {
        System.out.println(">>> arrayPrint()");
    }
    
}
