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
   
   int time = 0, level = 0;
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
      
      
      cn.getTextWindow().addKeyListener(klis);
      board.generateFood();
      board.generateFood();
      board.generateFood();
      rt.setDirection(1);
      System.out.println("--------------------------Helix Snake-----------------------");
      while(true) {
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
         
         rt.update();
         cn.getTextWindow().setCursorPosition(0, 2);
         rt.print();
         Thread.sleep(300);
         time += 300;
         if (time >= 5000) {
        	 board.generateWall();
        	 time = 0;
        	 level++;
         }
         if(rt.gameOver) {
        	 System.out.println("GAME OVER");
        	 System.out.println("Score= " + score.getScore());
        	 System.out.println("Level= " + level);
        	 break;
         }
      }
      highscore = hs.readfile();
      highscore.display1();
   }
   
   public void pause(Router rt) {
	  System.out.println("Game paused. Press r and Enter for resume.");
	  String s = sc.nextLine();
	  while (true) {
		  if(s.equals("r")) {
			  cn.getTextWindow().setCursorPosition(0, 27);
			  System.out.println("                                             \n                        ");
			  break;
		  }
		  s = sc.nextLine();
	  }
   }
}
