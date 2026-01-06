import lgcns.domain.Game.GuessGame;

public class GuessGameApp {
    
    public static void main(String[] args) {
        
        int answer = (int)(Math.random() * 100 + 1);

        GuessGame game = new GuessGame();

        // String result = game.gameForLoop(answer);
        // System.out.println(result);

        String result = game.gameWhileLoop(answer);
        System.out.println(result);
    }
}
