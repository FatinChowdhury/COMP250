package assignment1;

public class Tile {
	private int x;
	private int y;
	private boolean cityBuilt;
	private boolean tileImproved;
	private Unit[] ListOfUnits;
	
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.cityBuilt = false;
		this.tileImproved = false;
		this.ListOfUnits = new Unit[0];
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	public boolean isCity() { 
		return cityBuilt;
	}
	
	
	public boolean isImproved() {
		return tileImproved;
	}
	
	
	public boolean buildCity() {
		if (!cityBuilt) {
			cityBuilt = true;
			return true;
		}
		return false;
	}
	
	
	public boolean buildImprovement() {
		if (!tileImproved) {
			tileImproved = true;
			return true;
		}
		return false;
	}
	
	
	public boolean addUnit(Unit Unit) {
		if (Unit instanceof MilitaryUnit) {
			for (int position = 0; position<ListOfUnits.length; position++) {
				if (ListOfUnits[position] instanceof MilitaryUnit &&
						!ListOfUnits[position].getFaction().equals(Unit.getFaction())){
					return false; // I used ChatGPT for the 2nd condition of this if statement
				}
			}
		}
		
		for (int position = 0; position < ListOfUnits.length; position++) {
			if (ListOfUnits[position] == null) {
				ListOfUnits[position] = Unit;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeUnit(Unit Unit) {
		for (int position = 0; position < ListOfUnits.length; position++) {
			if (ListOfUnits[position] == Unit) {
				ListOfUnits[position] = null;
				return true; // actually removes the unit
			}
		}
		return false; // or else it wont
	}

	public Unit selectWeakEnemy(String faction) {
		Unit weakestEnemy = null;
		
		for (int position = 0; position < ListOfUnits.length; position++) {
			Unit Unit = ListOfUnits[position];
			if (!Unit.getFaction().equals(faction)) {
				if (weakestEnemy == null || Unit.getHP() < weakestEnemy.getHP()) {
					weakestEnemy = Unit;
				}
			}
		}
		return weakestEnemy;
	}

	
	
	public static double getDistance(Tile tile1, Tile tile2) {
		int x1 = tile1.getX();
		int x2 = tile2.getX();
	    int y1 = tile1.getY();
	    int y2 = tile2.getY();
        double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        return distance;
	}
	
	
}


