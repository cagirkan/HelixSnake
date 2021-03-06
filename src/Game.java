import enigma.core.Enigma;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;



public class Game {
   public enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
   public TextMouseListener tmlis; 
   public KeyListener klis; 
   public Board board = new Board(25,60);
   public Cell initPos = new Cell(2,2,21);
   public Snake snake = new Snake(initPos, board);
   public Router rt = new Router(snake, board);
   public Cell nextCell;
   

   // ------ Standard variables for mouse and keyboard ------
   public int keypr;   // key pressed?
   public int rkey;    // key   (for press/release)
   // ----------------------------------------------------
   
   
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
      rt.print();
      //random #
      /*Random rnd=new Random();
      px = rnd.nextInt(59)+2;
      py =rnd.nextInt(24)+2;
      cn.getTextWindow().output(px,py,'#');*/
      
      
      rt.setDirection(1);
      while(true) {
         if(keypr==1) {    // if keyboard button pressed
            if(rkey==KeyEvent.VK_LEFT) {
            	rt.setDirection(-1); 
            }
            if(rkey==KeyEvent.VK_RIGHT) {
            	rt.setDirection(1);
            }
            if(rkey==KeyEvent.VK_UP) {
            	rt.setDirection(2);
            }
            if(rkey==KeyEvent.VK_DOWN) {
            	rt.setDirection(-2);
            }
            
            if(rkey==KeyEvent.VK_SPACE) {
               String str;         
               str=cn.readLine();     // keyboardlistener running and readline input by using enter 
               cn.getTextWindow().setCursorPosition(5, 20);
               cn.getTextWindow().output(str);
            }
            
            
            keypr=0;    // last action  
         }
         //snake.move(nextCell);
         rt.update();
         rt.print();
         Thread.sleep(100);
         
      }
   }
}
