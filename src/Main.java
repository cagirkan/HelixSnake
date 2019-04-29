import enigma.core.Enigma;

public class Main {
	
	
	public static void main(String[] args) throws Exception{
		Enigma.getConsole("Game", 100, 30);
		//Game myGame = new Game();
		Screen screen = new Screen(25,60); // x koordnatý ile y koordinatý yer deðiþtirdi.
		screen.create();
		screen.print();
	}
	//random-input-menu methods
	
}
