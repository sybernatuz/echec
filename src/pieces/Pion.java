package pieces;

import java.util.ArrayList;

import environment.Case;

public class Pion extends Piece {

	private boolean moved = false;

	public Pion(int id) {
		super(id);
		this.setName("Pion");
	}

	@Override
	public ArrayList<Case> getPossibleCases(Case pCase) {
		ArrayList<Case> possibleCases = new ArrayList<>();
		int x, y, id;
		Case caseP;
		int[] var = { 0, 1, -1 };
		id = (int) pCase.getPiece().getId() / 100;
		for (int i = 0; i < 3; i++) {
			x = pCase.getX() + var[i] * ((id == 1) ? 1 : -1);
			y = pCase.getY() + ((id == 1) ? 1 : -1);
			if (x > 0 && y > 0 && x <= 8 && y <= 8) {
				caseP = new Case(x, y, null);
				possibleCases.add(caseP);
			}
		}
		if (!moved) {
			possibleCases.add(new Case(pCase.getX(), pCase.getY() + ((id == 1) ? +2 : -2), null));
		}
		setMoved(true);
		return possibleCases;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

}
