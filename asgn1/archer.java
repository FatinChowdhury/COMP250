package Assignment1;

class Archer extends MilitaryUnit {
    private int arrowsAvailable;

    public Archer(Tile position, double hp, String faction) {
        super(position, hp, 2, faction, 15.0, 2, 0);
        this.arrowsAvailable = 5;
    }

    private int getArrowsAvailable() {
        return arrowsAvailable;
    }
}
