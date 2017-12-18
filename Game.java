import java.util.ArrayList;
import java.util.Scanner;

class Game {
	private State currentState;
	private Scanner s = new Scanner(System.in);
	private AI lumos;
	private Move Mover;

	Game(boolean Go_First) {
		this.currentState = new State();
		this.Mover = new Move(currentState);
		currentState.PrintBoard();
		while (currentState.blackpoint < 6 && currentState.whitepoint < 6) {
			if (Go_First) {
				PlayerTurn();
				if (currentState.whitepoint >= 6)
					break;
				else
					ComputerTurn();
			} else {
				ComputerTurn();
				if (currentState.blackpoint >= 6)
					break;
				else
					PlayerTurn();
			}
		}
		if (currentState.blackpoint >= 6)
			System.out.println("Computer win");
		else
			System.out.println("Human win");
	}

	private void PlayerTurn() {
		String input = GetInput();
		int m[] = InputToMoveCommand(input);
		Mover.movePiece(m[0], m[1], m[2], m[3]);
		char t1 = (char) (m[1] + 97);
		char t2 = (char) (m[3] + 97);
		currentState.PrintBoard();
		System.out.println("Player: " +  t1 + m[0] + " to " + t2 + m[2]);
	}

	private void ComputerTurn() {
		lumos = new AI(currentState);
		System.out.println("AI thinking ...");
		//long startTime = System.currentTimeMillis();
		int m[] = lumos.CalculateNextMove();
		//long endTime   = System.currentTimeMillis();
		//long totalTime = endTime - startTime;
		//System.out.println("Runtime: " + totalTime + " ms");
		Mover.movePiece(m[0], m[1], m[2], m[3]);
		char t1 = (char) (m[1] + 97);
		char t2 = (char) (m[3] + 97);
		currentState.PrintBoard();
		System.out.println("Computer: " +  t1 + m[0] + " to " + t2 + m[2]);
	}

	private int[] InputToMoveCommand(String cmd) {
		int m[] = new int[4];
		m[0] = (int) cmd.charAt(1) - 48; // x1
		m[1] = (int) cmd.charAt(0) - 97; // y1
		m[2] = (int) cmd.charAt(3) - 48; // x2
		m[3] = (int) cmd.charAt(2) - 97; // y2
		return m;
	}

	private boolean isValid(String s) {
		if (s.length() != 4)
			return false;
		else {
			if ((s.charAt(0) < 'a' || s.charAt(0) > 'h') || (s.charAt(1) < '0' || s.charAt(1) > '7')
					|| (s.charAt(2) < 'a' || s.charAt(2) > 'h') || (s.charAt(3) < '0' || s.charAt(3) > '7')) {
				return false;
			} else {
				int m[] = InputToMoveCommand(s);
				if (currentState.board[m[0]][m[1]].getPiece() == null)
					return false;
				else if (currentState.board[m[0]][m[1]].getPiece().getColor() == 1)
					return false;
				else {
					ArrayList<Cell> temp = currentState.board[m[0]][m[1]].getPiece().move(currentState.board, m[0],
							m[1]);
					for (Cell t : temp) {
						if (t.getX() == m[2] && t.getY() == m[3])
							return true;
					}
					return false;
				}
			}
		}
	}

	private String GetInput() {
		System.out.print("It's your move : ");
		String input = s.nextLine();
		if(input.equals("exit")) {
			System.out.println("Exit");
			System.exit(1);
		}
		while (!isValid(input)) {
			System.out.print("Couldn't parse input, enter a valid move: ");
			input = s.nextLine();
		}
		return input;
	}
}
