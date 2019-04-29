import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import enigma.console.TextAttributes;
import java.awt.Color;

public class Game {
   public enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
   public KeyListener klis; 

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
      // ----------------------------------------------------
      //////////////////////////////
      int px=1,py=1;
      for (int i = 0; i < 60; i++) {
    	  cn.getTextWindow().output(px,py,'#');
    	  px++;
    	  }
      String score ="SCORE:";
      for (int i = 0; i < score.length(); i++) {
    	  cn.getTextWindow().output(px+4+i,py,score.charAt(i));
    	  }
      for (int i = 0; i < 10; i++) {
    	  cn.getTextWindow().output(px+4+i,py+1,'-');
    	  }
      for (int i = 0; i < 25; i++) {
    	  cn.getTextWindow().output(px,py,'#');
    	  py++;
    	  }
      String level ="Level:";
      for (int i = 0; i < level.length(); i++) {
    	  cn.getTextWindow().output(px+4+i,py-2,level.charAt(i));
    	  }
      String time ="Time:";
      for (int i = 0; i < time.length(); i++) {
    	  cn.getTextWindow().output(px+4+i,py-1,time.charAt(i));
    	  }
      for (int i = 0; i < 60; i++) {
    	  cn.getTextWindow().output(px,py,'#');
    	  px--;
    	  }
      for (int i = 0; i < 25; i++) {
    	  cn.getTextWindow().output(px,py,'#');
    	  py--;
    	  }
      //////////////////////////////////
      //random #
      Random rnd=new Random();
      px = rnd.nextInt(59)+2;
      py =rnd.nextInt(24)+2;
      cn.getTextWindow().output(px,py,'#');
      
      
      
      while(true) {
         if(keypr==1) {    // if keyboard button pressed
            if(rkey==KeyEvent.VK_LEFT) px--;   
            if(rkey==KeyEvent.VK_RIGHT) px++;
            if(rkey==KeyEvent.VK_UP) py--;
            if(rkey==KeyEvent.VK_DOWN) py++;
            
            char rckey=(char)rkey;
            //        left          right          up            down
            if(rckey=='%' || rckey=='\'' || rckey=='&' || rckey=='(') cn.getTextWindow().output(px,py,'P'); // VK kullanmadan test teknigi
            else cn.getTextWindow().output(rckey);
            
            if(rkey==KeyEvent.VK_SPACE) {
               String str;         
               str=cn.readLine();     // keyboardlistener running and readline input by using enter 
               cn.getTextWindow().setCursorPosition(5, 20);
               cn.getTextWindow().output(str);
            }
            
            keypr=0;    // last action  
         }
         Thread.sleep(20);
      }
   }
}
