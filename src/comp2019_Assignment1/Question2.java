package comp2019_Assignment1;

public class Question2 {

    public static void main(String[] args) {
        if (args.length != 8) {
            System.err.println("Usage: java comp2019_Assignment1.Question2 resources/terrainXX.txt resources/enemyXX.txt  startRow startCol hqRow hqCol terrainThreshold survivalThreshold");
            System.exit(1);
        }

        RectangularMap map = RectangularMapParser.fromFile(args[0]);
        RectangularMap enemyMap = RectangularMapParser.fromFile(args[1]);
        Location start = new Location(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
        Location goal = new Location(Integer.parseInt(args[4]),Integer.parseInt(args[5]));
        int terrainThreshold = Integer.parseInt(args[6]);
        double survivalThreshold = Double.parseDouble(args[7]);

        SafePathFinder pathFinder = new SafePathFinder(map, enemyMap, start, goal, terrainThreshold, survivalThreshold);
        Path path = pathFinder.findPath();
        if (path != null) {
            System.out.print("Path: ");
            System.out.println(path.toLocationsString());
            System.out.print("Path cost: ");
            System.out.println(path.getCosts());
        } else {
            System.out.println("No solution");
        }
        System.out.print("Explored states: ");
        System.out.println(pathFinder.getExplored());
   }

}
