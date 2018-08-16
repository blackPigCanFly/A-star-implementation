package comp2019_Assignment1;

public class SearchState implements Comparable<Object> {
    private SearchState parent;

    private Location location;
    private int g;
    private int h;
    private double survivalProbability; // for Question 2

    public SearchState(Location location, int g, int h) {
        this(location, g, h, 1.0);
    }

    public SearchState(Location location, int g, int h, double survivalProbability) {
        this.location = location;
        this.g = g;
        this.h = h;
        this.survivalProbability = survivalProbability;
    }

    public SearchState(SearchState parent, Location location, int g, int h) {
        this(location,g,h);
        this.parent = parent;
    }

    public SearchState(SearchState parent, Location location, int g, int h, double survivalProbability) {
        this(location,g,h,survivalProbability);
        this.parent = parent;
    }

    public Location getLocation() {
        return location;
    }

    public int getF() {
        return g + h;
    }

    public int getG() {
        return g;
    }

    public double getSurvivalProbability() {
        return survivalProbability;
    }

    public Path getPath() {
        if (parent == null) return new Path(location);
        Path path = parent.getPath();
        int gPath = path.getCosts();
        path.moveTo(location, g-gPath);
        return path;
    }

    public String toString() {
        String sP = String.format("%.4f", survivalProbability);
        return getPath().toLocationsString() + " [f=" + getF() + ",g=" + g + ",h=" + h + ", s=" + sP + "]";
    }

    @Override
    public int compareTo(Object other) {
        SearchState otherState = (SearchState)other;
        int dF = getF() - otherState.getF();
        if (dF == 0) {
            return otherState.getG() - getG(); // pick larger G first
        } else {
            return dF;
        }
    }

}
