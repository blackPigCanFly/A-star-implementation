package comp2019_Assignment1;

public class Question1 {

    public static void main(String[] args) {
        if (args.length != 6) {
            System.err.println("Usage: java comp2019_Assignment1.Question1 resources/terrainXX.txt startRow startCol hqRow hqCol terrainThreshold");
            System.exit(1);
        }

        RectangularMap map = RectangularMapParser.fromFile(args[0]);
        Location start = new Location(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        Location goal = new Location(Integer.parseInt(args[3]),Integer.parseInt(args[4]));
        int terrainThreshold = Integer.parseInt(args[5]);

        PathFinder pathFinder = new PathFinder(map, start, goal, terrainThreshold);
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
