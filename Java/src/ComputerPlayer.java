import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComputerPlayer extends Player {
	private static class ComputerMove {
		private final int gameVal;
		private Move move;
		
		public ComputerMove(Move move, int gameVal) {
			this.move = move;
			this.gameVal = gameVal;
		}
		
		public int getValue() {
			return gameVal;
		}
		
		public Move getMove() {
			return move;
		}
		
		public void prepend(Move move) {
			this.move = move;
		}
	}
	
	private static final HashMap<GameState.GameStateVal, Move> bestMoves = new HashMap<GameState.GameStateVal, Move>();
	
	public ComputerPlayer(Color color) {
		super(color);
	}

	@Override
	public Move getMove(GameState state) {
		return generateMove(state, 2 * 3).getMove();
	}
	
	private static ComputerMove generateMove(final GameState state, int depth) {
		Move prevBest = null;
		if ((prevBest = bestMoves.get(state.getGameStateVal())) != null) {
			GameState futureState = state.clone();
			futureState.applyMove(prevBest);
			return new ComputerMove(prevBest, futureState.getTotalGameValue());
		}
		
		ComputerMove bestMove = null;
		
		while (depth-- > 0) {
			List<Move> legal = findAllLegalMoves(state);
			final int compFact = state.getColor() == Color.WHITE ? 1 : -1;
			
			for (Move toConsider : legal) {
				GameState futureState = state.clone();
				futureState.applyMove(toConsider);
				
				ComputerMove potentialBest;
				if (depth > 0) {
					potentialBest = generateMove(futureState, depth);
					potentialBest.prepend(toConsider);
				} else {
					int gameVal = futureState.getTotalGameValue();
					potentialBest = new ComputerMove(toConsider, gameVal);
				}

				if (bestMove == null || bestMove.getValue() * compFact < potentialBest.getValue() * compFact) {
					bestMove = potentialBest;
				}
			}
		}
		
		bestMoves.put(state.getGameStateVal(), bestMove.getMove());
		return bestMove;
	}
	
	private static List<Move> findAllLegalMoves(GameState state) {
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(findPawnMoves(state));
		moves.addAll(findRookMoves(state));
		moves.addAll(findKnightMoves(state));
		moves.addAll(findBishopMoves(state));
		moves.addAll(findQueenMoves(state));
		moves.addAll(findKingMoves(state));
		return moves;
	}
	
	private static List<Move> convertToMoves(long board, int shiftAmount) {
		List<Move> moves = new ArrayList<Move>();
		
		int i = 0;
		while (board != 0) {
			if ((board & 1) != 0) {
				new Move(new Square(i - shiftAmount), new Square(i));
			}
			
			board >>= 1;
			++i;
		}
		
		return moves;
	}
	
	private static List<Move> findPawnMoves(GameState state) {
		List<Move> moves = new ArrayList<Move>();
		
		long pieces = state.getColor() == Color.WHITE ? state.whitePawn.getBoard() : state.blackPawn.getBoard();
		long valid;
		
		if (state.getColor() == Color.WHITE) {
			valid = ((pieces & Bitboard.row2) << (2 * 8)) & ~(state.white.getBoard() | state.black.getBoard());
			moves.addAll(convertToMoves(valid, 2 * 8));
			
			valid = (pieces << 8) & ~(state.white.getBoard() | state.black.getBoard());
			moves.addAll(convertToMoves(valid, 8));
			
			valid = ((pieces & ~Bitboard.colA) << 9) & state.black.getBoard();
			moves.addAll(convertToMoves(valid, 9));
			
			valid = ((pieces & ~Bitboard.colH) << 7) & state.black.getBoard();
			moves.addAll(convertToMoves(valid, 7));
		} else {
			valid = ((pieces & Bitboard.row7) >> (2 * 8)) & ~(state.white.getBoard() | state.black.getBoard());
			moves.addAll(convertToMoves(valid, -2 * 8));
			
			valid = (pieces >> 8) & ~(state.white.getBoard() | state.black.getBoard());
			moves.addAll(convertToMoves(valid, -8));
			
			valid = ((pieces & ~Bitboard.colA) >> 7) & state.white.getBoard();
			moves.addAll(convertToMoves(valid, -7));
			
			valid = ((pieces & ~Bitboard.colH) >> 9) & state.white.getBoard();
			moves.addAll(convertToMoves(valid, -9));
		}
		
		return moves;
	}
	
	private static List<Move> findRookMoves(GameState state) {
		Bitboard pieces  = state.getColor() == Color.WHITE ? state.whiteRook : state.blackRook;
		return new ArrayList<Move>();
	}
	
	private static List<Move> findKnightMoves(GameState state) {
		Bitboard pieces  = state.getColor() == Color.WHITE ? state.whiteKnight : state.blackKnight;
		return new ArrayList<Move>();
	}
	
	private static List<Move> findBishopMoves(GameState state) {
		Bitboard pieces  = state.getColor() == Color.WHITE ? state.whiteBishop : state.blackBishop;
		return new ArrayList<Move>();
	}
	
	private static List<Move> findQueenMoves(GameState state) {
		Bitboard pieces  = state.getColor() == Color.WHITE ? state.whiteQueen : state.blackQueen;
		return new ArrayList<Move>();
	}
	
	private static List<Move> findKingMoves(GameState state) {
		Bitboard pieces  = state.getColor() == Color.WHITE ? state.whiteKing : state.blackKing;
		return new ArrayList<Move>();
	}
}