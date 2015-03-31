import java.util.Random;

public class Piece {
	private final static Random rand = new Random();
	
	public final static Piece WHITE_KING   = new Piece(1000000);
	public final static Piece WHITE_QUEEN  = new Piece(900);
	public final static Piece WHITE_BISHOP = new Piece(500);
	public final static Piece WHITE_KNIGHT = new Piece(300);
	public final static Piece WHITE_ROOK   = new Piece(300);
	public final static Piece WHITE_PAWN   = new Piece(100);
	
	public final static Piece BLACK_KING   = new Piece(1000000);
	public final static Piece BLACK_QUEEN  = new Piece(900);
	public final static Piece BLACK_BISHOP = new Piece(500);
	public final static Piece BLACK_KNIGHT = new Piece(300);
	public final static Piece BLACK_ROOK   = new Piece(300);
	public final static Piece BLACK_PAWN   = new Piece(100);
	
	private int value;
	private long[] rand1, rand2;
	
	private Piece(int value) {
		this.value = value;
		rand1 = new long[64];
		rand2 = new long[64];
		
		for (int i = 0; i < 64; i++) {
			rand1[i] = rand.nextLong();
			rand2[i] = rand.nextLong();
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public long[] getRand1() {
		return rand1;
	}
	
	public long[] getRand2() {
		return rand2;
	}
}