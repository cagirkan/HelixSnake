import java.util.LinkedList;

public class Snake {

	SingleLinkedList snakePartList = new SingleLinkedList();
    Cell head;
    Board board;

    public Snake(Cell initPos, Board board) {
    	int row = initPos.row;
    	int col = initPos.col;
    	this.board = board;
    	head = initPos;
    	Cell cell = new Cell(row,col-1);
    	Cell cell2 = new Cell(row,col-2);
    	
        snakePartList.insert(head);
        snakePartList.insert(cell);
        snakePartList.insert(cell2);
        
        board.cells[row][col] = head;
        board.cells[row][col-1] = cell;
        board.cells[row][col-2] = cell2;
    }

    public int grow(Cell nextCell, Score s) {
    	Cell next = nextCell;
    	if(next.type == Cell.CELL_TYPE_FOOD_A)
    		next.changeType(Cell.CELL_TYPE_SNAKE_NODE_A);
    	else if(next.type == Cell.CELL_TYPE_FOOD_G)
    		next.changeType(Cell.CELL_TYPE_SNAKE_NODE_G);
    	else if(next.type == Cell.CELL_TYPE_FOOD_C)
    		next.changeType(Cell.CELL_TYPE_SNAKE_NODE_C);
    	else if(next.type == Cell.CELL_TYPE_FOOD_T)
    		next.changeType(Cell.CELL_TYPE_SNAKE_NODE_T);
        snakePartList.insertAtStart(next);
        return s.getScore()+5;
    }

    public void move(Cell nextCell) {
    	int temp = snakePartList.searchAt(snakePartList.size()).type;
    	snakePartList.searchAt(snakePartList.size()).changeType(Cell.CELL_TYPE_EMPTY);
    	snakePartList.deleteAt(snakePartList.size()-1);
    	
        head = nextCell;
        head.changeType(snakePartList.head.data.type);
        
        snakePartList.insertAtStart(head);
        snakePartList.shift(snakePartList, temp);
        
        
    }

     public boolean checkCrash(Cell nextCell) {
        if(nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_A ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_G ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_T ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_C ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_WALL) return true;
        return false;
    } 
}
