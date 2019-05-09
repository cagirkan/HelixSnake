import java.util.Random;

public class Board {

    final int ROW_COUNT, COL_COUNT;
    Cell[][] cells;

    public Board(int rowCount, int columnCount) {
        ROW_COUNT = rowCount;
        COL_COUNT = columnCount;

        cells = new Cell[ROW_COUNT][COL_COUNT];
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COL_COUNT; column++) {
            	if (row == 0 || row == ROW_COUNT-1 || column == 0 || column == COL_COUNT-1)
            		cells[row][column] = new Cell(row,column,30);
            	else
            		cells[row][column] = new Cell(row, column,0);
            }
        }
    }

    public void generateFood() {
    	Random rnd = new Random();
    	int row, col; 
    	while (true) {
    		row = (int) (Math.random() * (ROW_COUNT-1))+1;
            col = (int) (Math.random() * (COL_COUNT-1))+1;
            if(cells[row][col].type == 0) {
            	cells[row][col].changeType(rnd.nextInt(3)+11);
            	break;
            }
		}
    }
}
