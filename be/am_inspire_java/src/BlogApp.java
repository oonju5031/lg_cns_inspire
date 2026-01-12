import lgcns.domain.blog.view.BlogView;

public class BlogApp {
    
    public static void main(String[] args) {
        
        // @Builder 방식의 참조 타입 멤버 변수 주입
        // BlogView view = BlogView.builder()
        //                     .scan(new Scanner(System.in))
        //                     .build();

        // new 객체 생성
        BlogView view = new BlogView();
        view.mainMenu();
        
    }
}
