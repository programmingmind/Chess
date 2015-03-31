import java.util.LinkedList;
import java.util.List;

public class GameState {
	public static class GameStateVal {
		private final Color color;
		private final long val1, val2;
		private final int hash;
		
		private GameStateVal(Color color, long val1, long val2) {
			this.color = color;
			this.val1 = val1;
			this.val2 = val2;
			
			this.hash = new Long(val1).hashCode();
		}
		
		@Override
		public int hashCode() {
			return hash;
		}
		
		@Override
		public boolean equals(Object other) {
			if (other != null && other instanceof GameStateVal) {
				GameStateVal tmp = (GameStateVal)other;
				return color == tmp.color && val1 == tmp.val1 && val2 == tmp.val2;
			}
			return false;
		}
	}
	
	public final Bitboard white;
	public final Bitboard black;
	
	public final Bitboard whiteKing;
	public final Bitboard whiteQueen;
	public final Bitboard whiteBishop;
	public final Bitboard whiteKnight;
	public final Bitboard whiteRook;
	public final Bitboard whitePawn;
	
	public final Bitboard blackKing;
	public final Bitboard blackQueen;
	public final Bitboard blackBishop;
	public final Bitboard blackKnight;
	public final Bitboard blackRook;
	public final Bitboard blackPawn;
	
	private Color color;
	private List<Move> history;
	
	public GameState() {
		white = new Bitboard(Bitboard.row1 | Bitboard.row2);
		black = new Bitboard(Bitboard.row7 | Bitboard.row8);
		
		whiteKing   = new Bitboard( Bitboard.row1 & Bitboard.colE);
		whiteQueen  = new Bitboard( Bitboard.row1 & Bitboard.colD);
		whiteBishop = new Bitboard((Bitboard.row1 & Bitboard.colC) | 
								   (Bitboard.row1 & Bitboard.colF));
		whiteKnight = new Bitboard((Bitboard.row1 & Bitboard.colB) | 
				   				   (Bitboard.row1 & Bitboard.colG));
		whiteRook   = new Bitboard((Bitboard.row1 & Bitboard.colA) | 
				   				   (Bitboard.row1 & Bitboard.colH));
		whitePawn   = new Bitboard( Bitboard.row2);
		
		blackKing   = new Bitboard( Bitboard.row8 & Bitboard.colE);
		blackQueen  = new Bitboard( Bitboard.row8 & Bitboard.colD);
		blackBishop = new Bitboard((Bitboard.row8 & Bitboard.colC) | 
								   (Bitboard.row8 & Bitboard.colF));
		blackKnight = new Bitboard((Bitboard.row8 & Bitboard.colB) | 
				   				   (Bitboard.row8 & Bitboard.colG));
		blackRook   = new Bitboard((Bitboard.row8 & Bitboard.colA) | 
				   				   (Bitboard.row8 & Bitboard.colH));
		blackPawn   = new Bitboard( Bitboard.row7);
		
		color = Color.WHITE;
		
		history = new LinkedList<Move>();
	}
	
	private GameState(GameState original) {
		white = original.white.clone();
		black = original.black.clone();
		
		whiteKing = original.whiteKing.clone();
		whiteQueen = original.whiteQueen.clone();
		whiteBishop = original.whiteBishop.clone();
		whiteKnight = original.whiteKnight.clone();
		whiteRook = original.whiteRook.clone();
		whitePawn = original.whitePawn.clone();
		
		blackKing = original.blackKing.clone();
		blackQueen = original.blackQueen.clone();
		blackBishop = original.blackBishop.clone();
		blackKnight = original.blackKnight.clone();
		blackRook = original.blackRook.clone();
		blackPawn = original.blackPawn.clone();
	}
	
	@Override
	public GameState clone() {
		return new GameState(this);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void applyMove(Move move) {
		//TODO STUFF HERE
		history.add(move);
		color = Color.toggleColor(color);
	}
	
	public Move getLastMove() {
		int count = history.size();
		return count > 0 ? history.get(count-1) : null;
	}
	
	private static long addInRand(long val, Bitboard board, long[] rand) {
		for (int index : board.getIndices()) {
			val ^= rand[index];
		}
		return val;
	}
	
	private static int addInVal(Bitboard board, int value) {
		return value * board.getIndices().size();
	}
	
	public GameStateVal getGameStateVal() {
		long val1 = 0;
		long val2 = 0;
		
		val1 = addInRand(val1, whiteKing,   Piece.WHITE_KING.getRand1());
		val1 = addInRand(val1, whiteQueen,  Piece.WHITE_QUEEN.getRand1());
		val1 = addInRand(val1, whiteBishop, Piece.WHITE_BISHOP.getRand1());
		val1 = addInRand(val1, whiteKnight, Piece.WHITE_KNIGHT.getRand1());
		val1 = addInRand(val1, whiteRook,   Piece.WHITE_ROOK.getRand1());
		val1 = addInRand(val1, whitePawn,   Piece.WHITE_PAWN.getRand1());
		
		val2 = addInRand(val2, whiteKing,   Piece.WHITE_KING.getRand2());
		val2 = addInRand(val2, whiteQueen,  Piece.WHITE_QUEEN.getRand2());
		val2 = addInRand(val2, whiteBishop, Piece.WHITE_BISHOP.getRand2());
		val2 = addInRand(val2, whiteKnight, Piece.WHITE_KNIGHT.getRand2());
		val2 = addInRand(val2, whiteRook,   Piece.WHITE_ROOK.getRand2());
		val2 = addInRand(val2, whitePawn,   Piece.WHITE_PAWN.getRand2());
		
		val1 = addInRand(val1, blackKing,   Piece.BLACK_KING.getRand1());
		val1 = addInRand(val1, blackQueen,  Piece.BLACK_QUEEN.getRand1());
		val1 = addInRand(val1, blackBishop, Piece.BLACK_BISHOP.getRand1());
		val1 = addInRand(val1, blackKnight, Piece.BLACK_KNIGHT.getRand1());
		val1 = addInRand(val1, blackRook,   Piece.BLACK_ROOK.getRand1());
		val1 = addInRand(val1, blackPawn,   Piece.BLACK_PAWN.getRand1());
		
		val2 = addInRand(val2, blackKing,   Piece.BLACK_KING.getRand2());
		val2 = addInRand(val2, blackQueen,  Piece.BLACK_QUEEN.getRand2());
		val2 = addInRand(val2, blackBishop, Piece.BLACK_BISHOP.getRand2());
		val2 = addInRand(val2, blackKnight, Piece.BLACK_KNIGHT.getRand2());
		val2 = addInRand(val2, blackRook,   Piece.BLACK_ROOK.getRand2());
		val2 = addInRand(val2, blackPawn,   Piece.BLACK_PAWN.getRand2());
		
		return new GameStateVal(color, val1, val2);
	}
	
	public int getTotalGameValue() {
		return (
				addInVal(whiteKing,   Piece.WHITE_KING.getValue()) +
				addInVal(whiteQueen,  Piece.WHITE_QUEEN.getValue()) +
				addInVal(whiteBishop, Piece.WHITE_BISHOP.getValue()) +
				addInVal(whiteKnight, Piece.WHITE_KNIGHT.getValue()) +
				addInVal(whiteRook,   Piece.WHITE_ROOK.getValue()) +
				addInVal(whitePawn,   Piece.WHITE_PAWN.getValue())
		) - (
				addInVal(blackKing,   Piece.BLACK_KING.getValue()) +
				addInVal(blackQueen,  Piece.BLACK_QUEEN.getValue()) +
				addInVal(blackBishop, Piece.BLACK_BISHOP.getValue()) +
				addInVal(blackKnight, Piece.BLACK_KNIGHT.getValue()) +
				addInVal(blackRook,   Piece.BLACK_ROOK.getValue()) +
				addInVal(blackPawn,   Piece.BLACK_PAWN.getValue()));
	}
}