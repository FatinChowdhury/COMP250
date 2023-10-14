package Assignment1;
/*Write a class Tile. You can think of a Tile as a square on the board on which the game will
be played. A Tile must have the following private fields:
– An int indicating the x-coordinate of the tile.
– An int indicating the y-coordinate of the tile.
– A boolean indicating whether or not a city has been built on the tile.
– A boolean indicating whether or not the tile received some “improvements”.
– A ListOfUnits containing all the units positioned on the tile.*/
public class Tile {
	private int x;
	private int y;
	private boolean cityBuilt;
	private boolean tileImproved;
	private String[] ListOfUnits2; // what
	
/*The class must also have the following public methods:
– A constructor that takes as input two ints indicating the x and the y coordinate respectively. The constructor creates a Tile with the specified coordinates. A new tile is not a
city, nor it has ever been improved. It also hosts no units. You must represent this by
initializing its corresponding field with an empty ListOfUnits. */
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.cityBuilt = false;
		this.tileImproved = false;
		this.ListOfUnits2 = new String[0];
	}
/*A getX() and a getY() method which return the x and the y coordinate of the tile
respectively.*/
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
/*A isCity() method which returns whether or not the tile is a city.*/
	
	public boolean isCity() { // what
		return cityBuilt;
	}
	
/*A isImproved() method which returns whether or not the tile has been improved.*/
	
	public boolean isImproved() {
		return tileImproved;
	}
	
/*A buildCity() method which turns the tile into a city if it wasn’t already.*/
	
	public boolean buildCity() {
		if (!cityBuilt) {
			cityBuilt = true;
			return true;
		}
		return false;
	}
	
/*A buildImprovement() method which improves the tile if it wasn’t already. */
	
	public boolean buildImprovement() {
		if (!tileImproved) {
			tileImproved = true;
			return true;
		}
		return false;
	}
	
/*An addUnit() method which takes as input a unit and adds it to the tile’s ListOfUnits.
Note that a military unit can be added to the tile if and only if no military unit of a
different faction (the enemies’ army!) is stationed here. Non-military units can always
be added to the tile. The method returns true if the unit was successfully added to the
list, false otherwise.*/
	
	public boolean addUnit(String Unit) {
		if (isMilitaryUnit(unit)) {
			if (hasMilitaryUnitExists(Unit)) {
				return false;
			}
		}
	}
	
	String[] updatedListOfUnits = new String[ListOfUnits.length + 1];
	System.arraycopy(ListOfUnits, 0, updatedListOfUnits, 0, ListOfUnits.length);
        updatedListOfUnits[ListOfUnits.length] = unit;
        ListOfUnits = updatedListOfUnits;
        return true;
}

	private boolean isMilitaryUnit(String unit) {
        // Add your logic to determine if the unit is a military unit
        // For example, you can check if the unit belongs to a specific class or has certain properties
        	return false;
   	}

    	private boolean hasEnemyMilitaryUnit() {
        	// Add your logic to check if there is an enemy military unit on the tile
        	// You can iterate over the ListOfUnits and check if any military unit belongs to the enemy faction
        	return false;
   	}
/* A removeUnit() method which takes as input a unit and removes it from the tile’s
ListOfUnits. The method should also return a boolean indicating whether or not the
operation was successful.*/
	




/*A removeUnit() method which takes as input a unit and removes it from the tile’s
ListOfUnits. The method should also return a boolean indicating whether or not the
operation was successful.*/




/*A static method called getDistance() which takes as input two tiles and returns a
double indicating the distance between the two. Remember that given two points (x1, y1)
and (x2, y2), the distance can be computed with the following formula:
distance = ((x1 - x2)^2  +  (y1-y2)^2 )^0.5. */


