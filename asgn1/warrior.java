package assignment1;

class Warrior extends MilitaryUnit {
	
    public Warrior(Tile position, double hp, String faction) {
        super(position, hp, 1, faction, 20.0, 1, 25);
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
