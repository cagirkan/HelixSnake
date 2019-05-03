
public class Cell {

	 final static int CELL_TYPE_EMPTY = 0, CELL_TYPE_FOOD = 10, CELL_TYPE_SNAKE_NODE = 20, CELL_TYPE_SNAKE_WALL = 30;
	    final int row, col;
	    char data;
	    int type;

	    public Cell(int row, int col, int type) {
	        this.row = row;
	        this.col = col;
	        this.type = type;
	        if(type == CELL_TYPE_EMPTY) this.data = ' ';
	        else if (type == CELL_TYPE_FOOD) this.data = '.';
	        else if(type == CELL_TYPE_SNAKE_NODE) this.data = '-';
	        else this.data = '#';
	    }
}
