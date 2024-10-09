// class to store user input for atoms
import java.util.Scanner;

public class UserInputAtoms {
    // function to receive user input on where to place atoms
    public static void atomInput() {

        // stores where the atoms are inputted
        int[] atoms = {0, 0, 0, 0, 0, 0};

        // prompts the user and receives input on where to place an atom
        System.out.println("Which tile would you like to place an atom on? (Enter number between 1 - 61)");

        // keep scanning for input indefinitely until a valid
        // input is given
        for (int i = 1; i <= 6; i++) {

            System.out.print(i + ". ");
            atoms[i-1] = UserInterface.scan.nextInt();

            // error handling
            if (atoms[i-1] < 1 || atoms[i-1] > 61 || HexDisplay.hexagonCentres[atoms[i-1] - 1] == 2) {
                System.out.println("Invalid number entered, please enter number between 1-61");
                i--;
            }

            // calls addAtom() to store the atom
            else {
                HexDisplay.addAtom(atoms[i-1]);
            }

        }

        System.out.println("The tiles you have chosen to place atoms on are: " + atoms[0] + ", " + atoms[1] + ", " + atoms[2] + ", " + atoms[3] + ", "  + atoms[4] + " and "  + atoms[5] + ".");
    }
}




