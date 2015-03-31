public abstract class Player {
	protected Color color;
	
	protected Player(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public abstract Move getMove(GameState state);
}