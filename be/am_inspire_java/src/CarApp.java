import lgcns.domain.car.Car;

public class CarApp {
    public static void main(String[] args) {
        // Car myCar = new Car("testMaker", "testModel", 200000000);

        Car myCar = new Car();

        myCar.setMaker("testMaker");
        myCar.setModel("testModel");
        myCar.setPrice(200000000);

        System.out.println(myCar.toString());
        System.out.println();

        // 인스턴스 소유의 메소드 호출
        myCar.drive();
        String repair = myCar.repair();
        System.out.println(repair);
        String speed = myCar.speed(200);
        System.out.println(speed);
        myCar.performance("고급 휘발유");
    }
}
