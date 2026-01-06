package lgcns.domain.Game;

import java.util.Scanner;

/*
1~100 사이의 난수를 발생시켜 해당 값을 맞추는 게임
- 주어진 기회는 10번(up/down)
- 반환값
    - 성공: n번째에 정답을 맞췄습니다!
    - 실패: 10번의 기회를 모두 사용하였습니다.
*/
public class GuessGame {
    
    public String gameForLoop(int answer) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {

            System.out.println((i + 1) + "번째 시도:");
            int trial = sc.nextInt();

            if (trial == answer) {
                sc.close();
                return (i + 1) + "번째에 정답을 맞췄습니다!";
            } else if (trial > answer) {
                System.out.println("down");
            } else if (trial < answer) {
                System.out.println("up");
            }

        }

        sc.close();
        return "10번의 기회를 모두 사용하였습니다. 정답은 " + answer;

    }

    public String gameWhileLoop(int answer) {

        Scanner sc = new Scanner(System.in);

        int n = 0;

        while (n < 10) {
            System.out.println((n + 1) + "번째 시도:");
            int trial = sc.nextInt();

            if (trial == answer) {
                sc.close();
                return (n + 1) + "번째에 정답을 맞췄습니다!";
            } else if (trial > answer) {
                System.out.println("down");
                n++;
            } else if (trial < answer) {
                System.out.println("up");
                n++;
            }
        }

        sc.close();
        return "10번의 기회를 모두 사용하였습니다. 정답은 " + answer;

    }

}
