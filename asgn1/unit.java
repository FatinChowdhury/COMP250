package assignment1;

abstract class Unit {
	private Tile position;
	private double hp; // health points
	private int movingRange; // how far this unit can move in a single turn
	private String faction;
	
	public Unit(Tile position, double hp, int movingRange, String faction) {
		
		if (!position.addUnit(this)) {
			throw new IllegalArgumentException();
		}
		else {
			position.addUnit(this);
		}
		
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
	
	public boolean moveTo(Tile targetTile) {
        if (position.getDistance(targetTile, targetTile) <= movingRange) {
        	if (position.addUnit(this));
            position.removeUnit(this);
            targetTile.addUnit(this);
            position = targetTile;
            return true;
            }
        
        return false;
    }
	public void receiveDamage(double damageReceived) {
		if (damageReceived > 0) {
			if (this.position.isCity()) {
				damageReceived = 0.9*damageReceived;
				hp -= damageReceived;
			}
			else if (!this.position.isCity()) {
				hp -= damageReceived;
			}
			if (hp <= 0) {
				removeUnit();
			}	
		}
	}
	protected void removeUnit() {
		if (this.position != null) {
			position.removeUnit(this);
			position = null;
		}
		return;
	}
	
	public abstract void takeAction(Tile position);
	
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }

	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }

	    Unit other = (Unit) obj;
	    // needed help with GPT for this return statement
	    return this.position.equals(other.position) &&
	    	Math.pow(this.hp - other.hp, 2) < 0.001 && 
	        this.faction.equals(other.faction);
	}
	
}
