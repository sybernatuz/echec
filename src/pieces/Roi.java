package pieces;

import java.util.ArrayList;

import environment.Case;

public class Roi extends Piece {

	public Roi(int id) {
		super(id);
		this.setName("Roi");
	}

	@Override
	public ArrayList<Case> getPossibleCases(Case pCase) {
		ArrayList<Case> possibleCases = new ArrayList<>();
		int x, y;
		Case caseP;
		int[] var = { 1, -1, -1, 1, 1, 0, -1, 0 };
		int[] var2 = { 1, -1, 1, -1, 0, 1, 0, -1 };
		for (int i = 0; i < 8; i++) {
			x = pCase.getX() + var[i];
			y = pCase.getY() + var2[i];
			if (x > 0 && y > 0 && x <= 8 && y <= 8) {
				caseP = new Case(x, y, null);
				possibleCases.add(caseP);
			}
		}
		return possibleCases;
	}
}
