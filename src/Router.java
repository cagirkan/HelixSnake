import enigma.console.Console;
import enigma.console.TextAttributes;
import java.awt.Color;


public class Router {

	public static final int DIRECTION_NONE = 0, DIRECTION_RIGHT = 1, DIRECTION_LEFT = -1, DIRECTION_UP = 2, DIRECTION_DOWN = -2;
    private Snake snake;
    private Board board;
    private Score score;
    private int direction;
    public boolean gameOver;
    public TextAttributes ta;

    public Router(Snake snake, Board board, Score score) {
        this.snake = snake;
        this.board = board;
        this.score = score;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void print(Console cn) {
    	int type;
		for (int i = 0; i < board.ROW_COUNT; i++) {
			for (int j = 0; j < board.COL_COUNT; j++) {
				type = snake.snakePartList.searchSnake(i, j, board);
				if(type != -1) {
					board.cells[i][j].changeType(type);
				}
				ta = printWithColor(type);
				cn.setTextAttributes(ta);
				System.out.print(board.cells[i][j].data);
			}
			System.out.println();
		}
	}
    
    private TextAttributes printWithColor(int type) {
    	TextAttributes ta;
    	if(type == Cell.CELL_TYPE_EMPTY) {
    		ta = new TextAttributes(Color.white);
    		return ta;
    	}
    	else if(type == Cell.CELL_TYPE_FOOD_A || type == Cell.CELL_TYPE_FOOD_C || type == Cell.CELL_TYPE_FOOD_G || type == Cell.CELL_TYPE_FOOD_T) {
    		ta = new TextAttributes(Color.ORANGE,Color.DARK_GRAY);
    		return ta;
    	}
    	else if(type == Cell.CELL_TYPE_SNAKE_NODE_A || type == Cell.CELL_TYPE_SNAKE_NODE_C || type == Cell.CELL_TYPE_SNAKE_NODE_G || type == Cell.CELL_TYPE_SNAKE_NODE_T) {
    		ta = new TextAttributes(Color.CYAN, Color.DARK_GRAY);
    		return ta;
    	}
    	else{
    		ta = new TextAttributes(Color.PINK);
    		return ta;
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
                    if (nextCell.type == Cell.CELL_TYPE_FOOD_A ||
                    	nextCell.type == Cell.CELL_TYPE_FOOD_C ||
                    	nextCell.type == Cell.CELL_TYPE_FOOD_T ||
                    	nextCell.type == Cell.CELL_TYPE_FOOD_G ) {
                    	score.setScore(snake.move(nextCell,true,score)); 
                        board.generateFood();
                    }
                    //else if(nextCell.type == Cell.CELL_TYPE_SNAKE_WALL)
                    	//snake.move(nextCell, "wall", score);
                    else
                    	snake.move(nextCell,false,score);
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition) {
        int row = currentPosition.row;
        int col = currentPosition.col;

        if (direction == DIRECTION_RIGHT) {
        	if (col == 58) 
				col = 0;
            col++;
        } else if (direction == DIRECTION_LEFT) {
        	if(col == 1)
        		col = 59; 
            col--;
        } else if (direction == DIRECTION_UP) {
        	if(row == 1)
        		row = 24;
            row--;
        } else if (direction == DIRECTION_DOWN) {
        	if(row == 23)
        		row = 0;
            row++;
        }

        Cell nextCell = board.cells[row][col];

        return nextCell;
    }

	public int getDirection() {
		return direction;
	}
    
}
