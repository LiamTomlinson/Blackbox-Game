// test cases
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Testing {

    // function to test addAtom by adding 3 atoms in different places including edge cases,
    // checking to see if their corresponding location in hexagonCentres is set to 2,
    // showing the presence of an atom in that hexagon. Also checks for error handling
    @Test
    public void testAddAtom() {
        HexDisplay.addAtom(1);
        HexDisplay.addAtom(24);
        HexDisplay.addAtom(52);
        assertEquals(2, HexDisplay.hexagonCentres[0]);
        assertEquals(2, HexDisplay.hexagonCentres[23]);
        assertEquals(2, HexDisplay.hexagonCentres[51]);

        assertThrows(IllegalArgumentException.class, () -> HexDisplay.addAtom(-1));
        assertThrows(IllegalArgumentException.class, () -> HexDisplay.addAtom(67));

        assertEquals(1, HexDisplay.hexagonCentres[1]);
        assertEquals(1, HexDisplay.hexagonCentres[5]);
        assertEquals(1, HexDisplay.hexagonCentres[6]);

        assertEquals(1, HexDisplay.hexagonCentres[15]);
        assertEquals(1, HexDisplay.hexagonCentres[16]);
        assertEquals(1, HexDisplay.hexagonCentres[22]);
        assertEquals(1, HexDisplay.hexagonCentres[24]);
        assertEquals(1, HexDisplay.hexagonCentres[31]);
        assertEquals(1, HexDisplay.hexagonCentres[32]);

        assertEquals(1, HexDisplay.hexagonCentres[44]);
        assertEquals(1, HexDisplay.hexagonCentres[45]);
        assertEquals(1, HexDisplay.hexagonCentres[50]);
        assertEquals(1, HexDisplay.hexagonCentres[52]);
        assertEquals(1, HexDisplay.hexagonCentres[56]);
        assertEquals(1, HexDisplay.hexagonCentres[57]);
    }

    // checks to see if the spheres of influence have been added
    @Test
    public void testPrintCentre() {
        String output1 = HexDisplay.printCentre(HexDisplay.hexagonCentres[0], 1);
        String output2 = HexDisplay.printCentre(HexDisplay.hexagonCentres[23], 24);
        String output3 = HexDisplay.printCentre(HexDisplay.hexagonCentres[51], 52);
        assertEquals(HexDisplay.red + "X " + HexDisplay.reset, output1);
        assertEquals(HexDisplay.red + "X " + HexDisplay.reset, output2);
        assertEquals(HexDisplay.red + "X " + HexDisplay.reset, output3);

        String output4 = HexDisplay.printCentre(HexDisplay.hexagonCentres[1], 2);
        assertEquals(HexDisplay.green + "o " + HexDisplay.reset, output4);
    }

    @Test
    public void testRays() {
        // clear board from previous tests
        for (int i = 0; i < HexDisplay.hexagonCentres.length; i++) {
            HexDisplay.hexagonCentres[i] = 0;
        }

        // shoot ray straight through
        String output1 = Ray.shootRay(10);
        assertEquals("Ray exited the board at input number 37", output1);

        HexDisplay.addAtom(30);

        // Absoprtion
        String output2 = Ray.shootRay(3);
        assertEquals("Ray was absorbed.", output2);

        // single deflection in all 6 possible directions
        String output3 = Ray.shootRay(15);
        String output4 = Ray.shootRay(24);
        String output5 = Ray.shootRay(35);
        String output6 = Ray.shootRay(12);
        String output7 = Ray.shootRay(28);
        String output8 = Ray.shootRay(19);

        assertEquals("Ray exited the board at input number 5", output3);
        assertEquals("Ray exited the board at input number 12", output4);
        assertEquals("Ray exited the board at input number 19", output5);
        assertEquals("Ray exited the board at input number 24", output6);
        assertEquals("Ray exited the board at input number 46", output7);
        assertEquals("Ray exited the board at input number 35", output8);

        // add another atom 1 hexagon away
        HexDisplay.addAtom(46);
        String output9 = Ray.shootRay(12);

        // collision between two atoms 1 hexagon apart (not directly adjacent) should send ray back out the way it came
        assertEquals("Ray exited the board at input number 12", output9);

        // add 3rd atom such that now all 3 are adjacent
        HexDisplay.addAtom(38);

        String output10 = Ray.shootRay(35);

        // collision between 3 adjacent atoms should also send ray back out the way it came
        assertEquals("Ray exited the board at input number 35", output10);

        // add atom on the edge of the board
        HexDisplay.addAtom(50);

        String output11 = Ray.shootRay(31);

        // hitting sphere of influence immediately results in "reflected."
        assertEquals("Reflected.", output11);
    }

}
