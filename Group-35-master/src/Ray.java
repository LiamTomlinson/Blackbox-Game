public class Ray {
    // stores q, r and s co-ords
    private int currentQ;
    private int currentR;
    private int currentS;

    // stores current direction of travel
    private String travelDirection;

    // these let us know where the atoms are in relation to a given sphere of influence
    public static boolean atomNE = false, atomE = false, atomSE = false, atomSW = false, atomW = false, atomNW = false;

    // Ray constructor
    public Ray(int q, int r, int s, String direction) {
        this.currentQ = q;
        this.currentR = r;
        this.currentS = s;
        this.travelDirection = direction;
    }

    // function to shoot a ray into the board
    public static String shootRay(int input) {
        Ray currentRay;

        // gives initial direction of travel depending on input
        if (input % 2 != 0 && input > 0 && input < 10 || input == 47 || input == 49 || input == 51 || input == 53) {
            currentRay = new Ray(0, 0, 0, "SE");
        } else if (input % 2 == 0 && input <= 18 && input > 0) {
            currentRay = new Ray(0, 0, 0, "E");
        } else if (input % 2 != 0 && input >= 29 && input <= 45) {
            currentRay = new Ray(0, 0, 0, "W");
        } else if (input % 2 == 0 && input >= 38 && input <= 54) {
            currentRay = new Ray(0, 0, 0, "SW");
        } else if (input % 2 != 0 && input >= 11 && input <= 27) {
            currentRay = new Ray(0, 0, 0, "NE");
        } else {
            currentRay = new Ray(0, 0, 0, "NW");
        }

        // add co-ordinates to current ray using Hexagon Class inputCoordinates
        if (input == 54) {
            currentRay.addCoOrds(Hexagon.inputCoordinates[0][0], Hexagon.inputCoordinates[0][1], Hexagon.inputCoordinates[0][2]);
        }

        else {
            currentRay.addCoOrds(Hexagon.inputCoordinates[input - 1][0], Hexagon.inputCoordinates[input - 1][1], Hexagon.inputCoordinates[input - 1][2]);
        }

        // if an atom is hit immediately on the edge of the board
        if (HexDisplay.hexagonCentres[currentRay.findHexIndex()] == 1) {
            return "Reflected.";
        }

        // move through the board
        while (currentRay.getCurrentQ() >= -4 && currentRay.getCurrentQ() <= 4 && currentRay.getCurrentR() >= -4
                && currentRay.getCurrentR() <= 4 && currentRay.getCurrentS() >= -4 && currentRay.getCurrentS() <= 4) {

            // if ray hits a sphere of influence and deflectRay() returns 1 (meaning atom is next hexagon)
            // or if ray hits atom immediately on edge of the board
            // ray gets absorbed
            if ( (HexDisplay.hexagonCentres[currentRay.findHexIndex()] == 2) ||
                    ((HexDisplay.hexagonCentres[currentRay.findHexIndex()] == 1) && (currentRay.deflectRay() == 1)) ) {
                return "Ray was absorbed.";
            }

            // move the ray to the next hexagon depending on its direction of travel
            if (currentRay.travelDirection.equals("SE")) {
                currentRay.setCurrentR(currentRay.getCurrentR() + 1);
                currentRay.setCurrentS(currentRay.getCurrentS() - 1);
            } else if (currentRay.travelDirection.equals("E")) {
                currentRay.setCurrentQ(currentRay.getCurrentQ() + 1);
                currentRay.setCurrentS(currentRay.getCurrentS() - 1);
            } else if (currentRay.travelDirection.equals("NE")) {
                currentRay.setCurrentQ(currentRay.getCurrentQ() + 1);
                currentRay.setCurrentR(currentRay.getCurrentR() - 1);
            } else if (currentRay.travelDirection.equals("SW")) {
                currentRay.setCurrentQ(currentRay.getCurrentQ() - 1);
                currentRay.setCurrentR(currentRay.getCurrentR() + 1);
            } else if (currentRay.travelDirection.equals("W")) {
                currentRay.setCurrentQ(currentRay.getCurrentQ() - 1);
                currentRay.setCurrentS(currentRay.getCurrentS() + 1);
            } else if (currentRay.travelDirection.equals("NW")) {
                currentRay.setCurrentR(currentRay.getCurrentR() - 1);
                currentRay.setCurrentS(currentRay.getCurrentS() + 1);
            }

        }

        // after the while loop, the co-ordinates are now off the board so we
        // reverse the ray back a step to the last hexagon before it
        // left the board
        if (currentRay.travelDirection.equals("SE")) {
            currentRay.setCurrentR(currentRay.getCurrentR() - 1);
            currentRay.setCurrentS(currentRay.getCurrentS() + 1);
        } else if (currentRay.travelDirection.equals("E")) {
            currentRay.setCurrentQ(currentRay.getCurrentQ() - 1);
            currentRay.setCurrentS(currentRay.getCurrentS() + 1);
        } else if (currentRay.travelDirection.equals("NE")) {
            currentRay.setCurrentQ(currentRay.getCurrentQ() - 1);
            currentRay.setCurrentR(currentRay.getCurrentR() + 1);
        } else if (currentRay.travelDirection.equals("SW")) {
            currentRay.setCurrentQ(currentRay.getCurrentQ() + 1);
            currentRay.setCurrentR(currentRay.getCurrentR() - 1);
        } else if (currentRay.travelDirection.equals("W")) {
            currentRay.setCurrentQ(currentRay.getCurrentQ() + 1);
            currentRay.setCurrentS(currentRay.getCurrentS() - 1);
        } else if (currentRay.travelDirection.equals("NW")) {
            currentRay.setCurrentR(currentRay.getCurrentR() + 1);
            currentRay.setCurrentS(currentRay.getCurrentS() - 1);
        }

        // print the exit number of the ray
        return currentRay.printExitNo();
    }

    // function to deflect a ray off a sphere of influence
    public int deflectRay() {

        // hit 1 atom only
        if (countAtoms(this.getCurrentQ(), this.getCurrentR(), this.getCurrentS()) == 1) {

            // absorbed
            if (  (this.getTravelDirection().equals("W") && atomW) || (this.getTravelDirection().equals("NW") && atomNW)
                   || (this.getTravelDirection().equals("NE") && atomNE) || (this.getTravelDirection().equals("E") && atomE)
                   || (this.getTravelDirection().equals("SE") && atomSE) || (this.getTravelDirection().equals("SW") && atomSW)   ) {

                return 1;

            }

            // deflect
            else if (this.getTravelDirection().equals("W")) {
                if (atomNW) {
                    this.setTravelDirection("SW");
                } else if (atomSW) {
                    this.setTravelDirection("NW");
                }
            }

            else if (this.getTravelDirection().equals("NW")) {
                if (atomNE) {
                    this.setTravelDirection("W");
                } else if (atomW) {
                    this.setTravelDirection("NE");
                }
            }

            else if (this.getTravelDirection().equals("NE")) {
                if (atomE) {
                    this.setTravelDirection("NW");
                } else if (atomNW) {
                    this.setTravelDirection("E");
                }
            }

            else if (this.getTravelDirection().equals("E")) {
                if (atomNE) {
                    this.setTravelDirection("SE");
                } else if (atomSE) {
                    this.setTravelDirection("NE");
                }
            }

            else if (this.getTravelDirection().equals("SE")) {
                if (atomE) {
                    this.setTravelDirection("SW");
                } else if (atomSW) {
                    this.setTravelDirection("E");
                }
            }

            else if (this.getTravelDirection().equals("SW")) {
                if (atomSE) {
                    this.setTravelDirection("W");
                } else if (atomW) {
                    this.setTravelDirection("SE");
                }
            }

        }

        // hit 2 atoms
        else if (countAtoms(this.getCurrentQ(), this.getCurrentR(), this.getCurrentS()) == 2) {

            // gap between 2 atoms sends ray back the way it came
            if (this.getTravelDirection().equals("W") && atomNW && atomSW) {
                this.setTravelDirection("E");
            }
            else if (this.getTravelDirection().equals("NW") && atomNE && atomSW) {
                this.setTravelDirection("SE");
            }
            else if (this.getTravelDirection().equals("NE") && atomNW && atomSE) {
                this.setTravelDirection("SW");
            }
            else if (this.getTravelDirection().equals("E") && atomNE && atomSE) {
                this.setTravelDirection("W");
            }
            else if (this.getTravelDirection().equals("SE") && atomE && atomSW) {
                this.setTravelDirection("NW");
            }
            else if (this.getTravelDirection().equals("SW") && atomNW && atomE) {
                this.setTravelDirection("SW");
            }

            // two atoms directly beside each other
            else if (this.getTravelDirection().equals("W")) {
                if (atomNW) {
                    this.setTravelDirection("SE");
                } else if (atomSW) {
                    this.setTravelDirection("NE");
                }
            }

            else if (this.getTravelDirection().equals("NW")) {
                if (atomNE) {
                    this.setTravelDirection("SW");
                } else if (atomW) {
                    this.setTravelDirection("E");
                }
            }

            else if (this.getTravelDirection().equals("NE")) {
                if (atomE) {
                    this.setTravelDirection("W");
                } else if (atomNW) {
                    this.setTravelDirection("SE");
                }
            }

            else if (this.getTravelDirection().equals("E")) {
                if (atomNE) {
                    this.setTravelDirection("SW");
                } else if (atomSE) {
                    this.setTravelDirection("NW");
                }
            }

            else if (this.getTravelDirection().equals("SE")) {
                if (atomE) {
                    this.setTravelDirection("W");
                } else if (atomSW) {
                    this.setTravelDirection("NE");
                }
            }

            else if (this.getTravelDirection().equals("SW")) {
                if (atomW) {
                    this.setTravelDirection("E");
                } else if (atomSE) {
                    this.setTravelDirection("NW");
                }
            }

        }

        // 3 atoms sends the ray back the way it came
        else if (countAtoms(this.currentQ, this.currentR, this.currentS) == 3) {
            if (this.getTravelDirection().equals("W")) {
                this.setTravelDirection("E");
            }
            else if (this.getTravelDirection().equals("NW")) {
                this.setTravelDirection("SE");
            }
            else if (this.getTravelDirection().equals("NE")) {
                this.setTravelDirection("SW");
            }
            else if (this.getTravelDirection().equals("E")) {
                this.setTravelDirection("W");
            }
            else if (this.getTravelDirection().equals("SE")) {
                this.setTravelDirection("NW");
            }
            else if (this.getTravelDirection().equals("SW")) {
                this.setTravelDirection("NE");
            }

        }

        return 0;
    }

    // function to print where the ray exits
    public String printExitNo() {
        int i;

        // find the hexagon in inputCoordinates[] and store the index
        for (i = 0; i < Hexagon.inputCoordinates.length; i++) {
            if (Hexagon.inputCoordinates[i][0] == this.currentQ && Hexagon.inputCoordinates[i][1] == this.currentR && Hexagon.inputCoordinates[i][2] == this.currentS) {
                break;
            }
        }

        // different cases of which number to output,
        // depending on the hex number and direction of travel
        if (i < 9) {
            if (this.getTravelDirection().equals("NW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("W")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
            else if (this.getTravelDirection().equals("NE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[53] = 'o';
                return "Ray exited the board at input number 54";
            }
            else if (this.getTravelDirection().equals("SW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[10] = 'o';
                return "Ray exited the board at input number 11";
            }
        }

        else if (i == 9) {
            if (this.getTravelDirection().equals("NW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i-1] = 'o';
                return "Ray exited the board at input number " + i;
            }
            else if (this.getTravelDirection().equals("W")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("SW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }

        }

        else if (i < 17) {
            if (this.getTravelDirection().equals("W")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("SW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
        }

        else if (i == 17) {
            if (this.getTravelDirection().equals("W")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("SW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
            else if (this.getTravelDirection().equals("SE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+2] = 'o';
                return "Ray exited the board at input number " + (i + 3);
            }
        }

        else if (i < 26) {
            if (this.getTravelDirection().equals("SW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("SE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
        }

        else if (i == 26) {
            if (this.getTravelDirection().equals("SW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("SE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
            else if (this.getTravelDirection().equals("E")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+2] = 'o';
                return "Ray exited the board at input number " + (i + 3);
            }
        }

        else if (i < 35) {
            if (this.getTravelDirection().equals("SE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("E")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
        }

        else if (i == 35) {
            if (this.getTravelDirection().equals("SE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("E")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
            else if (this.getTravelDirection().equals("NE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+2] = 'o';
                return "Ray exited the board at input number " + (i + 3);
            }
        }

        else if (i < 44) {
            if (this.getTravelDirection().equals("E")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " +(i + 1);
            }
            else if (this.getTravelDirection().equals("NE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
        }

        else if (i == 44) {
            if (this.getTravelDirection().equals("E")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("NE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
            else if (this.getTravelDirection().equals("NW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+2] = 'o';
                return "Ray exited the board at input number " + (i + 3);
            }
        }

        else if (i < 53) {
            if (this.getTravelDirection().equals("NE")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i] = 'o';
                return "Ray exited the board at input number " + (i + 1);
            }
            else if (this.getTravelDirection().equals("NW")) {
                //inputs an o to ray output position
                HexDisplay.rayMarkers[i+1] = 'o';
                return "Ray exited the board at input number " + (i + 2);
            }
        }

        return "";

    }

    // function to count the number of atoms corresponding to a sphere of influence
    // and to show where atoms are in relation to the sphere of influence
    public static int countAtoms(int q, int r, int s) {
        int a[] = {q, r, s};
        int count = 0;
        atomNE = false;
        atomE = false;
        atomSE = false;
        atomSW = false;
        atomW = false;
        atomNW = false;

        // change the co-ords passed to the for loop below to check
        // the W, NW, NE, E, SE and SW hexagons in that order using the
        // Hexagon class coordinates[] array
        for (int n = 0; n < 6; n++) {
            switch (n) {
                case 0:
                    a[0] = q - 1;
                    a[1] = r;
                    a[2] = s + 1;
                    break;
                case 1:
                    a[0] = q;
                    a[1] = r - 1;
                    a[2] = s + 1;
                    break;
                case 2:
                    a[0] = q + 1;
                    a[1] = r - 1;
                    a[2] = s;
                    break;
                case 3:
                    a[0] = q + 1;
                    a[1] = r;
                    a[2] = s - 1;
                    break;
                case 4:
                    a[0] = q;
                    a[1] = r + 1;
                    a[2] = s - 1;
                    break;
                case 5:
                    a[0] = q - 1;
                    a[1] = r + 1;
                    a[2] = s;
                    break;
            }

            // search through array, if a matching hexagon is found, check to see
            // if its hexagonCentre[] = 2 meaning it has an atom
            // if it does, set the corresponding boolean to true and increment count
            for (int i = 0; i < Hexagon.coordinates.length; i++) {
                if (Hexagon.coordinates[i][0] == a[0] && Hexagon.coordinates[i][1] == a[1] && Hexagon.coordinates[i][2] == a[2]) {
                    if (HexDisplay.hexagonCentres[i] == 2) {
                        count++;

                        switch (n) {
                            case 0:
                                atomW = true;
                                break;
                            case 1:
                                atomNW = true;
                                break;
                            case 2:
                                atomNE = true;
                                break;
                            case 3:
                                atomE = true;
                                break;
                            case 4:
                                atomSE = true;
                                break;
                            case 5:
                                atomSW = true;
                                break;
                        }

                        break;
                    }
                }
            }

        }

        // return the number of atoms corresponding to this sphere of influence
        return count;
    }

    public int findHexIndex() {
        int a[] = {this.currentQ, this.currentR, this.currentS};

        for (int i = 0; i < Hexagon.coordinates.length; i++) {
            if (Hexagon.coordinates[i][0] == a[0] && Hexagon.coordinates[i][1] == a[1] && Hexagon.coordinates[i][2] == a[2]) {
                return i;
            }
        }

        return 0;
    }

    // function to add co-ordinates to a ray
    public void addCoOrds(int q, int r, int s) {
        this.setCurrentQ(q);
        this.setCurrentR(r);
        this.setCurrentS(s);
    }

    // getters and setters
    public int getCurrentQ() {
        return this.currentQ;
    }

    public int getCurrentR() {
        return this.currentR;
    }

    public int getCurrentS() {
        return this.currentS;
    }

    public String getTravelDirection() {
        return this.travelDirection;
    }

    public void setCurrentQ(int q) {
        this.currentQ = q;
    }

    public void setCurrentR(int r) {
        this.currentR = r;
    }

    public void setCurrentS(int s) {
        this.currentS = s;
    }

    public void setTravelDirection(String direction) {
        this.travelDirection = direction;
    }

}

