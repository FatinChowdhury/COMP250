package Assignment1;

class Worker extends Unit {
    private int jobsPerformed;

    public Worker(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
        this.jobsPerformed = 0;
    }
    private int getJobsPerformed() { 
        return jobsPerformed;
    }

    private void performJob() {
        jobsPerformed++;
    }
}
