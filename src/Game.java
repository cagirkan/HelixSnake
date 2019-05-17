import enigma.core.Enigma;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;



public class Game {
	public enigma.console.Console cn = Enigma.getConsole("Helix Snake",110,40);
	public Scanner sc = new Scanner(System.in);
	public KeyListener klis; 
	public HighScore hs = new HighScore();
	public Score score = new Score();
	DoubleLinkedList highscore;


	public Board board = new Board(25,60);
	public Cell initPos = new Cell();
	public Snake snake = new Snake(initPos, board);
	public Router rt = new Router(snake, board, score);
	public Cell nextCell;


	// ------ Standard variables for mouse and keyboard ------
	public int keypr;   // key pressed?
	public int rkey;    // key   (for press/release)
	// ----------------------------------------------------

	int time = 0, level = 0, moveSpeed = 100;
	Game() throws Exception {   // --- Contructor

		// ------ Standard code for mouse and keyboard ------ Do not change
		klis=new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(keypr==0) {
					keypr=1;
					rkey=e.getKeyCode();
				}
			}
			public void keyReleased(KeyEvent e) {}
		};

		highscore = hs.readfile();
		cn.getTextWindow().addKeyListener(klis);
		board.generateFood();
		board.generateFood();
		board.generateFood();
		rt.setDirection(1);
		System.out.println("--------------------------Helix Snake-----------------------");
		while(true) {
			cn.getTextWindow().setCursorPosition(0, 2);
			System.out.println("                      Level: " + level + " Time: " + time/1000);
			if(keypr==1) {    // if keyboard button pressed
				if(rkey==KeyEvent.VK_LEFT && rt.getDirection() != 1) {
					rt.setDirection(-1); 
				}
				if(rkey==KeyEvent.VK_RIGHT && rt.getDirection() != -1) {
					rt.setDirection(1);
				}
				if(rkey==KeyEvent.VK_UP && rt.getDirection() != -2) {
					rt.setDirection(2);
				}
				if(rkey==KeyEvent.VK_DOWN && rt.getDirection() != 2) {
					rt.setDirection(-2);
				}
				if(rkey==KeyEvent.VK_SPACE) {
					pause(rt);
				}

				keypr=0;    // last action  
			}
			
			//Score Field
			printLine(62, 3, 25, 'v');
			printLine(87, 3, 25, 'v');
			cn.getTextWindow().setCursorPosition(63, 3);
			System.out.println("Score: " + score.getScore());
			printLine(4, 63, 10, 'h');

			cn.getTextWindow().setCursorPosition(64, 28);
			
			rt.update();
			cn.getTextWindow().setCursorPosition(0, 3);
			rt.print();
			Thread.sleep(moveSpeed);
			time += moveSpeed;
			if (time%5000 == 0) {
				board.generateWall();
				level++;
			}
			if(rt.gameOver) {
				System.out.println("GAME OVER");
				System.out.println("Score= " + score.getScore());
				System.out.println("Level= " + level);
				break;
			}
		}
		cn.getTextWindow().setCursorPosition(0, 35);
		highscore.display1();
	}

	//Gets position and start and length parameters as integer and direction as c(h for horizontal, v for vertical)
	public void printLine(int position, int start, int length, char direction) {
		if(direction == 'v') {
			for (int i = start; i < start + length; i++) {
				cn.getTextWindow().setCursorPosition(position, i);
				System.out.print("|");
			}
		}
		else {
			for (int i = start; i < start + length; i++) {
				cn.getTextWindow().setCursorPosition(i, position);
				System.out.print("-");
			}
		}
	}

	public void pause(Router rt) {
		cn.getTextWindow().setCursorPosition(0, 28);
		System.out.println("Game paused. Press r and Enter for resume.");
		String s = sc.nextLine();
		while (true) {
			if(s.equals("r")) {
				cn.getTextWindow().setCursorPosition(0, 28);
				System.out.println("                                             \n                        ");
				break;
			}
			s = sc.nextLine();
		}
	}
}
