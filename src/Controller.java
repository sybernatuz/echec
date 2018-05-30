import java.util.ArrayList;
import java.util.List;

import environment.Case;
import environment.Plateau;
import environment.Player;

public class Controller {

	private Plateau plateau;
	private Player player1;
	private Player player2;
	private boolean end = false;

	public Controller() {
		plateau = new Plateau();
		player1 = new Player(1);
		player2 = new Player(2);
		ArrayList<Case> choises;
		String caseName = "", caseTarget = "", deadPiece;
		while (!end) {
			for (int i = 1; i <= 2; i++) {
				choises = null;
				plateau.printPlateau();
				System.out.println("Tour du joueur " + i);
				System.out.println("----------------");
				while (choises == null || choises.isEmpty()) {
					System.out.println("Entrer la case du pion à déplacer");
					caseName = plateau.getPieceCase(((i == 1) ? getPlayer1() : getPlayer2()), null);
					System.out.println("MOUVEMENT POSSIBLE");
					System.out.println("----------------");
					choises = getChoises(caseName, ((i == 1) ? getPlayer1() : getPlayer2()));
				}
				caseTarget = plateau.getPieceCase(((i == 1) ? getPlayer1() : getPlayer2()), choises);
				deadPiece=plateau.move(caseName, caseTarget);
				if(deadPiece!=null && deadPiece.equals("Roi")) {
					end=true;
					System.out.println("Le joueur "+i+" gagne");
					break;
				}
			}
		}
	}

	public ArrayList<Case> getChoises(String caseName, Player player) {
		Case c = plateau.getCases().get(caseName);
		ArrayList<Case> cases = new ArrayList<>();
		cases = c.getPiece().getPossibleCases(c);
		cases = plateau.getAllowedCases(cases, player, c);
		printList(cases);
		return cases;
	}

	public void printList(List<Case> cases) {
		for (Case c : cases) {
			if (c.getName() != null)
				System.out.println(c.getName());
		}
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

}
