package assignment1;

abstract class Archer extends MilitaryUnit {
    private int arrowsAvailable;

    public Archer(Tile position, double hp, String faction) {
        super(position, hp, 2, faction, 15.0, 2, 0);
        this.arrowsAvailable = 5;
    }
    
    public void takeAction(Tile targetTile) {
        if (arrowsAvailable == 0) {
            arrowsAvailable = 5; 
        } else {
            super.takeAction(targetTile);
            arrowsAvailable--;
        }
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!super.equals(obj)) {
            return false;
        }
        
        Archer other = (Archer) obj;
        return this.arrowsAvailable == other.arrowsAvailable;
    }


    
}
