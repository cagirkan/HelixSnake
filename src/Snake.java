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
        snakePartList.insert(head);
        board.cells[row][col] = head;
    }

    public void grow() {
        snakePartList.insert(head);
    }

    public void move(Cell nextCell) {
    	snakePartList.searchAt(snakePartList.size()).changeType(Cell.CELL_TYPE_EMPTY);
    	snakePartList.deleteAt(snakePartList.size()-1);

        head = nextCell;
        snakePartList.insertAtStart(head);
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
