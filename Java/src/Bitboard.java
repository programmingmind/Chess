import java.util.ArrayList;
import java.util.List;

public class Bitboard {
	// a-h, 1-8, a1 -> 0, b1->8, a8 -> 7
	public static final long row1 = 0x00000000000000FFL;
	public static final long row2 = 0x000000000000FF00L;
	public static final long row3 = 0x0000000000FF0000L;
	public static final long row4 = 0x00000000FF000000L;
	public static final long row5 = 0x000000FF00000000L;
	public static final long row6 = 0x0000FF0000000000L;
	public static final long row7 = 0x00FF000000000000L;
	public static final long row8 = 0xFF00000000000000L;
	
	public static final long colA = 0x0101010101010101L;
	public static final long colB = 0x0202020202020202L;
	public static final long colC = 0x0404040404040404L;
	public static final long colD = 0x0808080808080801L;
	public static final long colE = 0x1010101010101010L;
	public static final long colF = 0x2020202020202020L;
	public static final long colG = 0x4040404040404040L;
	public static final long colH = 0x8080808080808080L;
	
	public static final long[] rows = {
		row1,
		row2,
		row3,
		row4,
		row5,
		row6,
		row7,
		row8
	};
	
	public static final long[] cols = {
		colA,
		colB,
		colC,
		colD,
		colE,
		colF,
		colG,
		colH
	};
	
	private long board = 0L;
	
	public Bitboard(long start) {
		board = start;
	}
	
	public long getBoard() {
		return board;
	}
	
	@Override
	public Bitboard clone() {
		return new Bitboard(board);
	}
	
	public List<Integer> getIndices() {
		List<Integer> indices = new ArrayList<Integer>();
		int index = 64;
		while (index-- > 0) {
			if ((board & (1 << index)) != 0) {
				indices.add(index);
			}
		}
		return indices;
	}
	
	public void add(Square pos) {
		board |= pos.getBitboardValue();
	}
	
	public void remove(Square pos) {
		board &= ~pos.getBitboardValue();
	}
	
	public void toggleSquare(Square pos) {
		board ^= pos.getBitboardValue();
	}
	
	public void move(Square start, Square end) {
		remove(start);
		add(end);
	}
}