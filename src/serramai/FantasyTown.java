/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.Random;

/**
 * Name: Maiziel Serrao
 * File: FantasyTown.java
 * Main class: Main.java
 * 
 * FantasyTown class represents a program that creates and modifies a fantasy
 * town. A FantasyTown object is characterized by its name, description,
 * coordinates, occupants, and capacity. Methods included in this class can set
 * and alter these variables as well as compare FantasyTowns with each other
 * (for instance, in terms of distance).
 * 
 * @author Maiziel Serrao
 */
public class FantasyTown {

    /**
     * Instance variables of a FantasyTown object. name - the title of the
     * FantasyTown. description - information of the FantasyTown object.
     * occupants - the people who live in the FantasyTown object. capacity - the
     * maximum number of people who can occupy the FantasyTown. coordinates -
     * the latitude and longitude of the FantasyTown object.
     */
    private String name = "A Town Without A Name";
    private String description = "a mysterious place you've never been before";
    private String[] occupants = new String[]{"The Mayor"};
    private int capacity = 1;
    private double[] coordinates = new double[]{((int) (Math.random() * 100)),
        ((int) (Math.random() * 100))};

    /**
     * Default constructor for a FantasyTown object. Sets the FantasyTown name
     * to "A Town Without A Name", the description to "a mysterious place you've
     * never been before", the default occupant array to a length of 1 with an
     * entry of "The Mayor", a capacity of 1, and a random number generator for
     * the coordinates.
     */
    public FantasyTown() {
    }

    /**
     * Constructor for a FantasyTown object.
     *
     * @param name the desired title of the FantasyTown.
     * @param capacity the maximum number of occupants a FantasyTown can have.
     * @param xCoordinate the latitude of the FantasyTown.
     * @param yCoordinate the longitude of the FantasyTown.
     */
    public FantasyTown(String name, int capacity, double xCoordinate,
            double yCoordinate) {
        setName(name);
        setCapacity(capacity);
        setCoordinates(xCoordinate, yCoordinate);
    }

    /**
     * Retrieves name of the FantasyTown object.
     *
     * @return the name of the FantasyTown object.
     */
    public String getName() {
        return name;
    }

    /**
     * Alters the name of a FantasyTown object.
     *
     * @param name the desired name of the FantasyTown.
     * @throws IllegalArgumentException if the desired name is an empty string
     * or null.
     */
    public void setName(String name) {
        if ((name == null) || (name.equals(""))) {
            throw new IllegalArgumentException("Name can't be an empty string "
                    + "or null!");
        } else {
            this.name = name;
        }
    }

    /**
     * Retrieves the description of the FantasyTown object.
     *
     * @return the description of the FantasyTown object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Alters the description of the FantasyTown object.
     *
     * @param description the new desired description of the town.
     * @throws IllegalArgumentException if desired description is an empty
     * string or null.
     */
    public void setDescription(String description) {
        if ((description == null) || (description.equals(""))) {
            throw new IllegalArgumentException("Description can't be an empty "
                    + "string or null!");
        } else {
            this.description = description;
        }
    }

    /**
     * Adds to the description of the FantasyTown object.
     *
     * @param description the desired addition to the description of the
     * FantasyTown object.
     */
    public void addToDescription(String description) {
        this.description += description;
    }

    /**
     * Retrieves the memory location of the occupants' String array.
     *
     * @return the occupants memory location.
     */
    public String[] getOccupants() {
        return occupants;
    }

    /**
     * Retrieves the individual names of the occupants within the occupants
     * String array.
     *
     * @return the names of all the occupants within a FantasyTown object in one
     * string.
     */
    public String occupantString() {
        String occupantArray = new String("");
        int i;
        for (i = 0; i < this.occupants.length; i++) {
            if (i < this.occupants.length - 1) {
                occupantArray += this.occupants[i] + ", ";
            } else {
                occupantArray += "and " + this.occupants[i] + ".";
            }
        }
        return occupantArray;
    }

    /**
     * Chooses a random occupant from a FantasyTown object.
     *
     * @return a random occupant from a FantasyTown object.
     */
    public String getRandomOccupant() {
        Random randomNumGenerator = new Random();
        int randomIndex = randomNumGenerator.nextInt(this.occupants.length - 1)
                + 1;
        return occupants[randomIndex];
    }

    /**
     * Retrieves the number of occupants within a FantasyTown object.
     *
     * @return the length of the occupants array of a FantasyTown object.
     */
    public int getNumberOfOccupants() {
        return occupants.length;
    }

    /**
     * Checks the occupants array of a FantasyTown for an existing occupant with
     * a matching name.
     *
     * @param name the name of the occupant one wants to find in a FantasyTown
     * object.
     * @return true if the occupant exists within the FantasyTown object and
     * false if the occupant does not.
     */
    public boolean hasOccupant(String name) {
        boolean hasOccupant = false;
        for (int i = 0; i < occupants.length - 1; i++) {
            hasOccupant = occupants[i].equals(name);
        }
        return hasOccupant;
    }

    /**
     * Adds an occupant to a FantasyTown object if the town is not already full.
     *
     * @param name the name of the occupant one wants to add to the FantasyTown
     * object.
     * @throws IllegalArgumentException if the name of the occupant is an empty
     * string or null.
     */
    public void addOccupant(String name) {
        if ((this.getCapacity() > this.occupants.length) && (!name.equals(""))
                && (name != null)) {
            String[] newOccupants = new String[this.occupants.length + 1];
            int i;
            for (i = 0; i <= occupants.length - 1; i++) {
                newOccupants[i] = this.occupants[i];
            }
            newOccupants[i] = name;
            this.occupants = newOccupants;
        } else if ((name == null) || (name.equals(""))) {
            throw new IllegalArgumentException("Name of occupant cannot be an "
                    + "empty string or null!");
        } else {
            throw new IllegalArgumentException("Number of occupants cannot "
                    + "exceed capacity!");
        }
    }

    /**
     * Removes one occupant from the FantasyTown object from the last index of
     * the occupants array.
     */
    public void removeOccupant() {
        String[] newOccupants = new String[this.occupants.length - 1];
        int i;
        for (i = 0; i <= newOccupants.length - 1; i++) {
            newOccupants[i] = this.occupants[i];
        }
        this.occupants = newOccupants;
    }

    /**
     * Retrieves the maximum amount of occupants a FantasyTown object can have.
     *
     * @return the capacity of the FantasyTown object.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Alters the capacity of the FantasyTown object.
     *
     * @param capacity the new desired capacity of the FantasyTown object.
     * @throws IllegalArgumentException if the desired capacity is not a
     * positive integer.
     */
    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be at least 1!");
        }
    }

    /**
     * Extends the maximum number of occupants that can occupy a FantasyTown
     * object.
     *
     * @param amount the desired value by which to increment the FantasyTown
     * capacity.
     * @throws IllegalArgumentException if the desired amount is not a positive
     * integer.
     */
    public void increaseCapacity(int amount) {
        if (amount > 0) {
            this.capacity += amount;
        } else {
            throw new IllegalArgumentException("The population isn't declining!"
                    + " Add a positive integer!");
        }
    }

    /**
     * Checks if a FantasyTown object has reached its maximum capacity.
     *
     * @return true if the FantasyTown object has no more vacancies.
     */
    public boolean isFull() {
        Boolean isItFull = true;
        if (this.occupants.length == getCapacity()) {
            isItFull = true;
        } else {
            isItFull = false;
        }
        return isItFull;
    }

    /**
     * Retrieves the coordinates of the FantasyTown object.
     *
     * @return the coordinates of the FantasyTown object.
     */
    //overlapping coordinates okay?
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * Alters the FantasyTown objects x and y coordinates
     *
     * @param xCoordinate the desired latitude of the FantasyTown object.
     * @param yCoordinate the desired longitude of the FantasyTown object.
     * @throws IllegalArgumentException if the x or y coordinate is not an
     * integer between (or including) 0 and 100.
     */
    public void setCoordinates(double xCoordinate, double yCoordinate) {
        if ((xCoordinate >= 0) && (xCoordinate <= 100)) {
            this.coordinates[0] = xCoordinate;
        } else {
            throw new IllegalArgumentException("x coordinate must be between 0"
                    + " and 100!");
        }

        if ((yCoordinate >= 0) && (yCoordinate <= 100)) {
            this.coordinates[1] = yCoordinate;
        } else {
            throw new IllegalArgumentException("y coordinate must be between 0"
                    + " and 100!");
        }
    }

    /**
     * Evaluates the distance between two FantasyTown objects.
     *
     * @param nearbyTown the FantasyTown object that another FantasyTown object
     * is compared to.
     * @return the distance between two FantasyTown objects.
     */
    public double calculateDistanceTo(FantasyTown nearbyTown) {
        double[] nearbyTownCoordinates = nearbyTown.getCoordinates();
        double nearbyTownX = nearbyTownCoordinates[0];
        double differenceInX = nearbyTownX - this.coordinates[0];
        double nearbyTownY = nearbyTownCoordinates[1];
        double differenceInY = nearbyTownY - this.coordinates[1];
        double distanceBetweenTowns = Math.sqrt((Math.pow(differenceInX, 2))
                + (Math.pow(differenceInY, 2)));
        return distanceBetweenTowns;
    }

    /**
     * Creates a string summarizing the name, coordinates, number of occupants,
     * capacity and description of the FantasyTown object.
     *
     * @return the name, coordinates, number of occupants, capacity and
     * description of the FantasyTown object.
     */
    public String toString() {
        String format = "\n%s is located at (%.2f, %.2f) and is occupied by %d "
                + "out of a possible %d people.\n%s is %s.";
        return String.format(format, this.getName(), this.getCoordinates()[0],
                this.getCoordinates()[1], this.getNumberOfOccupants(),
                this.getCapacity(), this.getName(), this.getDescription());
    }

}
