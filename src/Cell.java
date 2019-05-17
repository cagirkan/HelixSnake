import java.util.Random;

public class Cell {

	 final static int CELL_TYPE_EMPTY = 0, 
			 			CELL_TYPE_FOOD_A = 11, CELL_TYPE_FOOD_G = 12, CELL_TYPE_FOOD_T = 13, CELL_TYPE_FOOD_C = 14,
			 			CELL_TYPE_SNAKE_NODE_A = 21, CELL_TYPE_SNAKE_NODE_G = 22, CELL_TYPE_SNAKE_NODE_T = 23, CELL_TYPE_SNAKE_NODE_C = 24, 
			 			CELL_TYPE_SNAKE_WALL = 30 , CELL_TYPE_SNAKE_INNER_WALL = 31;
	    int row, col;
	    char data;
	    int type;
	    Random rnd;
	    
	    public Cell() {
	    	rnd = new Random();
	    	int row = rnd.nextInt(15)+5;
	    	int col = rnd.nextInt(50)+5;
		    int type = rnd.nextInt(4)+21;
	    	this.row = row;
	        this.col = col;
		    this.type = type;
		    changeData();
	    }

	    public Cell(int row, int col) {
	        this.row = row;
	        this.col = col;
	        rnd = new Random();
	        this.type = rnd.nextInt(4)+21;
	        changeData();
	    }
	    
	    public Cell(int row, int col, int type) {
	        this.row = row;
	        this.col = col;
	        this.type = type;
	        changeData();
	    }
	    
	    public void changeType(int type) {
	    	this.type = type;
	    	changeData();
	    	
	    }
	    
	    private void changeData() {
	    	if(type == CELL_TYPE_EMPTY) this.data = ' ';
	        else if (type == CELL_TYPE_FOOD_A) this.data = 'A';
	        else if (type == CELL_TYPE_FOOD_G) this.data = 'G';
	        else if (type == CELL_TYPE_FOOD_C) this.data = 'C';
	        else if (type == CELL_TYPE_FOOD_T) this.data = 'T';
	        else if(type == CELL_TYPE_SNAKE_NODE_A) this.data = 'A';
	        else if(type == CELL_TYPE_SNAKE_NODE_G) this.data = 'G';
	        else if(type == CELL_TYPE_SNAKE_NODE_T) this.data = 'T';
	        else if(type == CELL_TYPE_SNAKE_NODE_C) this.data = 'C';
	        else this.data = '#';
	    }
}
