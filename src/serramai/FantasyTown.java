/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.Random;

/**
 *
 * @author Maiziel Serrao
 */
public class FantasyTown {

//instance variables
    private String name = "A Town Without A Name";
    private String description = "a mysterious place you've never been before.";
    private String[] occupants = new String[]{"The Mayor"};
    private int capacity = 1;
    private double[] coordinates = new double[]{0, 0};


    public FantasyTown() {
    }

    //constructor with four parameters
    public FantasyTown(String name, int capacity, double xCoordinate,
            double yCoordinate) {
        setName(name);
        setCapacity(capacity);
        setCoordinates(xCoordinate, yCoordinate);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        if ((description == null) || (description.equals(""))) {
            throw new IllegalArgumentException("Description can't be an empty "
                    + "string or null!");
        } else {
            this.description = description;
        }
    }

    public void addToDescription(String description) {
        this.description += description;
    }

    /**
     * @return the occupants
     */
    public String[] getOccupants() {
        return occupants;
    }

    //does he mean this??
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

    public String getRandomOccupant() {
        Random randomNumGenerator = new Random();
        int randomIndex = randomNumGenerator.nextInt(this.occupants.length - 1) + 1;
        return occupants[randomIndex];
    }

    public int getNumberOfOccupants() {
        return occupants.length;
    }

    public boolean hasOccupant(String name) {
        boolean hasOccupant = false;
        for (int i = 0; i < occupants.length - 1; i++) {
            hasOccupant = occupants[i].equals(name);
        }
        return hasOccupant;
    }

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
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be at least 1!");
        }
    }

    public void increaseCapacity(int amount) {
        if (amount > 0) {
            this.capacity += amount;
        } else {
            throw new IllegalArgumentException("The population isn't declining!"
                    + " Add a positive integer!");
        }
    }

    public void decreaseCapacity(int amount) {
        if ((amount > 0) && (amount < this.getCapacity())) {
            this.capacity -= amount;
        } else {
            throw new IllegalArgumentException("You can't lose more people than you have!");
        }
    }

    
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
     * @return the coordinates
     */
    //overlapping coordinates okay?
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
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

    
    public String toString() {
        String format = "\n%s is located at (%.2f, %.2f) and is occupied by %d "
                + "out of a possible %d people.\n%s is %s.";
        return String.format(format, this.getName(), this.getCoordinates()[0],
                this.getCoordinates()[1], this.getNumberOfOccupants(),
                this.getCapacity(), this.getName(), this.getDescription());
    }

}
