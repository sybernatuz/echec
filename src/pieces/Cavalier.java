package pieces;

import java.util.ArrayList;

import environment.Case;

public class Cavalier extends Piece {

	public Cavalier(int id) {
		super(id);
		this.setName("Cavalier");
	}

	@Override
	public ArrayList<Case> getPossibleCases(Case pCase) {
		ArrayList<Case> possibleCases = new ArrayList<>();
		int x, y;
		Case caseP;
		int[] var = { 1, -1, -1, 1 };
		int[] var2 = { 2, -2, 2, -2 };
		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				x = pCase.getX() + var[i];
				y = pCase.getY() + var2[i];
			} else {
				x = pCase.getX() + var2[i - 4];
				y = pCase.getY() + var[i - 4];
			}
			if (x > 0 && y > 0 && x <= 8 && y <= 8) {
				caseP = new Case(x, y, null);
				possibleCases.add(caseP);
			}
		}
		return possibleCases;
	}

}
