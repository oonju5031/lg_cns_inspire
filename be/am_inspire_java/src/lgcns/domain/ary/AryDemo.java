package lgcns.domain.ary;

import lgcns.domain.user.UserRequestDTO;

public class AryDemo {

    private UserRequestDTO[] userAry;

    public AryDemo() {
        userAry = new UserRequestDTO[0];
    }

    public void insertTable(String email, String password, String name) {
        UserRequestDTO newUser = new UserRequestDTO(email, password, name);
        UserRequestDTO[] newAry = new UserRequestDTO[userAry.length + 1];

        for (int i = 0; i < userAry.length; i++) {
            newAry[i] = userAry[i];
        }

        newAry[userAry.length] = newUser;
        userAry = newAry;
        
    }

    public UserRequestDTO[] getUsers() {
        return userAry;
    }
    
}
