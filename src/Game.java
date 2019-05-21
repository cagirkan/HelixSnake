import enigma.console.TextAttributes;
import enigma.core.Enigma;
import enigma.event.TextMouseListener;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;



public class Game {
	public enigma.console.Console cn = Enigma.getConsole("Helix Snake",110,40);
	public Scanner sc = new Scanner(System.in);
	public KeyListener klis; 
	public Score score = new Score();
	HighScore highscore = new HighScore("C:\\Users\\hicag\\git\\HelixSnake\\src\\highscores.txt");
	public TextAttributes ta = new TextAttributes(Color.red, Color.black);

	public Board board = new Board(25,60);
	public Cell initPos = new Cell();
	public Snake snake = new Snake(initPos, board);
	public Router rt = new Router(snake, board, score);
	public Aminoacid aminoacids = new Aminoacid("C:\\Users\\hicag\\git\\HelixSnake\\src\\aminoacids.txt");
	public String codon;
	public int codonScore = 0, scorePosition = 5;
	public Cell nextCell;

	// ------ Standard variables for mouse and keyboard ------
	public int keypr;   // key pressed?
	public int rkey;    // key   (for press/release)
	// ----------------------------------------------------

	int time = 0, level = 0, moveSpeed = 100;
	public Game() throws Exception {   // --- Contructor
		cn.setTextAttributes(ta);
		

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
			if (snake.getSize() == 3) {
				codon = snake.getCodon(snake.snakePartList.size()-3);
				codonScore =  aminoacids.searchCodonScore(codon);
				score.setScore(score.getScore() + codonScore);
				snake.setSize(0);
				if (codonScore != 0) {
					cn.getTextWindow().setCursorPosition(63, scorePosition);
					System.out.println(codon + "     " + codonScore);
					scorePosition ++;
				}
			}
			
			rt.update();
			cn.getTextWindow().setCursorPosition(0, 3);
			rt.print(cn);
			Thread.sleep(moveSpeed);
			time += moveSpeed;
			if (time%5000 == 0) {
				board.generateWall();
				level++;
			}
			if(rt.gameOver) {
				cn.getTextWindow().setCursorPosition(0, 12);
				System.out.println("#   _____                         ____                   _ \r\n" + 
						"#  / ____|                       / __ \\                 | |\r\n" + 
						"# | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __  | |\r\n" + 
						"# | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__| | |\r\n" + 
						"# | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |    |_|\r\n" + 
						"#  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|    (_)");
				printLine(20, 20, 22, 'h');
				printLine(22, 20, 22, 'h');
				cn.getTextWindow().setCursorPosition(21, 21);
				System.out.print("Enter Name: ");
				score.setName(sc.nextLine());
				cn.getTextWindow().setCursorPosition(0, 18);
				
				System.out.println();
				break;
			}
		}
		
		cn.getTextWindow().setCursorPosition(0, 35);
		if(score.getScore() >= highscore.getLast().getScore()) {
			highscore.Add(score);
			highscore.deleteLast();
		}
		highscore.displayTopTen();
		highscore.save("C:\\Users\\hicag\\git\\HelixSnake\\src\\highscores.txt");
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
		int dir = rt.getDirection();
		cn.getTextWindow().setCursorPosition(0, 28);
		System.out.println("Game paused. Press space for continue.");
		rkey = 0;
		while (true) {
			rt.setDirection(0);
			cn.getTextWindow().setCursorPosition(0, 28);
			if(keypr==1) {
				if (rkey == KeyEvent.VK_SPACE) 
					break;
				keypr=0;
			}
		}
		System.out.println("                                           \n                        ");
		rt.setDirection(dir);

	}
}
