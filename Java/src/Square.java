public class Square {
	private final int pos;
	
	public Square(int col, int row) {
		pos = col * 8 + row;
	}
	
	public Square(int pos) {
		this.pos = pos;
	}
	
	public long getBitboardValue() {
		return 1 << pos;
	}
	
	@Override
	public int hashCode() {
		return pos;
	}
}