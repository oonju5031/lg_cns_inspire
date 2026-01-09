import lgcns.domain.oop.Animal;
import lgcns.domain.oop.Bird;
import lgcns.domain.oop.Fly;

public class AbstractApp {
    
    public static void main(String[] args) {
        
        Fly fly = new Bird();

        ((Animal)fly).eating("곤충");
    }
}
