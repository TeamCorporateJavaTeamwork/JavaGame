package Game.entities;

public class Gate {

	private BoundingBox gate;

	public Gate(int boxStartingPointX, int boxStartingPointY, int boxWidth, int boxHeight) {

		this.gate = new BoundingBox(boxStartingPointX, boxStartingPointY, boxWidth, boxHeight);
	}

	public BoundingBox getBox() {

		return gate;
	}
}
