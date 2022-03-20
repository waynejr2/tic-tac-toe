package game;

public enum Mark {
	NONE(" ", 0), X("X", -1), O("O", 1);

	private String s;
	private int value;

	Mark( String s, int value ) {
		this.s = s;
		this.value = value;
	}

	public String toString() {
		return this.s;
	}
}
