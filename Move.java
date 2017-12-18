
class Move {
	private State state;

	Move(State state) {
		this.state = state;
	}
	
	void movePiece(int x1, int y1, int x2, int y2) {
		int turn = state.board[x1][y1].getPiece().getColor();

		// if eat piece ?
		if (state.board[x2][y2].getPiece() == null) {
			state.board[x2][y2].setPiece(state.board[x1][y1].getPiece());
			state.board[x1][y1].removePiece();
		} else {
			state.board[x2][y2].removePiece();
			state.board[x2][y2].setPiece(state.board[x1][y1].getPiece());
			state.board[x1][y1].removePiece();
			if (turn == 1)
				state.blackpoint++;
			else
				state.whitepoint++;
		}
		// if eat point ?
		if (state.board[x2][y2].getPoint() != 0
				&& state.board[x2][y2].getSide() != state.board[x2][y2].getPiece().getColor()) {
			if (state.board[x2][y2].getPiece().getColor() == 0) {
				if (state.board[x2][y2].getPoint() == 1) {
					state.whitepoint++;
					if (state.board[7][0].getPiece() == null) {
						state.board[7][0].setPiece(state.board[x2][y2].getPiece());
					}
					else {
						if (state.board[7][0].getPiece().getColor() == 1)
							state.whitepoint++;
						state.board[7][0].removePiece();
						state.board[7][0].setPiece(state.board[x2][y2].getPiece());
					}
					state.board[x2][y2].removePiece();
				} else {
					state.whitepoint += 2;
					if (state.board[7][7].getPiece() == null) {
						state.board[7][7].setPiece(state.board[x2][y2].getPiece());
					}
					else {
						if (state.board[7][7].getPiece().getColor() == 1)
							state.whitepoint++;
						state.board[7][7].removePiece();
						state.board[7][7].setPiece(state.board[x2][y2].getPiece());
					}
					state.board[x2][y2].removePiece();
				}
			} else if (state.board[x2][y2].getPiece().getColor() == 1) {
				if (state.board[x2][y2].getPoint() == 1) {
					state.blackpoint++;
					if (state.board[0][7].getPiece() == null) {
						state.board[0][7].setPiece(state.board[x2][y2].getPiece());
					}
					else {
						if (state.board[0][7].getPiece().getColor() == 0)
							state.blackpoint++;
						state.board[0][7].removePiece();
						state.board[0][7].setPiece(state.board[x2][y2].getPiece());
					}
					state.board[x2][y2].removePiece();
				} else {
					state.blackpoint += 2;
					if (state.board[0][0].getPiece() == null) {
						state.board[0][0].setPiece(state.board[x2][y2].getPiece());
					}
					else {
						if (state.board[0][0].getPiece().getColor() == 0)
							state.blackpoint++;
						state.board[0][0].removePiece();
						state.board[0][0].setPiece(state.board[x2][y2].getPiece());
					}
					state.board[x2][y2].removePiece();
				}
			}
		}
	}
	
	

	
}
