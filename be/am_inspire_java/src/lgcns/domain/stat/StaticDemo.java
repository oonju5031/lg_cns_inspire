package lgcns.domain.stat;

public class StaticDemo {

    private int x = 0;
    private static int y = 0;

    public static void commonUtils() {
        System.out.println(">>> 공통 기능 구현");

        // x = 10;  // 오류 발생
    }

    public void instanceParts() {
        System.out.println(">>> 인스턴스 기능 구현");

        y = 10;  // 접근 가능
    }
    
}
