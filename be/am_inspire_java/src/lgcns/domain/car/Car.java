package lgcns.domain.car;

public class Car {
    private String maker;
    private String model;
    private int price;

    public Car() {

    }

    public Car(String maker, String model, int price) {
        this.maker = maker;
        this.model = model;
        this.price = price;
    }


    public void drive() {
        System.out.println(">>> 매개변수 없음, 반환 타입 없음");
    }

    public String repair() {
        System.out.println(">>> 매개변수 없음, 반환 타입 String");
        return "수리 완료";
    }

    public void performance(String fuel) {
        System.out.println(">>> 매개변수 있음, 반환 타입 없음");
        System.out.println("연료: " + fuel);
    }

    public String speed(int speed) {
        System.out.println(">>> 매개변수 있음, 반환 타입 있음");
        return "과속 여부 확인 중";
    }


    public String toString() {
        return "maker: " + maker + ", model: " + model + ", price: " + price;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
