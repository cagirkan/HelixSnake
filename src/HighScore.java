import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScore {
	
	public DoubleLinkedList readfile() {
		int score=0, ct = 0;
		String name;
		DoubleLinkedList dll = new DoubleLinkedList();
	BufferedReader reader;
	try {
		reader = new BufferedReader(new FileReader("C:\\score.txt"));
		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			String []arrline = line.split(";");
			//if(ct == 0)
				//score = Integer.parseInt(arrline[0].substring(2));
			//else
			score = Integer.parseInt(arrline[0]);
			name = arrline[1];
			Score data = new Score(score,name);
			dll.Add(data);
			line = reader.readLine();
			ct++;
		}
		reader.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return dll;
	}
}
