package assignment1;

abstract class MilitaryUnit extends Unit {
    private double attackDamage;
    private int attackRange;
    private int armor;
    
    public MilitaryUnit(Tile position, double hp, int movingRange, String faction,
            double attackDamage, int attackRange, int armor) {
    	super(position, hp, movingRange, faction);
    	this.attackDamage = attackDamage;
    	this.attackRange = attackRange;
    	this.armor = armor;
    }
    
    public void takeAction(Tile targetTile) {
        if (getPosition().getDistance(targetTile, this.getPosition()) > attackRange) {
            return;
        }
        
        Unit weakestEnemy = targetTile.selectWeakEnemy(this.getFaction());
        if (weakestEnemy != null) {
            double damage = this.attackDamage;
            if (getPosition().isImproved()) {
                damage *= 1.05;
            }
            weakestEnemy.receiveDamage(damage);
        }
    }
    public void receiveDamage(double damageReceived) {
        double armorMultiplier = 100.0 / (100.0 + this.armor);
        damageReceived *= armorMultiplier;
        super.receiveDamage(damageReceived);
    }
    
}
