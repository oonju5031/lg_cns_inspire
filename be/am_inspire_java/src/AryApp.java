import lgcns.domain.ary.AryDemo;
import lgcns.domain.user.UserRequestDTO;

public class AryApp {

    public static void main(String[] args) {

        AryDemo demo = new AryDemo();
        
        demo.insertTable("example1@gmail.com", "1234", "example1");
        demo.insertTable("example2@gmail.com", "1234", "example2");
        demo.insertTable("example3@gmail.com", "1234", "example3");

        UserRequestDTO[] resultAry = demo.getUsers();

        for (UserRequestDTO data : resultAry) {
            System.out.println(data.getEmail());
        }
    }
    
}
