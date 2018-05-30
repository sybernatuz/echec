package pieces;

import java.util.ArrayList;

import environment.Case;

public abstract class Piece {

	private int id;
	protected String name;
	protected boolean diagonal = false;
	protected boolean straightLine = false;

	public Piece(int id) {
		setId(id);
	}

	public abstract ArrayList<Case> getPossibleCases(Case pCase);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDiagonal() {
		return diagonal;
	}

	public void setDiagonal(boolean diagonal) {
		this.diagonal = diagonal;
	}

	public boolean isStraightLine() {
		return straightLine;
	}

	public void setStraightLine(boolean straightLine) {
		this.straightLine = straightLine;
	}

}
