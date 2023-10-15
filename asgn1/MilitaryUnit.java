package Assignment1;

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
}
