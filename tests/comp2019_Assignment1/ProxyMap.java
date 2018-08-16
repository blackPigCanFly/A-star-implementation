package comp2019_Assignment1;

/**
 * Private class used in JUnit tests. DO NOT USE.
 */
public class ProxyMap implements RectangularMap {

	private RectangularMap map;
	private ArrayMap explored;
	
	public ProxyMap(RectangularMap map) {
		this.map = map;
		this.explored = new ArrayMap(map.getRows(), map.getColumns());
	}
	
	@Override
	public int getRows() {
		return map.getRows();
	}

	@Override
	public int getColumns() {
		return map.getColumns();
	}

	@Override
	public int getValueAt(int row, int col) {
		incrementAt(row,col);
		return map.getValueAt(row,col);
	}

	@Override
	public int getValueAt(Location loc) {
		return this.getValueAt(loc.getRow(), loc.getColumn());
	}

	@Override
	public Iterable<Location> getNeighbours(Location loc) {
		return map.getNeighbours(loc);
	}

	@Override
	public String toString() {
		return map.toString();
	}
	
	private void incrementAt(int row, int col) {
		explored.setValueAt(row, col, 1+explored.getValueAt(row, col));
	}
	
	public int getCallCountAt(int row, int col) {
		return explored.getValueAt(row, col);
	}
	
	public int getInvocationCount() {
		int count = 0;
        for(int r = 0; r < explored.getRows(); r++) {
            for(int c = 0; c < explored.getColumns(); c++) {
                count += explored.getValueAt(r, c);
            }
        }
        return count;
    }
	
	public int getExploredCount() {
		int count = 0;
        for(int r = 0; r < explored.getRows(); r++) {
            for(int c = 0; c < explored.getColumns(); c++) {
                if (explored.getValueAt(r, c) > 0) {
                		count ++;
                }
            }
        }
        return count;		
	}
	
	public RectangularMap getExploredMap() {
		return explored;
	}

}
