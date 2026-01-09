package lgcns.domain.oop;

public class Bird extends Animal implements Fly {
    
    @Override
    public void flying() {
        System.out.println("I'm flying!");
    }
}
