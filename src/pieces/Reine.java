package pieces;

import java.util.ArrayList;

import environment.Case;

public class Reine extends Piece {

	public Reine(int id) {
		super(id);
		this.setName("reine");
		this.setDiagonal(true);
		this.setStraightLine(true);
	}

	@Override
	public ArrayList<Case> getPossibleCases(Case pCase) {
		ArrayList<Case> possibleCases = new ArrayList<>();
		int x, y;
		Case caseP;
		int[] var = { 1, -1, 1, -1, 1, 0, 0, -1 };
		int[] var2 = { 1, -1, -1, 1, 0, 1, -1, 0 };
		for (int i = 1; i <= 8; i++) {
			for (int j = 0; j < 8; j++) {
				x = pCase.getX() + i * var[j];
				y = pCase.getY() + i * var2[j];
				if (x > 0 && y > 0 && x <= 8 && y <= 8) {
					caseP = new Case(x, y, null);
					possibleCases.add(caseP);
				}
			}
		}
		return possibleCases;
	}

}
