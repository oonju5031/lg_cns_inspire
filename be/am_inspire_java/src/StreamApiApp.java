import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import lgcns.domain.blog.domain.dto.BlogRequestDTO;
import lgcns.domain.blog.domain.dto.BlogResponseDTO;
import lgcns.domain.blog.function.InspireFunction;

public class StreamApiApp {
    
    public static void main(String[] args) {
        /*
        - Java Stream
            - Collection
            - 코드의 가독성, 병렬처리, 유지보수 향상

        - 함수형 인터페이스
            - 가질 수 있는 메소드가 단 하나인 경우
            - Supplier:  매개변수가 없고 반환값을 가지고 있는 형태
            - Function:  매개변수가 있고 반환값을 가지고 있는 형태
            - Consumer:  매개변수가 있고 반환값을 가지지 않는 형태
            - Predicate: 매개변수가 있고 반환값이 Boolean인 형태

        - Stream API
            - 원본 데이터의 소스를 변경하지 않음
            - 일회용: 한 번 사용하면 재사용하지 않음
            - 병렬 처리가 가능하여 실행 속도가 빠름
                - Java는 process가 아닌 thread 단위이므로 병렬 처리가 가능
                    - process: 메모리에 올라가 실행되는 인스턴스
                    - thread: 프로세스 내에서 병렬로 처리되는 다수의 작업
            - 작업을 내부에서 반복 처리
                - 이를 위해 람다식(lambda expression)이 필요
        
        - Lambda Expression
            - 익명의 형태를 가지는 메소드
            - (매개변수) -> {실행문} 형태
        
        - Optional
            - NullPointerException(NPE)을 회피하기 위하여 사용
                - 어떤 메소드가 null을 반환할지 확신할 수 없거나
                - null 처리를 놓쳐서 발생하는 예외를 피하고자 할 때
            - 메소드의 반환 타입으로만 사용 가능(전역변수나 매개변수로는 사용 불가능)
        */
        
        InspireFunction lambdaFunc = (x, y) -> x > y ? x : y;
        System.out.println(lambdaFunc.max(100, 200));

        InspireFunction lambdaFunc2 = (x, y) -> x + y;
        System.out.println(lambdaFunc2.max(100, 200));



        System.out.println("\n===== Lambda Expression =====");

        // Supplier: 매개변수가 없고 반환값이 있음
        System.out.println(">>> Supplier");
        Supplier<String> supplier = () -> "test";  // Interface<T> -> T는 리턴값 타입
        System.out.println(supplier.get());

        // Function: 매개변수가 있고 반환값이 있음
        System.out.println(">>> Function");
        Function<String, Integer> function = (str) -> str.length();  // Interface<T, R> -> T는 매개변수 타입, R은 리턴값 타입
        int len = function.apply("I am Iron Man.");
        System.out.println(len);

        // Consumer: 매개변수가 있고 반환값이 없음
        System.out.println(">>> Consumer");
        Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[1]);  // Interface<T> -> T는 매개변수 타입
        consumer
            .andThen(x -> System.out.println(x))
            .accept("This is a test.");

        // Predicate: 매개변수가 있고 반환값이 boolean
        System.out.println(">>> Predicate");
        Predicate<String> predicate = (str) -> str.equals("test");  // Interface<T> -> T는 매개변수 타입
        System.out.println(predicate.test("test"));
        System.out.println(predicate.test("test123"));



        System.out.println("\n===== Stream API =====");

        List<String> sites = Arrays.asList("naver", "daum", "google", "notion");
        Stream<String> stream = sites.stream();
        stream.forEach((str) -> System.out.println(str));
        /*
        (str) -> System.out.println(str);
            은 consumer이다. 다음과 같이 블록으로 풀어서 표현하면 더 직관적으로 알 수 있다.
        (str) -> {
            System.out.println(str);
        }
        */
        System.out.println("---");
        sites.stream()
            .filter(str -> str.length() > 2)
            .sorted()
            .forEach(System.out::println);  // 메소드 참조 형태: System.out::println은 str -> System.out.println(str)과 동일
        

        System.out.println("-----");
        List<BlogRequestDTO> list = Arrays.asList(
            BlogRequestDTO.builder().title("test1").writer("jylee").build(),
            BlogRequestDTO.builder().title("test2").writer("mjkim").build(),
            BlogRequestDTO.builder().title("test2").writer("twryu").build()
        );
        list.stream().forEach(System.out::println);

        // 객체의 List에서 특정 프로퍼티만을 가져와 List 만들기 (이 경우 writer을 가져와 String의 List로 만든다)
        List<String> writerList = list.stream()
                                    .map(BlogRequestDTO::getWriter)  // 메소드 참조 형태: writer -> writer.getWriter와 동일
                                    .toList();
        writerList.stream().forEach(System.out::println);

        // 일부 프로퍼티만을 가지는 객체의 List 만들기 (이 경우 title을 제거하고 writer만을 포함한 객체의 List를 만든다)
        List<BlogRequestDTO> filteredList = list.stream()
                                                .filter(obj -> obj.getWriter().length() > 5)
                                                .toList();
        filteredList.stream().forEach(System.out::println);



        System.out.println("\n===== Optional =====");

        Optional<String> optional1 = Optional.of("jylee");
        if (optional1.isPresent()) {
            System.out.println(optional1.get());
        }

        Optional<BlogResponseDTO> optional2 = Optional.empty();
        if (optional2.isPresent()) {
            System.out.println(optional2.get().getTitle());
        } else {
            System.out.println("NPE");
        }

        System.out.println("Successfully ended.");

    }
}
