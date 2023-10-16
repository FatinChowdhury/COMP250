package assignment1;

class Worker extends Unit {
    private int jobsPerformed;

    public Worker(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
        this.jobsPerformed = 0;
    }
    public void takeAction(Tile targetTile) {
        if (getPosition() == targetTile && !targetTile.isImproved()) {
            targetTile.buildImprovement();
            jobsPerformed++;
            if (jobsPerformed >= 10) {
                removeUnit();
            }
        }
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Worker other = (Worker) obj;
        return this.jobsPerformed == other.jobsPerformed;
    }    
}
