
public class Router {

	public static final int DIRECTION_NONE = 0, DIRECTION_RIGHT = 1, DIRECTION_LEFT = -1, DIRECTION_UP = 2, DIRECTION_DOWN = -2;
    private Snake snake;
    private Board board;
    private int direction;
    private boolean gameOver;

    public Router(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void print() {
    	int type;
		for (int i = 0; i < board.ROW_COUNT; i++) {
			for (int j = 0; j < board.COL_COUNT; j++) {
				type = snake.snakePartList.searchSnake(i, j);
				if(type != -1) {
					board.cells[i][j].changeType(21);
				}
				System.out.print(board.cells[i][j].data);
			}
			System.out.println();
		}
	}
    
    public void update() {
        if (!gameOver) {
            if (direction != DIRECTION_NONE) {
                Cell nextCell = getNextCell(snake.head);

                if (snake.checkCrash(nextCell)) {
                    setDirection(DIRECTION_NONE);
                    gameOver = true;
                } else {
                    snake.move(nextCell);
                    if (nextCell.type == Cell.CELL_TYPE_FOOD_A ||
                    	nextCell.type == Cell.CELL_TYPE_FOOD_C ||
                    	nextCell.type == Cell.CELL_TYPE_FOOD_T ||
                    	nextCell.type == Cell.CELL_TYPE_FOOD_G ) {
                        snake.grow();
                        board.generateFood();
                    }
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition) {
        int row = currentPosition.row;
        int col = currentPosition.col;

        if (direction == DIRECTION_RIGHT) {
            col++;
        } else if (direction == DIRECTION_LEFT) {
            col--;
        } else if (direction == DIRECTION_UP) {
            row--;
        } else if (direction == DIRECTION_DOWN) {
            row++;
        }

        Cell nextCell = board.cells[row][col];

        return nextCell;
    }

	public int getDirection() {
		return direction;
	}
    
}
