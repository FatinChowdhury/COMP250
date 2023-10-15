package Assignment1;

abstract class Unit {
	private Tile position;
	private double hp; // health points
	private int movingRange; // how far this unit can move in a single turn
	private String faction;
	
	public Unit(Tile position, double hp, int movingRange, String faction) {
		this.position = position;
        	this.hp = hp;
        	this.movingRange = movingRange;
        	this.faction = faction;
	}
	
	
	public final Tile getPosition() {
		return position;
	}
	
	public final double getHP() {
		return hp;
	}
	
	public final String getFaction() {
		return faction;
	}
	
}
	

			
	
