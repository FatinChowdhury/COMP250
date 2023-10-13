package Assignment1;

abstract class Unit {
	private Tile;
	private double hp; // health points
	private int movingRange; // how far this unit can move in a single turn
	private String faction;
	
	
	public Unit(Tile, double hp, int movingRange, String faction) {
		
	}
	
	public final getPosition() { // to modify void later?
		return Tile;
	}
	
	public final double getHP() { // to modify void later?
		return hp;
	}
	
	public final String getFaction() { // to modify void later?
		return faction;
	}
	
	class Settler{	// might have to do extend Unit at Settler.java
		
		public Settler(Tile, double hp, String faction) {
			int movingRange = 2;
		}	
	}
	
	class Worker {	// might have to do extend Unit at Worker.java
		private int jobsPerformed;
		public Worker(Tile, double hp, String faction){
			int movingRange = 2;
			int jobsPerformed = 0;
		}
	}
	
	abstract class MilitaryUnit { // might have to do extend Unit at MilitaryUnit.java
		private double attackDamage;
		private int attackRange;
		private int armor;
		
		public MilitaryUnit(Tile, double hp, int movingRange, String faction, 
				double attackDamage, int attackRange, int armor) {
			class Warrior {
				public Warrior(Tile, double hp, String faction) {
					int movingRange = 1;
					double attackDamage = 20.0;
					int attackRange = 1;
					int armor = 25;
				}
			}
			class Archer{
				private int arrowsAvailable;
				
				public Archer(Tile, double hp, String faction) {
					int movingRange = 2;
					double attackDamage = 15.0;
					int attackRange = 2;
					int armor = 0;
					int arrowsAvailable = 5;
				}
			}
			
			
			
		}
	}
	
}
