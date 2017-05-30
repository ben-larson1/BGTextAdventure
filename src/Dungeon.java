
public class Dungeon extends Structure{
	private int level;
	Monster monster;
	
	public Dungeon() {
		level = 2;
		generateMonster();
	}
	
	/**
	 * 
	 * @param _level : 1-3 with 3 being most difficult
	 */
	public Dungeon(int _level) {
		level = _level;
		generateMonster();
	}
	
	private void generateMonster() {
		switch(level) {
		case 1:
			monster = new Monster(50,3);
			break;
		case 2:
			monster = new Monster(100, 6);
			break;
		case 3:
			monster = new Monster(150,10);
			break;
		}
	}
	
	public Monster getMonster() { return monster; }
	
	public void damageMonster(int damage) {
		monster.damage(damage);
	}
	
}
