package comp2019_Assignment1;

import static org.junit.Assert.assertTrue;

public class MapTestUtil {

	/** Returns true iff actual is in the range [lower,...,upper].
	 *  Accounts for some small differences in how the explored states may be counted.
	 *
	 * @param actual Number of explored states returned by the unit under test
	 * @param lower  Expected number of explored states
	 * @param upper  Maximum number of explored states
	 */
	static void assertInBetween(int actual, int lower, int upper) {
	    assertTrue("Explored too few states: "+actual+" not in range ["+lower+","+upper+"]", actual >= lower);
	    assertTrue("Explored too many states: "+actual+" not in range ["+lower+","+upper+"]", actual <= upper);
	}

	static void assertExploredOk(int n, int min, int max) {
		int tolerance = 2; // some may not count the start and goal states
	    int lower = Math.max(0,min-tolerance);
		assertInBetween(n, lower, max);
	}
	
}
