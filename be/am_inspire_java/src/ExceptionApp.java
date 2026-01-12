import lgcns.domain.exception.ExceptionDemo;

public class ExceptionApp {
    
    public static void main(String[] args) {
        ExceptionDemo demo = new ExceptionDemo();

        System.out.println("START");
        
        try {
            demo.printAry();
            System.out.println("inner try block");

            demo.readString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("예외 여부와 상관없이 실행되는 블록");
        }

        System.out.println("END");
    }

}
