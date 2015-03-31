public class Game {
	private final Player white, black;
	
	private final GameState state;
	
	public Game(Player white, Player black) {
		this.white = white;
		this.black = black;
		
		state = new GameState();		
	}
	
	public boolean hasEnded() {
		// TODO anything!
		return false;
	}
	
	public void step() {
		Player toPlay = state.getColor() == Color.WHITE ? white : black;
		Move move = toPlay.getMove(state);
		state.applyMove(move);
	}
}