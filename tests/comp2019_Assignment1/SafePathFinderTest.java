package comp2019_Assignment1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SafePathFinderTest {

    /* each test shall complete in less than 20 seconds */
    /* really, only testWorld04 should take anywhere near that long */
    @Rule
    public TestRule timeout = new DisableOnDebug(new Timeout(40, TimeUnit.SECONDS)); // requires JUnit >= 4.12

    @Test
    public void testWorld01a() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain01.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy01.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        String correctPath = "(3,2)(3,1)(3,0)(2,0)(1,0)(0,0)(0,1)(0,2)(0,3)";
        int terrainThreshold = 50;
        double survivalThreshold = 0.75;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                                               new Location(3,2), new Location(0,3),
                                               terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 240, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 10, 10); // you may get slightly different numbers for this
    }

    @Test
    public void testWorld01b() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain01.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy01.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        String correctPath = "(3,2)(3,1)(3,0)(2,0)(1,0)(1,1)(0,1)(0,2)(0,3)";
        int terrainThreshold = 50;
        double survivalThreshold = 1.0;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                new Location(3,2), new Location(0,3),
                terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 300, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 10, 10); // you may get slightly different numbers for this
    }

    @Test
    public void testWorld01c() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain01.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy01.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        String correctPath = "(3,2)(3,3)(2,3)(1,3)(0,3)";
        int terrainThreshold = 50;
        double survivalThreshold = 0.20;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                new Location(3,2), new Location(0,3),
                terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 80, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 6, 6); // you may get slightly different numbers for this
    }

    @Test
    public void testWorld01d() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain01.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy01.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        int terrainThreshold = 10;
        double survivalThreshold = 0.30;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                new Location(3,2), new Location(0,3),
                terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNull("Solution must not exist", calculatedPath);
        MapTestUtil.assertExploredOk(tester.getExplored(), 2, 2); // you may get slightly different numbers for this
    }

    @Test
    public void testWorld02() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain02.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy02.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        String correctPath = "(3,3)(3,2)(3,1)(3,0)(2,0)(1,0)(1,1)(1,2)(0,2)(0,3)";
        int terrainThreshold = 50;
        double survivalThreshold = 0.60;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                new Location(3,3), new Location(0,3),
                terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 240, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 14, 14); // you may get slightly different numbers for this
    }

    @Test
    public void testWorld03() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain03.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy03.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        String correctPath = "(4,1)(4,2)(3,2)(2,2)(1,2)(0,2)(0,3)";
        int terrainThreshold = 50;
        double survivalThreshold = 0.5;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                new Location(4,1), new Location(0,3),
                terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 128, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 15, 15); // you may get slightly different numbers for this
    }

    @Test
    public void testWorld03highThreshold() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain03.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy03.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        String correctPath = "(4,1)(3,1)(2,1)(1,1)(0,1)(0,2)(0,3)";
        int terrainThreshold = 50;
        double survivalThreshold = 0.90;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                new Location(4,1), new Location(0,3),
                terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 168, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 14, 14); // you may get slightly different numbers for this
    }

    @Test
    public void testWorld04() {
        RectangularMap map = RectangularMapParser.fromFile("resources/terrain04.txt");
        ProxyMap proxyMap = new ProxyMap(map);
        RectangularMap emap = RectangularMapParser.fromFile("resources/enemy04.txt");
        ProxyMap proxyEMap = new ProxyMap(emap);
        String correctPath = "(20,80)(20,79)(20,78)(20,77)(20,76)(20,75)(20,74)(20,73)(20,72)(20,71)(20,70)(20,69)(20,68)(21,68)(21,67)(22,67)(22,66)(22,65)(23,65)(24,65)(24,64)(24,63)(25,63)(25,62)(26,62)(27,62)(27,61)(28,61)(29,61)(29,60)(30,60)(30,59)(31,59)(32,59)(32,58)(33,58)(34,58)(35,58)(35,57)(36,57)(36,56)(37,56)(38,56)(39,56)(40,56)(40,55)(41,55)(41,54)(42,54)(43,54)(44,54)(45,54)(45,53)(45,52)(46,52)(47,52)(47,51)(47,50)(47,49)(47,48)(47,47)(48,47)(48,46)(48,45)(48,44)(48,43)(49,43)(50,43)(50,42)(50,41)(51,41)(51,40)(51,39)(52,39)(53,39)(53,38)(54,38)(55,38)(56,38)(57,38)(58,38)(59,38)(60,38)(61,38)(62,38)(63,38)(64,38)(65,38)(66,38)(67,38)(68,38)(69,38)(70,38)(70,39)(70,40)(71,40)(72,40)(73,40)(74,40)(75,40)(76,40)(77,40)(78,40)(79,40)(80,40)";
        int terrainThreshold = 200;
        double survivalThreshold = 0.50;
        SafePathFinder tester = new SafePathFinder(proxyMap, proxyEMap,
                new Location(20,80), new Location(80,40), terrainThreshold, survivalThreshold);
        Path calculatedPath = tester.findPath();

        assertNotNull("Solution must exist", calculatedPath);
        assertEquals("Path incorrect", correctPath, calculatedPath.toLocationsString());
        assertEquals("Path costs incorrect", 24836, calculatedPath.getCosts());
        MapTestUtil.assertExploredOk(tester.getExplored(), 28756, 28756); // you may get slightly different numbers for this
    }

}
