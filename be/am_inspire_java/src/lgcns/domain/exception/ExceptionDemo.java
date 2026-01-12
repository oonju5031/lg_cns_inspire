package lgcns.domain.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.vavr.control.Try;

public class ExceptionDemo {
    
    private String[] strAry = {"text1", "text2", "text3"};

    public void printAry() throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i <= strAry.length; i++) {
            System.out.println(strAry[i]);
        }
    }

    public void readString() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("숫자를 입력하세요: ");

        int number = Integer.parseInt(br.readLine());
        System.out.println(number);
    }

    public void tryOf() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("숫자를 입력하세요: ");
        
        String input = null;
        try {
            input = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // vavr (value + variance)
        int number = Try.of(() -> Integer.parseInt("jylee"))
                                        .onFailure(e -> System.out.println("error"))
                                        .getOrElse(-1);

        System.out.println(number);
    }
}
