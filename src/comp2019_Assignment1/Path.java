package comp2019_Assignment1;

import java.util.ArrayList;

/**
 * Represents a path on a map.
 * A path consists of a sequence of locations and the costs of moving between adjacent locations.
 *
 * DO NOT MODIFY THE SIGNATURE OF EXISTING METHODS.
 * Otherwise, JUnit tests will fail and you will receive no credit for your code.
 * Of course, you can add additional methods and classes in your implementation.
 *
 */
public class Path {

    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<Integer>  stepCosts = new ArrayList<>();

    public Path(Location initialPosition) {
        locations.add(initialPosition);
    }

    public void moveTo(Location loc, Integer cost) {
        locations.add(loc);
        stepCosts.add(cost);
    }

    public int getLength() {
        return stepCosts.size();
    }

    public int getCosts() {
        int cost=0;
        for(Integer c : stepCosts) {
            cost += c;
        }
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder repr = new StringBuilder();
        repr.append(locations.get(0));
        for(int i=0; i<locations.size()-1; i++) {
            repr.append("--");
            repr.append(stepCosts.get(i));
            repr.append("--");
            repr.append(locations.get(i+1));
        }
        repr.append(" [path costs: ");
        repr.append(getCosts());
        repr.append("]");
        return repr.toString();
    }

    public String toLocationsString() {
        StringBuilder repr = new StringBuilder();
        for(Location loc : locations) {
            repr.append(loc);
        }
        return repr.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((locations == null) ? 0 : locations.hashCode());
        result = prime * result
                + ((stepCosts == null) ? 0 : stepCosts.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Path other = (Path) obj;
        if (locations == null) {
            if (other.locations != null)
                return false;
        } else if (!locations.equals(other.locations))
            return false;
        if (stepCosts == null) {
            if (other.stepCosts != null)
                return false;
        } else if (!stepCosts.equals(other.stepCosts))
            return false;
        return true;
    }
}
