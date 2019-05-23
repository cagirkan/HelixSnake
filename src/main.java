import java.awt.event.KeyEvent;
import java.util.Scanner;

import enigma.core.Enigma;

public class main {

	public static enigma.console.Console cn = Enigma.getConsole("Helix Snake",110,40);
	public static void main(String[] args) throws Exception {
		menu();
	}


	public static void menu() throws Exception {
		Scanner sc = new Scanner(System.in);
		int answer=0, option = 0, moveSpeed = 100, wallLevel = 20000;
		String gameType = "global";
		String choose;
		while(answer!=4) {
			clearConsole();
			cn.getTextWindow().setCursorPosition(42, 0);
			System.out.println("WELCOME TO THE HELIX SNAKE GAME!");
			cn.getTextWindow().setCursorPosition(45, 2);
			System.out.println("----------MENU----------");
			cn.getTextWindow().setCursorPosition(45, 5);
			System.out.println("1 - Play Helix Snake");
			cn.getTextWindow().setCursorPosition(45, 6);
			System.out.println("2 - High Score List");
			cn.getTextWindow().setCursorPosition(45, 7);
			System.out.println("3 - Settings");
			cn.getTextWindow().setCursorPosition(45, 8);
			System.out.println("0 - Exit");
			cn.getTextWindow().setCursorPosition(45, 11);
			System.out.print("Your choose is: ");
			answer=sc.nextInt();
			switch(answer) {
			case 1 :
				Game game = new Game(moveSpeed,wallLevel,gameType);
				break;
				
			case 2 :
				HighScore hs = new HighScore("C:\\Users\\hicag\\git\\HelixSnake\\src\\highscores.txt");
				clearConsole();
				cn.getTextWindow().setCursorPosition(45, 2);
				System.out.println("----------HIGHSCORES----------\n");
				cn.getTextWindow().setCursorPosition(45, 4);
				hs.display(cn);
				Thread.sleep(3500);
				break;
				
			case 3:
				while(option!=4) {
					clearConsole();
					cn.getTextWindow().setCursorPosition(45, 2);
					System.out.println("----------SETTINGS----------");
					cn.getTextWindow().setCursorPosition(45, 5);
					System.out.println("1 - Speed");
					cn.getTextWindow().setCursorPosition(45, 6);
					System.out.println("2 - Level");
					cn.getTextWindow().setCursorPosition(45, 7);
					System.out.println("3 - Move Type");
					cn.getTextWindow().setCursorPosition(45, 8);
					System.out.println("4 - Back");
					cn.getTextWindow().setCursorPosition(45, 10);
					System.out.print("Select an option: ");
					option=sc.nextInt();
					switch(option) {
					case 1 :
						clearConsole();
						cn.getTextWindow().setCursorPosition(45, 2);
						System.out.print("Enter a speed level between 1 and 10: ");
						moveSpeed = 200 - sc.nextInt()*25;
						cn.getTextWindow().setCursorPosition(45, 3);
						System.out.println("Speed level will be " + moveSpeed);
						Thread.sleep(2000);
						break;
					
					case 2 :
						clearConsole();
						cn.getTextWindow().setCursorPosition(45, 2);
						System.out.print("Enter a level between 1 and 20: ");
						wallLevel = 21000 - (sc.nextInt())*1000;
						cn.getTextWindow().setCursorPosition(45, 3);
						System.out.println("Wall generate level will be " + wallLevel);
						Thread.sleep(2000);
						break;
					
					case 3 :
						clearConsole();
						cn.getTextWindow().setCursorPosition(45, 2);
						System.out.print("Type fps for First person move type: ");
						cn.getTextWindow().setCursorPosition(45, 3);
						System.out.print("Type global for global move type");
						cn.getTextWindow().setCursorPosition(45, 4);
						System.out.print("Make your choice: ");
						gameType = sc.next();
						cn.getTextWindow().setCursorPosition(45, 6);
						System.out.println("Game's move type will be " + gameType);
						Thread.sleep(2000);
						break;
					case 4:
						break;
					default :
						System.out.println("Invalid value");
						break;
					}
				}
				option = 0;
				break;
			case 0:
				System.exit(0);
				break;
			default :
				System.out.println("Invalid value");
				break;
			}
		}
	}

	public static void clearConsole() {
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < 30; i++) {
			System.out.println("                                                                                    ");
		}
		cn.getTextWindow().setCursorPosition(0, 0);
	}


}
