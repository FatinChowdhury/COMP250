package assignment1;

class Settler extends Unit {
    public Settler(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
    }

	public void takeAction(Tile targetTile) {
		if (this.getPosition() == targetTile && !targetTile.isCity()) {
			targetTile.buildCity();
			removeUnit();
		}
	}
	
	public boolean equals(Object obj) {
	    if (this == obj) {
	    	return true;
	    }
		if (!super.equals(obj)) {  
	        return false;
		}
	    return true;
	}
}
