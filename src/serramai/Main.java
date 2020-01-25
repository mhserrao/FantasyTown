/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.Scanner;

/**
 *
 * @author maizi
 */
public class Main {
    //second

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int numberOfTowns = 10;
        System.out.println();
        FantasyTown[] allTowns = new FantasyTown[numberOfTowns];

        //creating an array of FantasyTown objects, each FantasyTown is a default
        for (int i = 0; i < allTowns.length; i++) {
            allTowns[i] = new FantasyTown();
        }

        int numOfTownsSet = 1; //number of towns created by the user
        int townCounter = 0; //variable created to keep loop from creating towns over allTowns.length
        boolean makeNewTown = false; //condition to make a new town - must be true

        //creating towns in a loop
        do {
            while (townCounter < allTowns.length) {

                FantasyTown newTown = new FantasyTown("Bikini Bottom", 10, 0, 0);
                Scanner keysIn = new Scanner(System.in);

                System.out.println("For Town " + (townCounter + 1));

                //setting name
                String name = new String("");
                boolean nameCondition = true;

                do {
                    try {
                        System.out.println("\nWhat is the name of your town? > ");
                        name = keysIn.nextLine();
                        newTown.setName(name);
                        break;
                    } catch (Exception d) {
                        System.out.println("Name cannot be an empty string or null!");
                        nameCondition = false;
                    }
                } while (!nameCondition);

                //setting capacity 
                int capacity;
                boolean capacityCondition = true;
                do {
                    try {
                        System.out.println("\nWhat is the capacity of your town? > ");
                        capacity = keysIn.nextInt() + 1;
//                        if(capacity<0){
//                            throw new Exception();
//                        }
                        newTown.setCapacity(capacity);
                        break;
                    } catch (Exception e) {
                        System.out.println("\nPlease enter a valid number!");
                        keysIn.nextLine();
                        capacityCondition = false;
                    }
                } while (!capacityCondition);

                //setting coordinates - find a way to check previous coordinates
                double xCoordinate;
                double yCoordinate;
                boolean coordinateCondition = true;
                do {
                    try {
                        System.out.println("\nWhat is the x coordinate of your town? > ");
                        xCoordinate = keysIn.nextDouble();
                        System.out.println("\nWhat is the y coordinate of your town? > ");
                        yCoordinate = keysIn.nextDouble();
                        newTown.setCoordinates(xCoordinate, yCoordinate);
                        break;
                    } catch (Exception f) {
                        System.out.println("\nPlease enter a valid number (between 0 and 100)!");
                        keysIn.nextLine();
                        coordinateCondition = false;
                    }
                } while (!coordinateCondition);

                //flush
                keysIn.nextLine();

                //setting occupant names 
                boolean occupantCondition = true;
                int occupantCounter = 1;
                String nextOccupant = new String("");

                while (!nextOccupant.equals("1") && (occupantCounter < newTown.getCapacity())) {
                    do {
                        try {
                            System.out.println("\nWho lives here? (enter 1 if no more occupants)");
                            nextOccupant = keysIn.nextLine();
                            occupantCondition = true;
                            if (!nextOccupant.equals("1") && (occupantCounter < newTown.getCapacity())) {
                                newTown.addOccupant(nextOccupant);
                                occupantCounter++;
                            }
                        } catch (Exception g) {
                            System.out.println("Occupant name cannot be null or empty string!");
                            occupantCondition = false;
                        }
                    } while (!occupantCondition);
                }

                //asking for description
                boolean descriptionCondition = true;
                String description = new String();
                do {
                    System.out.println("\nDo you want to give your town a description? (enter yes/no) > ");
                    String descriptionCont = keysIn.nextLine().toLowerCase();
                    if (descriptionCont.equals("yes") || (descriptionCont.equals("y"))) {
                        try {
                            System.out.println("\nWhat is the description of your town? > ");
                            description = keysIn.nextLine();
                            newTown.setDescription(description);
                            break;
                        } catch (Exception h) {
                            System.out.println("Your description cannot be null or an empty string!");
                            descriptionCondition = false;
                        }
                    }
                } while (!descriptionCondition);

                System.out.println(newTown + "\n");

                allTowns[townCounter] = newTown;
                townCounter++;

                //prompt user if they want to enter a new town
                String confirmNewTown = new String();
                System.out.println("Do you want to enter a new town's information? (yes/no)");
                confirmNewTown = scan.nextLine().toLowerCase();
                if (confirmNewTown.equals("yes")) {
                    makeNewTown = true;
                    numOfTownsSet++;
                } else {
                    break;
                }
            }
            break;
        } while (makeNewTown = true);

        //printing all towns
        for (int i = 0; i < numOfTownsSet; i++) {
            System.out.println("\nTown " + (i + 1) + allTowns[i].toString());
        }

        int currentTownIndex = numOfTownsSet - 1; //index of the last added element in allTowns array
        int distanceWilling = 0; //distance user is willing to walk
        FantasyTown currentTown = allTowns[currentTownIndex]; //town user is currently in
        boolean walkSomewhereElse = false; //loop condition for user to go elsewhere - must be true for loop to continue

        do {
            //informing user of their location
            System.out.println("\nYou are currently in " + allTowns[currentTownIndex].getName()
                    + ". Your coordinates are (" + allTowns[currentTownIndex].getCoordinates()[0] + ", "
                    + allTowns[currentTownIndex].getCoordinates()[0] + ").");

            //make an array of walkable towns
            boolean[] walkableTowns = new boolean[numOfTownsSet]; //boolean array of walkable towns; length as long as number of towns created by the user
            boolean isWalkable = false;
            double[] distanceBetween = new double[numOfTownsSet]; //double array of distance between towns
            int unwalkableTowns = 1; //counter for number of unwalkable towns

            //prompting user how far are you willing to walk?
            System.out.println("\nHow far are you willing to walk?");
            distanceWilling = scan.nextInt();

            //flush
            scan.nextLine();

            //fill the walkableTowns array
            for (int i = 0; i < numOfTownsSet; i++) {
                distanceBetween[i] = allTowns[currentTownIndex].calculateDistanceTo(allTowns[i]); //distance between different towns   
                if (distanceBetween[i] > distanceWilling) { //can't walk here
                    walkableTowns[i] = false; //fills walkable towns array
                    unwalkableTowns++;
                } else if (((int) distanceBetween[i]) == 0) {//same coordinates
                    walkableTowns[i] = false;
                    unwalkableTowns++;
                } else { //possible to walk here
                    walkableTowns[i] = true; //fills walkable towns array
                }
            }

            //if no walkable towns, print condescending remark and quit
            if (unwalkableTowns == numOfTownsSet) {
                System.out.println("\nYou are too lazy! You can't go anywhere!");
                System.exit(0);
            }

            //print out true values of walkableTowns array and distanceBetween 
            for (int i = 0; i < numOfTownsSet; i++) {
                if (walkableTowns[i] == true) {
                    System.out.printf("\n%s: %.2f", allTowns[i].getName(), distanceBetween[i]);
                }
            }

            String desiredTown = new String();
            
            boolean desiredTownIsValid = false;
            
            do{
            //prompt user to enter where they want to go
            System.out.println("\nWhere do you want to go?");
            desiredTown = scan.nextLine().toLowerCase();

            //loop through array and find town
            //if exists
            //check if it's full
            //if it is full, print cap statement and reprompt
            //else town is walkable and not full, walk here and change capacities of new and old towns, change currentTown
            //else
            //tell user it's not a valid place
            //reprompt the user
            
            boolean desiredTownIsFull = false;
            boolean desiredTownExists = false;
            int indexOfDesiredTown = currentTownIndex;

            //make and fill an array of all the town names
            String[] townNames = new String[numOfTownsSet];
            for (int i = 0; i < townNames.length; i++) {
                townNames[i] = allTowns[i].getName().toLowerCase();
            }
            
            //check if town exists
            for(int i=0; i<townNames.length; i++){
                if(desiredTown.equals(townNames[i]))
                    indexOfDesiredTown = i;
                else{
                    System.out.println("That town doesn't exist! Try again.");
                    break;
                }
            }
            
            //check to see that town is not full
            if(allTowns[indexOfDesiredTown].isFull()){
                System.out.println("It's too full! Try somewhere else!");
            }
            else{
                if(walkableTowns[indexOfDesiredTown]){
                    System.out.println("Okay!");
                            allTowns[currentTownIndex].decreaseCapacity(1);
                            allTowns[indexOfDesiredTown].increaseCapacity(1);
                            currentTown = allTowns[indexOfDesiredTown];
                            currentTownIndex = indexOfDesiredTown;
                            desiredTownIsValid = true;
                }//check to see if it's walkable
            }
            }while(!desiredTownIsValid);

//            do {
//                for (int i = 0; i < townNames.length; i++) {
//                    //check to see if name exists within array
//                    if (desiredTown.equalsIgnoreCase(townNames[i]) && (walkableTowns[i])) {
//                        //check if town is full
//                        if (allTowns[i].isFull()) {
//                            System.out.println("That town is full! Try somewhere else.");
//                            desiredTown = scan.nextLine();
//                        } else {
//                            System.out.println("Okay!");
//                            allTowns[currentTownIndex].decreaseCapacity(1);
//                            allTowns[i].increaseCapacity(1);
//                            currentTown = allTowns[i];
//                            currentTownIndex = i;
//                            desiredTownIsValid = true;
//                        }
//                    } else {
//                        System.out.println("That town is invalid! Try again.");
//                    }
//                }
//            } while (desiredTownIsValid);

            //funny code starting here
//            do {
//                for (int i = 0; i < walkableTowns.length; i++) {
//                    if ((walkableTowns[i]) && (desiredTown.equalsIgnoreCase(allTowns[i].getName()))) { //it's walkable and it exists
//                        desiredTownExists = true;
//                        if (!allTowns[i].isFull()) { //town is not full
//                            allTowns[currentTownIndex].decreaseCapacity(1);
//                            currentTownIndex = i;
//                            allTowns[i].increaseCapacity(1);
//                            desiredTownIsValid = true;
//                        }
//                        else{
//                            System.out.println("\nSorry, that town is full! Go somewhere else!");
//                            System.out.println("\nWhere do you want to go?");
//                            desiredTown = scan.nextLine();
//                            desiredTownIsValid = false;
//                        }
//                    }
//                    else {
//                        System.out.println("\nThat's not a valid place!");
//                        System.out.println("\nWhere do you want to go?");
//                        desiredTown = scan.nextLine();
//                        desiredTownIsValid = false;
//                        continue;
//                    }
//
////                    if (desiredTown.equals(allTowns[i].getName())) {
////                        if (allTowns[i].isFull()) {
////                            System.out.println("Sorry, that town is full! Go somewhere else!");
////                            System.out.println("Where do you want to go?");
////                            desiredTownIsValid = false;
////                        } else {
////                            desiredTownIsValid = true;
////                            currentTownIndex = i;
////                            allTowns[i].increaseCapacity(1);
////                            allTowns[i].decreaseCapacity(1);
////                        }
////                    } else {
////                        System.out.println("It's not a valid place! Try again.");
////                        System.out.println("Where do you want to go?");
////                        desiredTownIsValid = false;
////                    }
//                }
//            } while (!desiredTownIsValid);
            //ask user if they want to go somewhere else
            String goSomewhereElseConfirm = new String("");
            System.out.println("Do you want to go somewhere else? (yes/no)");
            goSomewhereElseConfirm = scan.nextLine().toLowerCase();
            if (goSomewhereElseConfirm.equals("yes")) {
                walkSomewhereElse = true;
            } else {
                walkSomewhereElse = false;
            }

        } while (walkSomewhereElse = true);

        //check all code below
//          slightly less older prompt
//        int currentTownIndex = allTowns.length - 1;
//        boolean walkingLoop = true;
//        FantasyTown currentTown = new FantasyTown();
//
//        
//        do {
//            //informing user of location
//            System.out.println("\nYou are currently in " + allTowns[currentTownIndex].getName()
//                    + ". Your coordinates are (" + allTowns[currentTownIndex].getCoordinates()[0] + ", "
//                    + allTowns[currentTownIndex].getCoordinates()[0] + ").");
//
//            //walking to other towns
//            //prompt user of distance willing to walk
//            System.out.println("\nHow far are you willing to walk?");
//            int distanceWilling = scan.nextInt();
//
//            //flush scanner
//            scan.nextLine();
//
//            //boolean array of walkable towns - redundant?
//            boolean[] walkableTowns = new boolean[numberOfTowns];
//
//            //calculate distance from start town znd print walkable town and distance
//            int falseCounter = 0;
//            for (int i = 0; i < allTowns.length - 1; i++) {
//                double isWalkable = allTowns[allTowns.length - 1].calculateDistanceTo(allTowns[i]);
//                if ((isWalkable <= distanceWilling)) {
//                    walkableTowns[i] = true;
//                    System.out.printf("\n%s: %.2f kilometres.", allTowns[i].getName(), isWalkable);
//                } else {
//                    walkableTowns[i] = false;
//                    falseCounter++;
//                }
//
//            }
//
//            //check if no towns are walkable
//            if (falseCounter == allTowns.length) {
//                System.out.println("\nYou are a lazy person. You won't walk anywhere!");
//                System.exit(0);
//            }
//
//            //prompt the user with desired walkable town - check this
//            boolean placeReachable = false;
//            while (placeReachable == false) {
//                System.out.println("\nWhere do you want to go?");
//                String desiredTown = scan.nextLine().toLowerCase();
//                for (int i = 0; i < allTowns.length-1; i++) {
//                    if (desiredTown.equals(allTowns[i].getName().toLowerCase())&&(!walkableTowns[i])) {
//                        if (allTowns[i].isFull()) {
//                            System.out.println("Oh no! There's a cap on the population! Go somewhere else!");
//                            break;
//                        } else {
//                            currentTown = allTowns[i];
//                            allTowns[i].increaseCapacity(1);
//                            allTowns[currentTownIndex].decreaseCapacity(1);
//                            currentTownIndex = i;
//                            placeReachable = true;
//                            break;
//                        }
//                    }
//                    else{
//                        System.out.println("\nSorry, that town doesn't exist.");
//                    }
//                }
//            }
        //old prompt
//            System.out.println("\nWhere do you want to go?");
//            String desiredTown = scan.nextLine().toLowerCase();
//            for (int i = 0; i < (allTowns.length - 1); i++) {
//                if (desiredTown.equals(allTowns[i].getName().toLowerCase()) && (!allTowns[i].isFull())) {
//                    currentTown = allTowns[i];
//                    allTowns[i].increaseCapacity(1);
//                    allTowns[currentTownIndex].decreaseCapacity(1);
//                    currentTownIndex = i;
//                    break;
//                } else if (allTowns[i].isFull() && desiredTown.equals(allTowns[i].getName().toLowerCase())) {
//                    System.out.println("\nThe town is full! Enter somewhere else!");
//                    desiredTown = scan.nextLine();
//                    continue;
//                } else {
//                    System.out.println("\nNot a valid place! Try again.");
//                    desiredTown = scan.nextLine();
//                    continue;
//                }
//            }
        //travel again?
//        String goElsewhere = new String();
//        System.out.println("\nDo you want to go somewhere else? (yes/no) > ");
//        goElsewhere = scan.nextLine().toLowerCase();
//        if (!goElsewhere.equals("yes")) {
//            walkingLoop = false;
//        } else {
//            System.out.println("Thanks for playing! Have a happy life!");
//        }
//    }
//    while (walkingLoop 
//
//== true);
    }

}
