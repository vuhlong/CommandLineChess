class Cell implements Cloneable {
	private int x, y;
	private Piece piece;
	private int point = 0;
	private int side;

	Cell(int x, int y, Piece piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}

	Cell(Cell c) throws CloneNotSupportedException {
		this.x = c.x;
		this.y = c.y;
		this.side = c.side;
		this.point = c.point;
		if (c.getPiece() != null) {
			setPiece(c.getPiece().getcopy());
		} else
			piece = null;
	}
	
	Cell getcopy() throws CloneNotSupportedException {
        return (Cell) this.clone();
    }

	void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	Piece getPiece() {
		return this.piece;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	int getPoint() {
		return point;
	}

	int getSide() {
		return side;
	}

	void setSide(int side) {
		this.side = side;
	}

	void removePiece() {
		this.piece = null;
	}

	void setPoint(int point) {
		this.point = point;
	}

}
