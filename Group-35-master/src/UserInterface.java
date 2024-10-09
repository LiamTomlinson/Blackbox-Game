import java.util.Scanner;

/**
 * The UserInterface class provides the user interface for the game.
 */
public class UserInterface {

    // default array for the board
    static int[] defaultArray = new int[61];

    // initialise scanner to take input
    static Scanner scan = new Scanner(System.in);

    /**
     * Displays the user interface and manages the game flow.
     *
     * @return the final score of the game
     */
    public static int ui() {
        if (Main.game1 != 0) {

            // stores the guesses for where atoms are
            int[] guesses = new int[6];

            int score = 0;
            int rayInputNo;

            // stores the output of shootRay
            String s;

            // set default to space
            for (int i = 0; i < 54; i++) {
                HexDisplay.rayMarkers[i] = ' ';
            }

            // input variable to take user input
            int input;

            // do while loop which prompts user with options to play game, see rules, or exit
            // if they enter 1 - the game commences, if they enter 2 - the rules are displayed
            // if they enter 3 - the game exits
            // has error handling, so loops again if inputs are incorrect
            do {
                System.out.println("Please select an option: ");
                System.out.println("1. Play Game");
                System.out.println("2. Game Rules");
                System.out.println("3. Exit Game");
                System.out.print("Select: ");

                // take in input
                input = scan.nextInt();
                scan.nextLine();

                // switch statement for user input - 1, 2, or 3
                switch (input) {

                    // case one starts game
                    case 1:
                        HexDisplay.printBoard(HexDisplay.hexagonCentres);
                        System.out.println("\n\n");

                        // input atoms onto board
                        UserInputAtoms.atomInput();

                        // prints board with atoms
                        HexDisplay.printBoard(HexDisplay.hexagonCentres);
                        scan.nextLine();

                        System.out.println("Press enter to clear the board for the experimenter.");
                        scan.nextLine();

                        System.out.println("\n\n\n\n\n\n\n\n\n");

                        HexDisplay.printBoard(defaultArray);

                        // shoot rays until user enters 0
                        do {
                            System.out.println("To shoot a ray, enter input number.\nTo finish shooting rays, enter 0.");
                            rayInputNo = scan.nextInt();

                            // if valid ray input shoot the ray
                            if (rayInputNo > 0 && rayInputNo < 55) {
                                s = Ray.shootRay(rayInputNo);

                                // place ray input marker (i for input)
                                HexDisplay.rayMarkers[rayInputNo - 1] = 'i';
                                score++;
                                HexDisplay.printBoard(defaultArray);
                                System.out.println("\n\n\n\n");
                                System.out.println(s);
                            }

                        } while (rayInputNo != 0);

                        // prompts the user and receives input on hexagon guesses
                        System.out.println("Please enter your guess for which hexagons contain atoms (Enter number between 1 - 61)");

                        // keep scanning for input indefinitely until a valid
                        // input is given
                        for (int i = 1; i <= 6; i++) {

                            System.out.print(i + ". ");
                            guesses[i - 1] = scan.nextInt();

                            // error handling
                            if (guesses[i - 1] < 1 || guesses[i - 1] > 61 || alreadyGuessed(guesses[i - 1], i - 1, guesses)) {
                                System.out.println("Invalid number entered, please enter number between 1-61");
                                i--;
                            }

                        }

                        System.out.println("The hexagons you have guessed are: " + guesses[0] + ", " + guesses[1] + ", " + guesses[2] + ", " + guesses[3] + ", " + guesses[4] + " and " + guesses[5] + ".");

                        HexDisplay.printBoard(HexDisplay.hexagonCentres);

                        score = findWinners(score, guesses);
                        System.out.println("Your score is: " + score);
                        break;

                    case 2:
                        // print rules
                        rules();
                        continue;
                    case 3:
                        // exits game
                        System.out.println("Exiting the game. Goodbye!");
                        break;
                    default:
                        // if they input incorrect number - prompts user to enter again
                        System.out.println("Please enter a correct number.");
                }

            } while (input != 1 && input != 3);

            for (int i = 0; i < HexDisplay.hexagonCentres.length; i++) {
                HexDisplay.hexagonCentres[i] = 0;
            }

            for (int i = 0; i < HexDisplay.rayMarkers.length; i++) {
                HexDisplay.rayMarkers[i] = ' ';
            }


            return score;
        }
        return 0;
    }

    /**
     * Displays the game rules to help players understand how to play.
     */
    public static void rules(){
        // printing game rules
    }

    /**
     * Checks if a hexagon has been guessed before.
     *
     * @param n the number of the hexagon
     * @param index the index of the current guess
     * @param guesses an array containing previous guesses
     * @return true if the hexagon has been guessed before, false otherwise
     */
    public static boolean alreadyGuessed(int n, int index, int[] guesses) {
        for (int i = 0; i < index; i++) {
            if (guesses[i] == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the winners based on the hexagon guesses.
     *
     * @param score the current score of the game
     * @param guesses an array containing the hexagon guesses
     * @return the updated score after evaluating the guesses
     */
    public static int findWinners(int score, int[] guesses) {
        for (int i = 0; i < guesses.length; i++) {
            score += 5;
            if (HexDisplay.hexagonCentres[guesses[i] - 1] == 2) {
                System.out.println("Hexagon " + guesses[i] + " is a hit!");
                score -= 5;
            }
        }
        return score;
    }

}
