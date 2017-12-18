import java.util.ArrayList;

class Piece implements Cloneable{
    private int color;
    private char image;
    private ArrayList<Cell> possiblemoves = new ArrayList<Cell>();

    Piece(int color, char image) {
        this.color = color;
        this.image = image;
    }
    
    int getColor() {
        return color;
    }

    Piece getcopy() throws CloneNotSupportedException {
        return (Piece) this.clone();
    }

    char getImage() {
        return this.image;
    }

    ArrayList<Cell> move(Cell board[][], int x, int y) {
        possiblemoves.clear();
        int posx[] = {x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};
        int posy[] = {y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1};
        int defx[] = {x, x, x + 1, x + 1, x, x, x - 1, x - 1};
        int defy[] = {y - 1, y + 1, y, y, y - 1, y + 1, y, y};
        for (int i = 0; i < 8; i++)
            if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8))
                if (board[posx[i]][posy[i]].getPiece() == null || (board[posx[i]][posy[i]].getPiece().getColor() != this.getColor())) {
                    if (board[defx[i]][defy[i]].getPiece() == null)
                        possiblemoves.add(board[posx[i]][posy[i]]);
                }
        return possiblemoves;
    }
}
