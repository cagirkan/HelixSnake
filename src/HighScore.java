import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HighScore {
	
	public DoubleLinkedList readfile() throws Exception {
		DoubleLinkedList dll = new DoubleLinkedList();
		int score=0;
		String name;
		
		File file = new File("C:\\Users\\hicag\\git\\HelixSnake\\src\\highscores.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st; 
		while ((st = br.readLine()) != null) { 
		  String stArr[] = st.split(";");
		  name = stArr[0];
		  score = Integer.parseInt(stArr[1]);
		  Score sc = new Score(score,name);
		  dll.Add(sc);
		}
		br.close();
		return dll;
	}
}
