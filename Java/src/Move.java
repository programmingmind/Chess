public class Move {
	protected final Square start, end;
	
	public Move(Square start, Square end) {
		this.start = start;
		this.end = end;
	}
	
	public Square getStart() {
		return start;
	}
	
	public Square getEnd() {
		return end;
	}
}