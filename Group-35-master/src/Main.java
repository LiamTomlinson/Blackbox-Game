/**
 * The Main class contains the main function that plays the game.
 */
public class Main {
    static int game1 = -1, game2 = -1;
    /**
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Player 1: ");
        game1 = UserInterface.ui();

        if (game1 != 0) {
            System.out.println("Player 2: ");
            game2 = UserInterface.ui();
        }

        if (game1 != 0 && game2 != 0) {

            System.out.println("Player 1 score: " + game1);
            System.out.println("Player 2 score: " + game2);

            if (game1 < game2) {
                System.out.println("Player 1 wins!");
            } else if (game2 < game1) {
                System.out.println("Player 2 wins!");
            } else {
                System.out.println("It's a tie!");
            }

        }

        UserInterface.scan.close();
    }
}


