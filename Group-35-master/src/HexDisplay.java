// class that controls printing the board
import java.util.ArrayList;
public class HexDisplay {

    // integer array that stores the status of every hexagon -
    // 0 means empty, 1 means there is a circle from an atom's
    // sphere of influence and 2 means it contains an atom
    static int[] hexagonCentres = new int[61];
    static char[] rayMarkers = new char[54];

    // text colour escape codes
    static String red = "\u001B[31m", green = "\u001B[32m", blue = "\u001B[34m", yellow = "\u001B[33m", pink = "\u001B[38;5;207m", reset = "\u001B[0m";

    // function to print the board with printCentre() being called
    // at each hexagon's centre. This allows the board to remain constant
    // whilst the hexagon's centres can be changed easily
    public static void printBoard(int[] a) {

        System.out.println(blue + "                                       " + yellow + " 1-" + pink + rayMarkers[0] + blue + " * " + yellow + "54-" + pink + rayMarkers[53] + yellow +  " 53-" + pink + rayMarkers[52] + blue + " * " + yellow + "52-" + pink + rayMarkers[51] + yellow +  " 51-" + pink + rayMarkers[50] + blue + " *" + yellow + " 50-" + pink + rayMarkers[49] + yellow +  " 49-" + pink + rayMarkers[48] + blue + " *" + yellow + " 48-" + pink + rayMarkers[47] + yellow + " 47-" + pink + rayMarkers[46] + blue + " *" + yellow + " 46-" + pink + rayMarkers[45] + blue                                                                                                            );
        System.out.println("                                         *     *     *     *     *     *     *     *     *     *                                                                                                                    ");
        System.out.println("                                      *           *           *           *           *           *                                                                                                                 ");
        System.out.println("                                  " + yellow + "2-" + pink + rayMarkers[1] + blue + " *     " + reset + printCentre(a[0], 1) + blue +  "    *     " + reset + printCentre(a[1], 2) + blue +  "    *     " + reset + printCentre(a[2], 3) + blue + "    *     " + reset + printCentre(a[3], 4) + blue +  "    *     " + reset + printCentre(a[4], 5) + blue +  "    *" + yellow + " 45-"  + pink + rayMarkers[44] + blue);
        System.out.println("                              " + yellow + "3-" + pink + rayMarkers[2] + blue +  "     *           *           *           *           *           *     " + yellow + " 44-" + pink + rayMarkers[43] + blue +  "                                                                                                               ");
        System.out.println("                                   *     *     *     *     *     *     *     *     *     *     *     *                                                                                                              ");
        System.out.println("                                *           *           *           *           *           *           *                                                                                                           ");
        System.out.println("                            " + yellow + "4-"  + pink + rayMarkers[3] + blue +  " *     " + reset + printCentre(a[5], 6) + blue + "    *     " + reset + printCentre(a[6], 7) + blue + "    *     "  + reset + printCentre(a[7], 8) + blue +  "    *     "  + reset + printCentre(a[8], 9) + blue +  "    *     "  + reset + printCentre(a[9], 10) + blue +  "    *     "  + reset + printCentre(a[10], 11) + blue +  "    *" + yellow + " 43-"  + pink + rayMarkers[42] + blue);
        System.out.println("                        " + yellow + "5-" + pink + rayMarkers[4] + blue + "     *           *           *           *           *           *           *     " + yellow + " 42-"+ pink + rayMarkers[41] + blue + "                                                                                                           ");
        System.out.println("                             *     *     *     *     *     *     *     *     *     *     *     *     *     *                                                                                                             ");
        System.out.println("                          *           *           *           *           *           *           *           *                                                                                                     ");
        System.out.println("                      " + yellow + "6-" + pink + rayMarkers[5] + blue + " *     " + reset + printCentre(a[11], 12) + blue + "    *     "  + reset + printCentre(a[12], 13) + blue + "    *     " + reset + printCentre(a[13], 14) + blue + "    *     " + reset + printCentre(a[14], 15) + blue + "    *     " + reset + printCentre(a[15], 16) + blue + "    *     " + reset + printCentre(a[16], 17) + blue + "    *     " + reset + printCentre(a[17], 18) + blue + "    *" + yellow + " 41-"  + pink + rayMarkers[40] + blue);
        System.out.println("                  " + yellow + "7-" + pink + rayMarkers[6] + blue + "     *           *           *           *           *           *           *           *     " + yellow + " 40-" + pink + rayMarkers[39] + blue +"                                                                                                    ");
        System.out.println("                       *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *                                                                                                  ");
        System.out.println("                    *           *           *           *           *           *           *           *           *                                                                                               ");
        System.out.println("                " + yellow + "8-" + pink + rayMarkers[7] + blue + " *     " + reset + printCentre(a[18], 19) + blue + "    *     " + reset + printCentre(a[19], 20) + blue + "    *     " + reset + printCentre(a[20], 21) + blue + "    *     " + reset + printCentre(a[21], 22) + blue + "    *     " + reset + printCentre(a[22], 23) + blue + "    *     " + reset + printCentre(a[23], 24) + blue + "    *     " + reset + printCentre(a[24], 25) + blue + "    *     " + reset + printCentre(a[25], 26) + blue + "    *" + yellow + " 39-"  + pink + rayMarkers[38] + blue);
        System.out.println("             " + yellow + "9-" + pink + rayMarkers[8] + blue + "    *           *           *           *           *           *           *           *           *     " + yellow + " 38-" + pink + rayMarkers[37] + blue +"                                                                                               ");
        System.out.println("                 *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *                                                                                            ");
        System.out.println("              *           *           *           *           *           *           *           *           *           *                                                                                         ");
        System.out.println("         " + yellow + "10-" + pink + rayMarkers[9] +  blue + " *     " + reset + printCentre(a[26], 27) + blue + "    *     " + reset + printCentre(a[27], 28) + blue + "    *     " + reset + printCentre(a[28], 29) + blue + "    *     " + reset + printCentre(a[29], 30) + blue + "    *     " + reset + printCentre(a[30], 31) + blue + "    *     " + reset + printCentre(a[31], 32) + blue + "    *     " + reset + printCentre(a[32], 33) + blue + "    *     " + reset + printCentre(a[33], 34) + blue + "    *     " + reset + printCentre(a[34], 35) + blue + "    *  " + yellow + " 37-" + pink + rayMarkers[36] + blue);
        System.out.println("              *           *           *           *           *           *           *           *           *           *                                                                                         ");
        System.out.println("                 *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *                                                                                            ");
        System.out.println("            " + yellow + "11-" + pink + rayMarkers[10] + blue + "    *           *           *           *           *           *           *           *           *     " + yellow + " 36-" + pink + rayMarkers[35] + blue + "                                                                                              ");
        System.out.println("               " + yellow + "12-" + pink + rayMarkers[11] + blue + " *     " + reset + printCentre(a[35], 36) + blue + "    *     " + reset + printCentre(a[36], 37) + blue + "    *     " + reset + printCentre(a[37], 38) + blue + "    *     " + reset + printCentre(a[38], 39) + blue + "    *     " + reset + printCentre(a[39], 40) + blue + "    *     " + reset + printCentre(a[40], 41) + blue + "    *     " + reset + printCentre(a[41], 42) + blue + "    *     " + reset + printCentre(a[42], 43) + blue + "    *" + yellow + " 35-"  + pink + rayMarkers[34] + blue);
        System.out.println("                    *           *           *           *           *           *           *           *           *                                                                                               ");
        System.out.println("                       *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *                                                                                                  ");
        System.out.println("                " + yellow + "13-"  + pink + rayMarkers[12] + blue + "      *           *           *           *           *           *           *           *     " + yellow + " 34-" + pink + rayMarkers[33] + blue + "                                                                                                    ");
        System.out.println("                     " + yellow + "14-" + pink + rayMarkers[13] +  blue + " *     " + reset + printCentre(a[43], 44) + blue + "    *     "  + reset + printCentre(a[44], 45) + blue + "    *     " + reset + printCentre(a[45], 46) + blue + "    *     " + reset + printCentre(a[46], 47) + blue + "    *     " + reset + printCentre(a[47], 48) + blue + "    *     " + reset + printCentre(a[48], 49) + blue + "    *     " + reset + printCentre(a[49], 50) + blue + "    *" + yellow + " 33-"  + pink + rayMarkers[32] + blue);
        System.out.println("                          *           *           *           *           *           *           *           *                                                                                                     ");
        System.out.println("                             *     *     *     *     *     *     *     *     *     *     *     *     *     *                                                                                                        ");
        System.out.println("                       " + yellow + "15-" + pink + rayMarkers[14] +  blue + "     *           *           *           *           *           *           *     " + yellow + " 32-" + pink + rayMarkers[31] + blue + "                                                                                                          ");
        System.out.println("                          " + yellow + "16-" + pink + rayMarkers[15] +  blue +"  *     " + reset + printCentre(a[50], 51) + blue + "    *     " + reset + printCentre(a[51], 52) + blue + "    *     "  + reset + printCentre(a[52], 53) + blue +  "    *     "  + reset + printCentre(a[53], 54) + blue +  "    *     "  + reset + printCentre(a[54], 55) + blue +  "    *     "  + reset + printCentre(a[55], 56) + blue +  "    *" + yellow + " 31-"  + pink + rayMarkers[30] + blue);
        System.out.println("                                *           *           *           *           *           *           *                                                                                                           ");
        System.out.println("                                   *     *     *     *     *     *     *     *     *     *     *     *                                                                                                              ");
        System.out.println("                             " + yellow + "17-" + pink + rayMarkers[16] +  blue +"     *           *           *           *           *           *     " + yellow + " 30-" + pink + rayMarkers[29] + blue + "                                                                                                                ");
        System.out.println("                                " + yellow + "18-" + pink + rayMarkers[17] +  blue + "  *     " + reset + printCentre(a[56], 57) + blue +  "    *     " + reset + printCentre(a[57], 58) + blue +  "    *     " + reset + printCentre(a[58], 59) + blue + "    *     " + reset + printCentre(a[59], 60) + blue +  "    *     " + reset + printCentre(a[60], 61) + blue +  "    *" + yellow + " 29-"  + pink + rayMarkers[28] + blue);
        System.out.println("                                      *           *           *           *           *           *                                                                                                                 ");
        System.out.println("                                         *     *     *     *     *     *     *     *     *     *                                                                                                                    ");
        System.out.println("                                     " + yellow + " 19-" + pink + rayMarkers[18] + blue + "  * " + yellow + "20-" + pink + rayMarkers[19] + yellow +  " 21-" + pink + rayMarkers[20] + blue + " * " + yellow + "22-" + pink + rayMarkers[21] + yellow +  " 23-" + pink + rayMarkers[22] + blue + " *" + yellow + " 24-" + pink + rayMarkers[23] + yellow +  " 25-" + pink + rayMarkers[24] + blue + " *" + yellow + " 26-" + pink + rayMarkers[25] + yellow + " 27-" + pink + rayMarkers[26] + blue + " *" + yellow + " 28-"  + pink + rayMarkers[27] + blue +  reset);

    }

    // function to add an atom to a given hexagon
    public static void addAtom(int hex) {

        // error handling
        if (hex < 1 || hex > 61) {
            throw new IllegalArgumentException("Invalid input.");
        }

        // set this hexagon to 2 in hexagonCentres[],
        // showing it now contains an atom
        hexagonCentres[hex - 1] = 2;

        // variables to hold co-ordinates stored in an array
        int q = Hexagon.coordinates[hex - 1][0];
        int r = Hexagon.coordinates[hex - 1][1];
        int s = Hexagon.coordinates[hex - 1][2];
        int[] a = {q, r, s};

        // this loop searches all the adjacent hexagons
        // and checks to see if they contain an atom, if they don't,
        // a circle of the sphere of influence is added
        for (int n = 0; n < 6; n++) {
            switch (n) {

                // check the West hexagon
                case 0:
                    a[0] = q - 1;
                    a[1] = r;
                    a[2] = s + 1;
                    break;

                // check the NorthWest hexagon
                case 1:
                    a[0] = q;
                    a[1] = r - 1;
                    a[2] = s + 1;
                    break;

                // check the NorthEast hexagon
                case 2:
                    a[0] = q + 1;
                    a[1] = r - 1;
                    a[2] = s;
                    break;

                // check the East hexagon
                case 3:
                    a[0] = q + 1;
                    a[1] = r;
                    a[2] = s - 1;
                    break;

                // check the SouthEast hexagon
                case 4:
                    a[0] = q;
                    a[1] = r + 1;
                    a[2] = s - 1;
                    break;

                // check the SouthWest hexagon
                case 5:
                    a[0] = q - 1;
                    a[1] = r + 1;
                    a[2] = s;
                    break;
            }

            // look in coordinates[] in the Hexagon class, if the co-ordinates match
            // call addCircle() to add a circle of sphere of influence
            // this is repeated for each adjacent atom
            // if an atom is non-existent (e.g the hexagon to the West of
            // hex 1), the loop simply continues
            for (int i = 0; i < Hexagon.coordinates.length; i++) {
                if (Hexagon.coordinates[i][0] == a[0] && Hexagon.coordinates[i][1] == a[1] && Hexagon.coordinates[i][2] == a[2]) {
                    addCircle(i);
                    break;
                }
            }

        }

    }

    // function to print a String (either "X", "o", or hexNumber)
    // representing an atom, circle from sphere of influence or the
    // number of an empty hexagon respectively
    public static String printCentre(int n, int hexNo) {
        if (n == 2) {
            return red + "X " + reset;
        } else if (n == 1) {
            return green + "o " + reset;
        } else {
            if (hexNo < 10) {
                return "0" + hexNo;
            } else {
                return "" + hexNo;
            }
        }
    }

    // function to add a circle of a sphere of influence to a hexagon
    // by first checking to see if the hexagon contains an atom, if not,
    // the hexagon is set to 1 in hexagonCentres, showing the presence of
    // a circle
    public static void addCircle(int n) {
            if (hexagonCentres[n] != 2) {
                hexagonCentres[n] = 1;
            }
    }

}
