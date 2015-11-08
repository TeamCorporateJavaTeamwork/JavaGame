package Game.entities;

public class Player {
	private String name;
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
			return new Gate(180 + 30, 80 + 200, 0, 400-200);
		} else if(field == 2) {
			return new Gate(180 + 800 - 30, 80 + 200, 0, 400-200);
		}
		return null;
	}

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
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
