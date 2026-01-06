import lgcns.domain.loop.LoopDemo;

public class LoopApp {

    public static void main(String[] args) {
        LoopDemo demo = new LoopDemo();

        System.out.println(demo.rangeSum(1, 10));
        System.out.println(demo.randomRangeSum());


        // char 자료형의 int로의 promotion
        char l1 = 'A'; char l2 = 'B';

        System.out.println(l1 + l2);    // 131
        System.out.println(l1 + 0);     // 65
        System.out.println((char) 65);  // A

        demo.timesTable();
    }
    
}
