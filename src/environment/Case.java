package environment;

import pieces.Piece;

public class Case implements Cloneable {

	private int x;
	private int y;
	private Piece piece;
	private String name;

	public Case(int x, int y, Piece piece, String name) {
		this.x = x;
		this.y = y;
		this.piece = piece;
		this.name = name;
	}

	public Case(int x, int y, Piece piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}
	


	public Case(Case case1) {
		this.setX(case1.getX());
		this.setY(case1.getY());
	}

	public Case() {
	}

	public String toString() {
		return "(X=" + this.x + " - Y=" + this.y + ")";
	}

	protected Case clone() {
		return new Case(this);
	}

	@Override
	public int hashCode() {
		return x * 100 + y;
	}

	@Override
	public boolean equals(Object obj) {
		Case other = (Case) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * Returns value of x
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets new value of x
	 * 
	 * @param
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns value of y
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets new value of y
	 * 
	 * @param
	 */
	public void setY(int y) {
		this.y = y;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
