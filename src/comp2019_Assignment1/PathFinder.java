package comp2019_Assignment1;
import java.util.ArrayList;
import java.util.Collections;


/**
 * This class finds the best path from a start location to the goal (HQ) location given the map.
 * Each location along the path, including start and end location, must have a terrain value less than or equal to a given threshold.
 * The entry point for your code is in method findPath().
 *
 * DO NOT MODIFY THE SIGNATURE OF EXISTING METHODS AND VARIABLES.
 * Otherwise, JUnit tests will fail and you will receive no credit for your code.
 * Of course, you can add additional methods and classes in your implementation.
 *
 */
public class PathFinder {

    protected Location start;	   // start location
    protected Location goal;        // goal location
    protected RectangularMap map;   // the map
    protected int terrainThreshold; // threshold for navigable terrain
    private int explored = 0;       // the number of states visited during the search;
    								   // increment this whenever you retrieve a state from the frontier

    public PathFinder(RectangularMap map, Location start, Location goal, int terrainThreshold) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.terrainThreshold = terrainThreshold;
    }

    public RectangularMap getMap() {
        return map;
    }

    public Location getStart() {
        return start;
    }

    public Location getGoal() {
        return goal;
    }

    public int getTerrainThreshold() {
        return terrainThreshold;
    }

    public int getExplored() {
        return explored;
    }

	/* DO NOT CHANGE THE CODE ABOVE */
    /* adding imports and variables is okay. */
    
    /* TODO: Question 1
     * add your code below. 
     * you can add extra methods.
     */

    public Path findPath() {
        //
        //TODO Question1
        // Implement A* search that finds the best path from start to goal.
        // Return a Path object if a solution was found; return null otherwise.
        // Refer to the assignment specification for details about the desired path.

		// not yet implemented
		// throw new UnsupportedOperationException();
        
        //return null if start illegal terrain.
        if(map.getValueAt(start) > terrainThreshold) {return null;}
        
        //the open list stores paths that need to be checked out.
        ArrayList<SearchState> openList = new ArrayList<>();
        
        //add start to open list.
        SearchState startState = new SearchState(start, 0, 0);
        openList.add(startState); 

        //the closed list stores paths that have been checked out.
        ArrayList<SearchState> closeList = new ArrayList<>();

        //repeat for detecting goal from open list until it is empty.
        while(!openList.isEmpty()){

            //get the first path and its location from open list.
            //drop it from open list and add it to the closed list.
            SearchState curentState = openList.remove(0);
            Location currentLocation = curentState.getLocation();
            closeList.add(curentState);

            explored++; //increment when a state is checked;

            //return path if the goal is detected.
            if(currentLocation.equals(goal)) {return curentState.getPath();}

            //get all the reachable adjacent locations.
            Iterable<Location> neighbours = map.getNeighbours(currentLocation);

            //checking adjacent locations.
            for(Location nextLocation : neighbours){

                int difficulty = map.getValueAt(nextLocation);

                //select locations which meets the demand of difficulty.
                if(difficulty <= terrainThreshold){

                    int cost = difficulty + map.getValueAt(currentLocation);
                    int g = cost + curentState.getG();                 //the movement cost from start to next checked location.
                    int h = nextLocation.manhattanDistance(goal);   //the estimate cost from next checked location to goal.
                                                                    //use Manhattan distance.
                    
                    SearchState tempState = null;   //tempt state

                    //compare the F score if next checked location already be contained in open list or close list.
                    if((tempState = getSearchStateByLocation(nextLocation, openList)) != null){
                        if((tempState.getF()) > (g + h)){
                            //replace the old path if win
                            tempState = new SearchState(curentState, nextLocation, g, h);
                        }

                    }else if((tempState = getSearchStateByLocation(nextLocation, closeList)) != null){
                        if((tempState.getF()) > (g + h)){
                            //drop the old path, add it to open list and replace with new path.
                            closeList.remove(tempState);
                            tempState = new SearchState(curentState, nextLocation, g, h);
                            openList.add(tempState);
                        }
                    }else{
                        //just add it to open list if next checked location is purely a new one.
                        SearchState nextState = new SearchState(curentState, nextLocation, g, h);
                        openList.add(nextState);
                    }

                }
            }
            Collections.sort(openList); //sort open list by F score.
        }
        return null;    //return null if goal is not detected.
    }
    
    
    private SearchState getSearchStateByLocation(Location location, ArrayList<SearchState> stateList){
        for(SearchState state : stateList){
            if(state.getLocation().equals(location)){
                return state;
            }
        }
        return null;
    }

}
