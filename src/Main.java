import enigma.core.Enigma;

public class Main {
	
	
	public static void main(String[] args) throws Exception{
		Enigma.getConsole("Game", 100, 30);
		//Game myGame = new Game();
		Screen screen = new Screen(25,60); // x koordnat� ile y koordinat� yer de�i�tirdi.
		screen.create();
		screen.print();
	}
	//random-input-menu methods
	
}
