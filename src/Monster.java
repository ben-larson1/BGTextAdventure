
public class Monster extends Entity{
	public int health;
	public int attackVal;
	
	public Monster(){
		health = 100;
	}
	
	public Monster(int _health, int _attackVal) {
		health = _health;
		attackVal = _attackVal;
	}
	
	public int getHealth() { return health; }
	
	public void damage(int amount) {
		health -= amount;
	}
	
	public void heal(int amount) {
		health += amount;
	}
	
	public void setHealth(int _health) {
		health = _health;
	}
}
