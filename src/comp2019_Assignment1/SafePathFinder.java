package comp2019_Assignment1;
import java.util.ArrayList;
import java.util.Collections;

public class SafePathFinder {

    protected Location start;	   // start location
    protected Location goal;        // goal location
    protected RectangularMap map;   // the map
    protected RectangularMap enemyMap;  // shows the probability of being captured at each square
    protected int terrainThreshold; // threshold for navigable terrain
    protected double survivalThreshold; // the minimum probability of survival that is required
    private int explored = 0;       // the number of states visited during the search;
    								   // increment this whenever you retrieve a state from the frontier

    public SafePathFinder(RectangularMap map, RectangularMap enemyMap, Location start, Location goal, 
    						 int terrainThreshold, double survivalThreshold) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.terrainThreshold = terrainThreshold;
        this.enemyMap = enemyMap;
        this.survivalThreshold = survivalThreshold;
    }

    public RectangularMap getMap() {
        return map;
    }

    public RectangularMap getEnemyMap() {
        return enemyMap;
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
  
    /* TODO: Question 2
     * add your code below. 
     * you can add extra methods.
     */

   
    public Path findPath() {
        //
        //TODO Question2

		// not yet implemented
		// throw new UnsupportedOperationException();

        //return null if start illegal terrain.
        if(map.getValueAt(start) > terrainThreshold) {return null; }

        //the open list stores paths that need to be checked out.      
        ArrayList<SearchState> openList = new ArrayList<>();

        double survivalProbability = 1 - enemyMap.getValueAt(start) / 100.0;    //calculate survival probability.
        //add start to open list.
        SearchState startState = new SearchState(start, 0, 0, survivalProbability);
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
            explored++;

            //return path if the goal is detected.
            if(currentLocation.equals(goal)) {return curentState.getPath();}

            //get all the reachable adjacent locations.
            Iterable<Location> neighbours = map.getNeighbours(currentLocation);

            //checking these adjacent locations.
            for(Location nextLocation : neighbours){

                int difficulty = map.getValueAt(nextLocation);
                double nextSurvivalProbability = curentState.getSurvivalProbability() * (1 - enemyMap.getValueAt(nextLocation) / 100.0);

                //select locations which meets the demand of difficulty and survival probability.
                if((difficulty <= terrainThreshold) && (nextSurvivalProbability >= survivalThreshold)){
                    int cost = difficulty + map.getValueAt(currentLocation);
                    int g = cost + curentState.getG();  //the movement cost from start to next checked location.
                    int h = (int)(nextLocation.manhattanDistance(goal) * 2);    //the estimate cost from next checked location to goal.
                                                                    //use Manhattan distance multiplied by 2(the number 2 is experimentally obtained).

                    //tempt list stores search state.
                    ArrayList<SearchState> tempStatesOfOpenList = getSearchStatesByLocation(nextLocation, openList);
                    ArrayList<SearchState> tempStatesOfCloseList = getSearchStatesByLocation(nextLocation, closeList);

                    boolean isBetter = true; //boolean value indicates which next checked location is better than old one or not.

                    //compare the F score and survival probability if next checked location already contained in open list.
                    for(SearchState state : tempStatesOfOpenList){
                        int tempF = state.getF();
                        double tempSurvival = state.getSurvivalProbability();

                        if(((g + h) >= tempF) && (nextSurvivalProbability <= tempSurvival)){
                            isBetter = false;
                        }
                        if( (((g + h) <= tempF) && (nextSurvivalProbability > tempSurvival)) || (((g + h) < tempF) && (nextSurvivalProbability >= tempSurvival)) ) {
                            openList.remove(state);
                        }
                    }

                    //compare the F score and survival probability if next checked location already contained in close list.
                    for(SearchState state : tempStatesOfCloseList){
                        int tempF = state.getF();
                        double tempSurvival = state.getSurvivalProbability();
                        if(((g + h) >= tempF) && (nextSurvivalProbability <= tempSurvival)){
                            isBetter = false;
                        }
                        if((((g + h) <= tempF) && (nextSurvivalProbability > tempSurvival)) || ( ( (g + h) < tempF ) && (nextSurvivalProbability >= tempSurvival))) {
                            closeList.remove(state);
                        }
                    }
                    //add it to open list if the next checked location is better.
                    if(isBetter){
                        openList.add(new SearchState(curentState, nextLocation, g, h, nextSurvivalProbability));
                    }
                }
            }
            Collections.sort(openList); //sort open list by the F score.
        }
        return null;    //return null if path is not detected.
    }
    
    private ArrayList<SearchState> getSearchStatesByLocation(Location location, ArrayList<SearchState> stateList){
        ArrayList<SearchState> searchStates = new ArrayList<>();
        for(SearchState state : stateList){
            if(state.getLocation().equals(location)){
                searchStates.add(state);
            }
        }
        return searchStates;
    }

}