public enum Color {
	WHITE,
	BLACK;
	
	public static Color toggleColor(Color initial) {
		return initial == WHITE ? BLACK : WHITE;
	}
}