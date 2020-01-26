/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.Scanner;

/**
 * A program that allows the user create fantasy towns. The name, description,
 * capacity, number of occupants, names of occupants, and coordinates of
 * FantasyTowns are set by the user. The user then can travel to other
 * FantasyTowns based on distance and occupancy.
 *
 * @author Maiziel Serrao
 */
public class Main {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int numberOfTowns = 10; //maximum number of towns that can be created
        System.out.println();
        FantasyTown[] allTowns = new FantasyTown[numberOfTowns];

       //creating an array of FantasyTown objects, each FantasyTown is a default
        for (int i = 0; i < allTowns.length; i++) {
            allTowns[i] = new FantasyTown();
        }

        int numOfTownsSet = 1; //number of towns created by the user
        int townCounter = 0; //variable created to keep loop from creating towns 
        //beyond allTowns.length
        boolean makeNewTown = false; //condition to make a new town

        //creating towns in a loop
        do {
            while (townCounter < allTowns.length) {

                FantasyTown newTown = new FantasyTown("Bikini Bottom", 10, 0,
                        0);
                Scanner keysIn = new Scanner(System.in);

                System.out.println("For Town " + (townCounter + 1));

                //setting name
                String name = new String("");
                boolean nameCondition = true;

                do {
                    try {
                        System.out.println("\nWhat is the name of your town? > "
                                + "");
                        name = keysIn.nextLine();
                        newTown.setName(name);
                        break;
                    } catch (Exception d) {
                        System.out.println("Name cannot be an empty string or "
                                + "null!");
                        nameCondition = false;
                    }
                } while (!nameCondition);

                //setting capacity 
                int capacity;
                boolean capacityCondition = true;
                do {
                    try {
                        System.out.println("\nWhat is the capacity of your town"
                                + "? > ");
                        capacity = keysIn.nextInt() + 1;
                        newTown.setCapacity(capacity);
                        break;
                    } catch (Exception e) {
                        System.out.println("\nPlease enter a valid number!");
                        keysIn.nextLine();
                        capacityCondition = false;
                    }
                } while (!capacityCondition);

                // taken x coordinates array
                int[] townCoordinatesX = new int[numberOfTowns];
                for (int i = 0; i < townCoordinatesX.length; i++) {
                    townCoordinatesX[i]
                            = (int) Math.round(allTowns[i].getCoordinates()[0]);
                }

                // taken y coordinates array
                int[] townCoordinatesY = new int[numberOfTowns];
                for (int i = 0; i < townCoordinatesY.length; i++) {
                    townCoordinatesY[i]
                            = (int) Math.round(allTowns[i].getCoordinates()[1]);
                }

                //setting coordinates 
                double xCoordinate;
                double yCoordinate;
                boolean coordinateCondition = true;
                do {
                    try {
                        System.out.println("\nWhat is the x coordinate of your "
                                + "town? > ");
                        xCoordinate = keysIn.nextDouble();
                        System.out.println("\nWhat is the y coordinate of your "
                                + "town? > ");
                        yCoordinate = keysIn.nextDouble();
                        for (int i = 0; i < numberOfTowns; i++) {
                            //check x and y coordinates are not already taken
                            if ((xCoordinate == townCoordinatesX[i])
                                    && (yCoordinate == townCoordinatesY[i])) {
                                System.out.println("There's already a town "
                                        + "there!");
                                throw new Exception();
                            }
                        }
                        newTown.setCoordinates(xCoordinate, yCoordinate);
                        break;
                    } catch (Exception f) {
                        System.out.println("\nPlease enter a valid number "
                                + "(between 0 and 100)!");
                        keysIn.nextLine();
                        coordinateCondition = false;
                    }
                } while (!coordinateCondition);

                //flush Scanner object
                keysIn.nextLine();

                //setting occupant names 
                boolean occupantCondition = true;
                int occupantCounter = 1;
                String nextOccupant = new String("");

                while (!nextOccupant.equals("1")
                        && (occupantCounter < newTown.getCapacity())) {
                    do {
                        try {
                            System.out.println("\nWho lives here? (enter 1 if "
                                    + "no more occupants)");
                            nextOccupant = keysIn.nextLine();
                            occupantCondition = true;
                            if (!nextOccupant.equals("1")
                                    && (occupantCounter < 
                                    newTown.getCapacity())) {
                                newTown.addOccupant(nextOccupant);
                                occupantCounter++;
                            }
                        } catch (Exception g) {
                            System.out.println("Occupant name cannot be null or"
                                    + " empty string!");
                            occupantCondition = false;
                        }
                    } while (!occupantCondition);
                }

                //asking for description
                boolean descriptionCondition = true;
                String description = new String();
                do {
                    System.out.println("\nDo you want to give your town a "
                            + "description? (enter yes/no) > ");
                    String descriptionCont = keysIn.nextLine().toLowerCase();
                    if (descriptionCont.equals("yes")
                            || (descriptionCont.equals("y"))) {
                        try {
                            System.out.println("\nWhat is the description of "
                                    + "your town? > ");
                            description = keysIn.nextLine();
                            newTown.setDescription(description);
                            break;
                        } catch (Exception h) {
                            System.out.println("Your description cannot be null"
                                    + " or an empty string!");
                            descriptionCondition = false;
                        }
                    }
                } while (!descriptionCondition);

                System.out.println(newTown + "\n");

                allTowns[townCounter] = newTown;
                townCounter++;

                //prompt user if they want to enter a new town
                String confirmNewTown = new String();
                System.out.println("Do you want to enter a new town's "
                        + "information? (yes/no)");
                confirmNewTown = scan.nextLine().toLowerCase();
                if (confirmNewTown.equals("yes")) {
                    makeNewTown = true;
                    numOfTownsSet++;
                } else {
                    makeNewTown = false;
                    break;
                }
            }
            break;
        } while (makeNewTown = true);

        //printing all towns
        for (int i = 0; i < numOfTownsSet; i++) {
            System.out.println("\nTown " + (i + 1) + allTowns[i].toString());
        }

        //create an index of the current town
        int currentTownIndex = numOfTownsSet - 1; //reset this value
        boolean goElsewhere = false;

        do {
            goElsewhere = false; //reset the go somewhere else condition
            FantasyTown currentTown = allTowns[currentTownIndex];

            //tell the user where they are
            System.out.printf("\n\nYou are currently in %s. Your coordinates "
                    + "are (%.2f, %.2f).",
                    currentTown.getName(),
                    currentTown.getCoordinates()[0],
                    currentTown.getCoordinates()[1]);

            //create an array of distance between towns
            double[] distanceBetween = new double[numOfTownsSet];

            //fills array of distance between towns
            for (int i = 0; i < distanceBetween.length; i++) {
                distanceBetween[i]
                  = allTowns[currentTownIndex].calculateDistanceTo(allTowns[i]);
            }

            //variable for distance willing to walk
            int distanceWilling = 1;
            boolean distancePossible = true;

            do {
                //prompt user distance willing to walk
                try {
                    System.out.println("\nHow far are you willing to walk?");
                    distanceWilling = scan.nextInt();
                    if (distanceWilling < 0) {
                        throw new Exception();
                    }
                    break;
                } catch (Exception h) {
                    System.out.println("Please enter a valid number!");
                    scan.nextLine();
                    distancePossible = false;
                }
            } while (!distancePossible);

            //flush
            scan.nextLine();

            //create an array of all walkable towns
            boolean[] walkableTowns = new boolean[numOfTownsSet];

            int unwalkableCounter = 1;

            //fills array of walkable towns based on distance
            for (int i = 0; i < walkableTowns.length; i++) {
                boolean isWalkable = true;
                if ((distanceBetween[i] > distanceWilling)) {//can't walk here
                    isWalkable = false;
                    unwalkableCounter++;
                }
                walkableTowns[i] = isWalkable;
            }

            if (unwalkableCounter == numOfTownsSet) {
                System.out.println("You are too lazy. You can't go anywhere!");
                System.exit(0);
            }

            //print all walkable towns based on distance, plus their distance
            for (int i = 0; i < walkableTowns.length; i++) {
                if (walkableTowns[i]) {
                    System.out.printf("\n%s is %.2f units away.",
                            allTowns[i].getName(), distanceBetween[i]);
                }
            }

            //makes an array of boolean checking if town is full
            boolean[] isTownFull = new boolean[numOfTownsSet];

            //fills an isTownFull array 
            for (int i = 0; i < isTownFull.length; i++) {
                boolean townIsFull = false;
                if (allTowns[i].isFull()) {
                    townIsFull = true;
                }
                isTownFull[i] = townIsFull;
            }

            String desiredTown = new String();

            //prompt user to enter where they want to go
            System.out.println("\nWhere do you want to go?");
            desiredTown = scan.nextLine();

            //make an array filled with town names
            String[] townNames = new String[numOfTownsSet];

            //fill array with town names
            for (int i = 0; i < townNames.length; i++) {
                townNames[i] = allTowns[i].getName();
            }

            //make an array seeing if town name exists
            boolean[] townNameExists = new boolean[numOfTownsSet];
            int townToGoTo = -1; //index of town to go to

            //fill array with true if towns exist 
            for (int i = 0; i < townNameExists.length; i++) {
                boolean townNameIs = false;
                if (desiredTown.equalsIgnoreCase(townNames[i])) {
                    townNameIs = true;
                    townToGoTo = i;
                }
                townNameExists[i] = townNameIs;
            }

            //check if town to go to is walkable, not full and exists
            if (townToGoTo != -1) {
                if ((walkableTowns[townToGoTo]) && (isTownFull[townToGoTo])) {
                    //can walk there, but full
                    System.out.println("You can't walk here! It's too full!");
                } else if ((walkableTowns[townToGoTo])
                        && (!isTownFull[townToGoTo])) {
                            //can walk there, and not full
                    if (townToGoTo == currentTownIndex) { 
                            //if it's the same town
                        System.out.println("Let's explore around the area "
                                + "then!");
                    }
                    System.out.println("Okay, let's go!");
                    allTowns[currentTownIndex].removeOccupant();
                    allTowns[townToGoTo].addOccupant("User");
                    currentTownIndex = townToGoTo;
                } else { 
                            //can't walk there (too far)
                    System.out.println("You can't walk here! It's too far!");
                }
            } else {
                System.out.println("That name isn't valid!");
            }

            String goAgain = new String();

            //reprompt user if they want to go somewhere else
            System.out.println("Do you want to go somewhere else? (yes/no)");
            goAgain = scan.nextLine().toLowerCase();
            if (goAgain.equals("yes")) {
                goElsewhere = true;
                //printing all towns
                for (int i = 0; i < numOfTownsSet; i++) {
                    System.out.println("\nTown " + (i + 1)
                            + allTowns[i].toString());
                }
            }
        } while (goElsewhere);
    }
}
