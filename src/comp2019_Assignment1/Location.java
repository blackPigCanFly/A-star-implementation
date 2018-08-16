package comp2019_Assignment1;

/**
 * This class represents a location on a rectangular map.
 * A location is a pair (row,column).
 * The top left corner of the map is location (0,0);
 * The top right corner is at (0,w-1), where w is the number of columns in the grid.
 *
 * DO NOT MODIFY THE SIGNATURE OF EXISTING METHODS.
 * Otherwise, JUnit tests will fail and you will receive no credit for your code.
 * Of course, you can add additional methods and classes in your implementation.
 *
 */
public class Location {
    private int row, column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Location other = (Location) obj;
        return this == obj || other.column == column && other.row == row;
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

    public int manhattanDistance(Location location) {
        return Math.abs(getRow()-location.getRow()) + Math.abs(getColumn()-location.getColumn());
    }

}

