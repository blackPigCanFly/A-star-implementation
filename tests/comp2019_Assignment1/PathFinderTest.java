package comp2019_Assignment1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class PathFinderTest {

    /* each test shall complete in less than 10 seconds */
    @Rule
    public TestRule timeout = new DisableOnDebug(new Timeout(10, TimeUnit.SECONDS)); // requires JUnit >= 4.12


    @Test
    public void testWorld01() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain01.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        String correctPath = "(3,2)(3,3)(2,3)(1,3)(0,3)";
        int terrainThreshold = 50;
        PathFinder tester = new PathFinder(proxyMap, new Location(3,2), new Location(0,3), terrainThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 80, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 5,7);
    }

    @Test
    public void testWorld01ImpossibleStart() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain01.txt");
        ((ArrayMap)map).setValueAt(3,2,15); // make terrain impossible to traverse at the start
        ProxyMap proxyMap = new ProxyMap(map);
        int terrainThreshold = 10;
        PathFinder tester = new PathFinder(proxyMap, new Location(3,2), new Location(0,3), terrainThreshold);
        Path calculatedPath = tester.findPath();

        assertNull("Solution must not exist", calculatedPath);
        MapTestUtil.assertExploredOk(tester.getExplored(), 1, 1);
    }

    @Test
    public void testWorld01ImpossibleGoal() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain01.txt");
        ((ArrayMap)map).setValueAt(0,3,15); // make terrain impossible to traverse at the goal
        ProxyMap proxyMap = new ProxyMap(map);
        int terrainThreshold = 10;
        PathFinder tester = new PathFinder(proxyMap, new Location(3,2), new Location(0,3), terrainThreshold);
        Path calculatedPath = tester.findPath();

        assertNull("Solution must not exist", calculatedPath);
        MapTestUtil.assertExploredOk(tester.getExplored(), 4, 4);
  }

    @Test
    public void testWorld03() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain03.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        String correctPath = "(4,1)(4,2)(3,2)(2,2)(1,2)(0,2)(0,3)";
        int terrainThreshold = 50;
        PathFinder tester = new PathFinder(proxyMap, new Location(4,1), new Location(0,3), terrainThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 128, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 14, 14);
    }


    @Test
    public void testWorld03impossibleTerrain() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain03.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        int terrainThreshold = 28;
        PathFinder tester = new PathFinder(proxyMap, new Location(4,1), new Location(0,3), terrainThreshold);
        Path calculatedPath = tester.findPath();

        assertNull("Solution must not exist", calculatedPath);
        MapTestUtil.assertExploredOk(tester.getExplored(), 4, 4);
    }


    @Test
    public void testWorld04() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain04.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        String correctPath = "(20,80)(20,79)(20,78)(20,77)(20,76)(20,75)(20,74)(20,73)(20,72)(20,71)(20,70)(20,69)(20,68)(21,68)(21,67)(22,67)(22,66)(22,65)(23,65)(24,65)(24,64)(24,63)(25,63)(25,62)(26,62)(27,62)(27,61)(28,61)(29,61)(29,60)(30,60)(30,59)(31,59)(32,59)(32,58)(33,58)(34,58)(35,58)(35,57)(36,57)(36,56)(37,56)(38,56)(39,56)(40,56)(40,55)(41,55)(41,54)(42,54)(43,54)(44,54)(45,54)(45,53)(45,52)(46,52)(47,52)(47,51)(48,51)(48,50)(49,50)(49,49)(49,48)(49,47)(50,47)(51,47)(51,46)(52,46)(52,45)(52,44)(53,44)(53,43)(54,43)(54,42)(55,42)(55,41)(55,40)(56,40)(57,40)(58,40)(59,40)(60,40)(61,40)(62,40)(63,40)(64,40)(65,40)(66,40)(67,40)(68,40)(69,40)(70,40)(71,40)(72,40)(73,40)(74,40)(75,40)(76,40)(77,40)(78,40)(79,40)(80,40)";
        int terrainThreshold = 500;
        PathFinder tester = new PathFinder(proxyMap, new Location(20,80), new Location(80,40), terrainThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 24024, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 7207, 7227); 
        // if your number is in the range 7225..7227 you can improve your heuristic function to reduce it to 7207
    }

    @Test
    public void testWorld05() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain05.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        String correctPath = "(9,3)(8,3)(8,4)(7,4)(7,5)(6,5)(6,6)(5,6)(5,7)(5,8)(4,8)(3,8)(2,8)(1,8)(0,8)";
        int terrainThreshold = 40;
        PathFinder tester = new PathFinder(proxyMap, new Location(9,3), new Location(0,8), terrainThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 615, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 46, 47);
    }
}
