import java.util.LinkedList;

import com.sun.xml.internal.ws.api.pipe.NextAction;

public class Snake {

	SnakeList snakePartList = new SnakeList();
    Cell head;
    Board board;
    private int size;

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
        
        size = 3;
    }

    public int move(Cell nextCell, boolean food, Score s) {
    	if(food) {
    		if (nextCell.type == Cell.CELL_TYPE_FOOD_A) 
				nextCell.changeType(Cell.CELL_TYPE_SNAKE_NODE_A);
    		else if (nextCell.type == Cell.CELL_TYPE_FOOD_G) 
				nextCell.changeType(Cell.CELL_TYPE_SNAKE_NODE_G);
    		else if (nextCell.type == Cell.CELL_TYPE_FOOD_C) 
				nextCell.changeType(Cell.CELL_TYPE_SNAKE_NODE_C);
    		else if (nextCell.type == Cell.CELL_TYPE_FOOD_T) 
				nextCell.changeType(Cell.CELL_TYPE_SNAKE_NODE_T);
    		
    		head = nextCell;
    		snakePartList.insertAtStart(head);
    		size++;
    		return s.getScore()+5;

    	}
    	else {
	    	int temp = snakePartList.searchAt(snakePartList.size()).type;
	    	snakePartList.searchAt(snakePartList.size()).changeType(Cell.CELL_TYPE_EMPTY);
	    	snakePartList.deleteAt(snakePartList.size()-1);
	    	
	        head = nextCell;
	        head.changeType(snakePartList.head.data.type);
	        
	        snakePartList.insertAtStart(head);
	        snakePartList.shift(snakePartList, temp);
    	}
    	
    	return s.getScore();

    }

     public boolean checkCrash(Cell nextCell) {
        if(nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_A ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_G ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_T ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_NODE_C ||
        	nextCell.type == Cell.CELL_TYPE_SNAKE_INNER_WALL) return true;
        return false;
    }

	public String getCodon(int index) {
		String codon, reverse = "", s;
		codon = snakePartList.getSnakeToString();
		 for(int i = codon.length() - 1; i >= 0; i--)
	        {
	            reverse = reverse + codon.charAt(i);
	        }
		 s = reverse.substring(index,index+3);
		 return s;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	} 
	
	
     
}
