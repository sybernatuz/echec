package environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import pieces.Cavalier;
import pieces.Fou;
import pieces.Piece;
import pieces.Pion;
import pieces.Reine;
import pieces.Roi;
import pieces.Tour;

public class Plateau {

	private Map<String, Case> cases;
	public static String letters = "ABCDEFGH";

	public Plateau() {
		this.cases = initializePlateau();
	}

	public Map<String, Case> initializePlateau() {
		Map<String, Case> cases = new HashMap<>();
		// Genere les cases
		for (int i = 1; i < 9; i++) {
			for (int j = 0; j < 8; j++) {
				cases.put(letters.charAt(j) + String.valueOf(i),
						new Case(j + 1, i, null, letters.charAt(j) + String.valueOf(i)));
			}
		}
		// Genere les pieces dans les cases
		int y1 = 2, y2 = 1;
		for (int i = 100; i <= 200; i += 100) {
			if (i == 200) {
				y1 = 7;
				y2 = 8;
			}
			for (int j = 0; j < 8; j++) {
				cases.get(letters.charAt(j) + String.valueOf(y1)).setPiece( new Pion(i + j));
			}
			cases.get(letters.charAt(0) + String.valueOf(y2)).setPiece(new Tour(i + 9));
			cases.get(letters.charAt(7) + String.valueOf(y2)).setPiece(new Tour(i + 10));
			cases.get(letters.charAt(1) + String.valueOf(y2)).setPiece(new Cavalier(i + 11));
			cases.get(letters.charAt(6) + String.valueOf(y2)).setPiece( new Cavalier(i + 12));
			cases.get(letters.charAt(2) + String.valueOf(y2)).setPiece(new Fou(i + 13));
			cases.get(letters.charAt(5) + String.valueOf(y2)).setPiece(new Fou(i + 14));
			cases.get(letters.charAt(3) + String.valueOf(y2)).setPiece(new Reine(i + 15));
			cases.get(letters.charAt(4) + String.valueOf(y2)).setPiece(new Roi(i + 16));
		}
		return cases;
	}


	@SuppressWarnings("resource")
	public String getPieceCase(Player p, ArrayList<Case> choises) {
		String input = "00";
		Scanner sc = new Scanner(System.in);
		boolean validate = false;
		Piece piece;
		int id;
		while (!validate) {
			input = sc.nextLine();
			input = input.toUpperCase();
			if (input.length() == 2) {
				if (choises != null && choises.contains(cases.get(input))) {
					validate = true;
				} else if (Character.isLetter(input.charAt(0)) && Character.isDigit(input.charAt(1))) {
					if (Character.getNumericValue(input.charAt(1)) <= 8 && Character.getNumericValue(input.charAt(1)) > 0 && letters.indexOf(input.charAt(0)) != -1) {
						if (cases.get(input).getPiece() != null) {
							piece = cases.get(input).getPiece();
							id = (int) piece.getId() / 100;
							if (p.getId() == id) {
								validate = true;
							}
						}
					}
				}
			}
			if (!validate) {
				System.out.println("Case incorrect");
			}
		}
		return input;
	}

	public ArrayList<Case> getAllowedCases(ArrayList<Case> pCases, Player p, Case pieceCase) {
		ArrayList<Case> targetCases = new ArrayList<>();
		Piece piece;
		Case targetCase;
		Iterator<Case> i = pCases.iterator();
		while (i.hasNext()) {
			Case c = i.next();
			targetCase = this.cases.get(letters.charAt(c.getX() - 1) + String.valueOf(c.getY()));
			if (targetCase.getPiece() != null) {
				piece = targetCase.getPiece();
				int id = (int) piece.getId() / 100;
				if (p.getId() == id || (pieceCase.getPiece().getName().equals("Pion")
						&& p.getId()==id && (pieceCase.getY() + 1 == targetCase.getY() || pieceCase.getY() - 1 == targetCase.getY()))) {
					i.remove();
				}
				targetCases.add(c);
			} else if (pieceCase.getPiece().getName().equals("Pion")) {
				if (pieceCase.getX() + 1 == targetCase.getX() || pieceCase.getX() - 1 == targetCase.getX()) {
					i.remove();
				}
			}
		}
		i.forEachRemaining(pCases::add);
		pCases = removeBlockedCases(pieceCase, targetCases, pCases);
		pCases = setNamesByPosition(pCases);
		return pCases;
	}

	public void printMap(Map<String, Case> map) {
		for (String o : map.keySet()) {
			System.out.println(o);
		}
	}

	private ArrayList<Case> setNamesByPosition(ArrayList<Case> cases) {
		for (Case c : cases) {
			c.setName(letters.charAt(c.getX() - 1) + String.valueOf(c.getY()));
		}
		return cases;
	}

	private ArrayList<Case> removeBlockedCases(Case activeCase, ArrayList<Case> targetCases, ArrayList<Case> cases) {
		ArrayList<Case> result = new ArrayList<>();
		ArrayList<Case> blockedCases = new ArrayList<>();
		Case c;
		int x, y;
		for (Case cc : targetCases) {
			if (!blockedCases.contains(cc)) {
				if (activeCase.getPiece().isStraightLine()) {
					if (cc.getX() == activeCase.getX()) {
						x = cc.getY() - activeCase.getY();
					} else {
						x = cc.getX() - activeCase.getX();
					}

					int sign = -1;
					if (x > 0) {
						sign = 1;
					}
					c = cc.clone();
					for (int i = 1; i < 8; i++) {
						if (cc.getX() == activeCase.getX()) {
							c.setY(c.getY() + 1 * sign);
						} else {
							c.setX(c.getX() + 1 * sign);
						}
						if (cases.contains(c)) {
							blockedCases.add(new Case(c.getX(), c.getY(), null));
						}
					}
				}
				if (activeCase.getPiece().isDiagonal()) {
					x = cc.getX() - activeCase.getX();
					y = cc.getY() - activeCase.getY();
					c = cc;
					int j = 0, k = 0;
					if (x > 0 && y > 0) {
						j = 1;
						k = 1;
					} else if (x > 0 && y < 0) {
						j = 1;
						k = -1;
					} else if (x < 0 && y > 0) {
						j = -1;
						k = 1;
					} else {
						j = -1;
						k = -1;
					}
					for (int i = 1; i < 8; i++) {
						c.setX(c.getX() + j);
						c.setY(c.getY() + k);
						if (cases.contains(c)) {
							blockedCases.add(new Case(c.getX(), c.getY(), null));
						}
					}
				}
			}
		}
		for (Case ccc : cases) {
			if (!blockedCases.contains(ccc)) {
				result.add(ccc);
			}
		}
		return result;
	}

	public void printPlateau() {
		String delimiter = " -------------------------";
		System.out.println(delimiter);
		String line = "";
		Case c;
		for (int j = 8; j >= 1; j--) {
			line += j;
			for (int i = 0; i < letters.length(); i++) {
				line += "|";
				c = cases.get(letters.charAt(i) + String.valueOf(j));
				if (c.getPiece() != null) {
					line += c.getPiece().getName().charAt(0) + ((c.getPiece().getId() / 100 == 1) ? "1" : "2");
				} else {
					line += "  ";
				}
			}
			line += "|";
			System.out.println(line);
			System.out.println(delimiter);
			line = "";
		}
		System.out.println("  A  B  C  D  E  F  G  H");
		System.out.println("");
	}

	public String move(String caseName, String caseTarget) {
		String deadPiece = null;
		if (cases.get(caseTarget).getPiece() != null) {
			deadPiece = cases.get(caseTarget).getPiece().getName();
		}
		cases.get(caseTarget).setPiece(cases.get(caseName).getPiece());
		cases.get(caseName).setPiece(null);
		return deadPiece;
	}


	public Map<String, Case> getCases() {
		return cases;
	}

	public void setCases(Map<String, Case> cases) {
		this.cases = cases;
	}

}
