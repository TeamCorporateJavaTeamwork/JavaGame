package Game.entities;

public class Player {
	private String name;
	private int[] charKeyName;
	private int score;

	private Mallet mallet;
	private Gate gate;

	public Player(String name, int posX, int posY, int field) {
		this.score = 0;
		this.name = name;
		this.mallet = new Mallet(posX, posY, field);
		
		this.gate = createGate(field);
	}

	private Gate createGate(int field) {

		if(field == 1) {
			return new Gate(180 + 30, 55 + 200, 0, 400-200);
		} else if(field == 2) {
			return new Gate(180 + 800 - 30, 55 + 200, 0, 400-200);
		}
		return null;
	}

	public void convertNameCharsToKeys() {
		this.charKeyName = new int[name.length()];
		String upperName = this.name.toUpperCase();

		for (int i = 0; i < this.name.length(); i++) {
			this.charKeyName[i] = ((int)upperName.charAt(i)) - 65;
			if(this.charKeyName[i] < 0) {
				this.charKeyName[i] = ((int)upperName.charAt(i)) - 21;
				if(this.charKeyName[i] < 25) {
					this.charKeyName[i] = 26;
				}
			}
		}
	}


	public void setName(String name) {

		this.name = name;
	}

	public int[] getCharKeyName() {
		return charKeyName;
	}

	public int getScore() {

		return this.score;
	}

	public void setScore(int score) {

		this.score = score;
	}

	public Mallet getMallet() {

		return this.mallet;
	}

	public Gate getGate() {

		return this.gate;
	}
}
