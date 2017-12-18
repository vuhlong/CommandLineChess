import java.util.ArrayList;

class State implements Cloneable {
	Cell[][] board = new Cell[8][8];
	int whitepoint;
	int blackpoint;
	private static int[][] bpoint = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 10, 0, 10, 0, 10, 0, 10, 0 },
			{ 5, 10, 5, 10, 5, 10, 5, 10 }, { 10, 20, 15, 20, 15, 20, 10, 5 }, { 20, 15, 20, 15, 20, 15, 20, 15 },
			{ 15, 5, 50, 30, 50, 30, 10, 20 }, { 20, 50, 30, 10, 5, 50, 30, 15 }, { 15, 0, 0, 0, 0, 0, 0, 20 }, };

	private static int[][] wpoint = { { -20, 0, 0, 0, 0, 0, 0, -15 }, { -15, -30, -50, -5, -10, -30, -50, -20 },
			{ -20, -10, -30, -50, -30, -50, -5, -15 }, { -15, -20, -15, -20, -15, -20, -15, -20 },
			{ -5, -10, -20, -15, -20, -15, -20, -15, -20 }, { -10, -5, -10, -5, -10, -5, -10, -5 },
			{ 0, -10, 0, -10, 0, -10, 0, -10 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, };

	State() {
		this.whitepoint = 0;
		this.blackpoint = 0;
		setupboard();
	}

	private State(State S) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				try {
					this.board[i][j] = new Cell(S.board[i][j]);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		this.whitepoint = S.whitepoint;
		this.blackpoint = S.blackpoint;
	}

	private void setupboard() {
		//unicode
		/*
		Piece w1 = new Piece(0, '♘');
		Piece w2 = new Piece(0, '♘');
		Piece w3 = new Piece(0, '♘');
		Piece w4 = new Piece(0, '♘');
		Piece w5 = new Piece(0, '♘');
		Piece w6 = new Piece(0, '♘');
		Piece b1 = new Piece(1, '♞');
		Piece b2 = new Piece(1, '♞');
		Piece b3 = new Piece(1, '♞');
		Piece b4 = new Piece(1, '♞');
		Piece b5 = new Piece(1, '♞');
		Piece b6 = new Piece(1, '♞');
		*/
		//windows no unicode
		Piece w1 = new Piece(0, 'X');
		Piece w2 = new Piece(0, 'X');
		Piece w3 = new Piece(0, 'X');
		Piece w4 = new Piece(0, 'X');
		Piece w5 = new Piece(0, 'X');
		Piece w6 = new Piece(0, 'X');
		Piece b1 = new Piece(1, 'O');
		Piece b2 = new Piece(1, 'O');
		Piece b3 = new Piece(1, 'O');
		Piece b4 = new Piece(1, 'O');
		Piece b5 = new Piece(1, 'O');
		Piece b6 = new Piece(1, 'O');

		Piece p;
		Cell c;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				p = null;
				if (i == 7 && j == 0)
					p = w1;
				if (i == 7 && j == 1)
					p = w2;
				if (i == 7 && j == 2)
					p = w3;
				if (i == 7 && j == 5)
					p = w4;
				if (i == 7 && j == 6)
					p = w5;
				if (i == 7 && j == 7)
					p = w6;
				if (i == 0 && j == 0)
					p = b1;
				if (i == 0 && j == 1)
					p = b2;
				if (i == 0 && j == 2)
					p = b3;
				if (i == 0 && j == 5)
					p = b4;
				if (i == 0 && j == 6)
					p = b5;
				if (i == 0 && j == 7)
					p = b6;
				c = new Cell(i, j, p);
				if (i == 0 && j == 3) {
					c.setPoint(1);
					c.setSide(1);
				}
				if (i == 0 && j == 4) {
					c.setPoint(2);
					c.setSide(1);
				}
				if (i == 7 && j == 3) {
					c.setPoint(2);
					c.setSide(0);
				}
				if (i == 7 && j == 4) {
					c.setPoint(1);
					c.setSide(0);
				}
				board[i][j] = c;
			}
		}
	}

	ArrayList<State> ChildsOf(int turn) {
		Move Mover;
		ArrayList<State> Childs = new ArrayList<>();
		State temp = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.board[i][j].getPiece() != null) {
					if (this.board[i][j].getPiece().getColor() == turn) {
						ArrayList<Cell> Moves = this.board[i][j].getPiece().move(this.board, i, j);
						for (Cell m : Moves) {
							temp = new State(this);
							Mover = new Move(temp);
							Mover.movePiece(i, j, m.getX(), m.getY());
							Childs.add(temp);
						}
					}
				}
			}
		}
		return Childs;
	}

	int[] GenerateMove(State t) {
		int m[] = new int[4];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.board[i][j].getPiece() != null) {
					if ((this.board[i][j].getPiece().getColor() == 1) && t.board[i][j].getPiece() == null) {
						m[0] = i;
						m[1] = j;
					} else if ((this.board[i][j].getPiece().getColor() == 0) && t.board[i][j].getPiece() != null) {
						if ((t.board[i][j].getPiece().getColor() == 1)) {
							m[2] = i;
							m[3] = j;
						}
					}
				} else {
					if (t.board[i][j].getPiece() != null) {
						if ((t.board[i][j].getPiece().getColor() == 1)) {
							m[2] = i;
							m[3] = j;
						}
					}
				}
			}
		}

		boolean eat_point = true;
		int x = m[0], y = m[1];
		int posx[] = { x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2 };
		int posy[] = { y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1 };
		for (int i = 0; i < 8; i++) {
			if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8)) {
				if (m[2] == posx[i] && m[3] == posy[i]) {
					eat_point = false;
					break;
				}
			}
		}

		if (eat_point) {
			if (m[3] == 0) {
				m[2] = 7;
				m[3] = 3;
			} else {
				m[2] = 7;
				m[3] = 4;
			}
		}

		return m;
	}

	void PrintBoard() {
		System.out.println("   a b c d e f g h");
		System.out.print("   _______________");
		System.out.println(" |Computer: " + blackpoint + "-" + whitepoint + " :Player|");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 8; j++) {
				if (j > 0)
					System.out.print(" ");
				if (board[i][j].getPiece() != null)
					System.out.print(board[i][j].getPiece().getImage());
				else {
					if (board[i][j].getPoint() != 0)
						System.out.print(board[i][j].getPoint());
					else
						System.out.print("-");
				}
			}
			System.out.println();
		}
	}

	private int material(Cell c) {
		if (c.getPiece().getColor() == 1)
			return 800;
		else
			return -800;
	}

	private int mobility(Cell c) {
		ArrayList<Cell> moves = c.getPiece().move(this.board, c.getX(), c.getY());
		int result = moves.size();
		if (c.getPiece().getColor() == 1)
			return result * 50;
		else
			return -result * 50;
	}

	private int checkdef(Cell c) {
		int result = 0;
		int x = c.getX(), y = c.getY();
		int px[] = { x, x - 1, x, x + 1 };
		int py[] = { y - 1, y, y + 1, y };
		int ax[] = { x + 1, x - 1, x - 1, x + 1 };
		int ay[] = { y - 1, y - 1, y + 1, y + 1 };
		for (int i = 0; i < 4; i++) {
			if (px[i] >= 0 && px[i] < 8 && py[i] >= 0 && py[i] < 8) {
				if (this.board[px[i]][py[i]].getPiece() != null) {
					int a[];
					if (i == 0)
						a = new int[] { 2, 3 };
					else if (i == 1)
						a = new int[] { 3, 0 };
					else if (i == 2)
						a = new int[] { 1, 0 };
					else
						a = new int[] { 2, 1 };

					for (int k : a) {
						if (ax[k] >= 0 && ax[k] < 8 && ay[k] >= 0 && ay[k] < 8) {
							if (this.board[ax[k]][ay[k]].getPiece() != null && (this.board[ax[k]][ay[k]].getPiece()
									.getColor() == this.board[x][y].getPiece().getColor())) {
								if (this.board[x][y].getPiece().getColor() == 1)
									result += 50;
								else
									result -= 50;
							}
							if (ay[k] == 3 || ay[k] == 4) {
								if (ax[k] == 0 && (this.board[x][y].getPiece().getColor() == 1)) {
									if (ay[k] == 3)
										result += 100;
									else
										result += 2 * 100;
								}
								if (ax[k] == 7 && (this.board[x][y].getPiece().getColor() == 0)) {
									if (ay[k] == 3)
										result -= 2 * 100;
									else
										result -= 100;
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	private int posisionpoint(Cell c) {
		ArrayList<Cell> moves = c.getPiece().move(this.board, c.getX(), c.getY());
		if(c.getPiece().getColor() == 1) {
			for(Cell m : moves) {
				if(bpoint[c.getX()][c.getY()] != 50 || bpoint[c.getX()][c.getY()] != 30) {
					if((bpoint[m.getX()][m.getY()] > bpoint[c.getX()][c.getY()]) || m.getPoint() != 0) return bpoint[c.getX()][c.getY()];
				}
			}
		}
		else {
			for(Cell m : moves) {
				if(wpoint[c.getX()][c.getY()] != 50 || wpoint[c.getX()][c.getY()] != 30) {
					if((wpoint[m.getX()][m.getY()] > wpoint[c.getX()][c.getY()]) || m.getPoint() != 0) return wpoint[c.getX()][c.getY()];
				}
			}
		}
		return 0;
	}

	int evaluation(int turn) {
		if (blackpoint >= 6)
			return Integer.MAX_VALUE;
		if (whitepoint >= 6)
			return -Integer.MAX_VALUE;
		int result = 1000 * (blackpoint - whitepoint);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.board[i][j].getPiece() != null) {
					result = result + material(this.board[i][j]) + mobility(this.board[i][j])
							+ checkdef(this.board[i][j]) + posisionpoint(this.board[i][j]);
				}
			}
		}
		return result;
	}

}