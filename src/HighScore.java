import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {
	private HighscoreNode head;	
	private HighscoreNode tail;

	public HighScore() {
		head = null;
		tail = null;
	}

	public HighScore(String filePath) throws NumberFormatException, IOException {
		head = null;
		tail = null;
		int score=0;
		String name;

		File file = new File(filePath); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String st; 
		while ((st = br.readLine()) != null) { 
			String stArr[] = st.split(";");
			name = stArr[0];
			score = Integer.parseInt(stArr[1]);
			Score sc = new Score(score,name);
			Add(sc);
		}
		br.close();
	}

	public void Add(Score data) {
		HighscoreNode newnode;
		newnode = new HighscoreNode(data); 
		if (head == null) {  //list is empty
			head = newnode;
			tail = newnode;	     
		}
		else {			
			if(((Integer)newnode.getData().getScore()>=(Integer)head.getData().getScore())) {//temp==head
				head.setPrev(newnode);//temp
				newnode.setNext(head);//temp
				head=newnode;
			}
			else if(((Integer)newnode.getData().getScore()<=(Integer)tail.getData().getScore())) {//temp=tail
				tail.setNext(newnode);//temp
				newnode.setPrev(tail);//temp
				tail=newnode;
			}
			else{
				HighscoreNode temp=head;
				while (temp != null)
				{
					if((Integer)temp.getData().getScore()>(Integer)newnode.getData().getScore() &&
							(Integer)newnode.getData().getScore()>(Integer)temp.getNext().getData().getScore()) {
						temp.getNext().setPrev(newnode);
						newnode.setNext(temp.getNext());
						temp.setNext(newnode);
						newnode.setPrev(temp);
						break;
					}
					temp = temp.getNext();
				}
			}
		}
	}

	public int size(){
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			HighscoreNode temp = head;
			while (temp != null)
			{
				count++;
				temp=temp.getNext();
			}
		}
		return count;   
	}

	public void displayTopTen(){
		int counter = 0;
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			HighscoreNode temp = head;
			while (temp != null)
			{
				counter++;
				System.out.println(temp.getData().getScore() + " " + temp.getData().getName());
				temp = temp.getNext();
				if(counter >= 10)
					break;
			}
			System.out.println();
		}
	}	


	public void save(String filePath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		if (head == null)    
			System.out.println("Linked list is empty");
		else {
			HighscoreNode temp = head;
			while (temp != null)
			{
			    writer.write(temp.getData().getName() + ";" + temp.getData().getScore()+"\n");
				temp = temp.getNext();
			}
			writer.close();
		}
	}

	public Score getLast() {
		Score last;
		HighscoreNode temp = head;
		while (temp.getNext() != null)
		{
			temp = temp.getNext();
		}
		last = temp.getData();
		return last;
	}
	
	public void deleteLast() {
		HighscoreNode tempHead = head;
		HighscoreNode tempTail = tail;

		while (tempHead.getNext() != null)
		{
			tempTail = tempHead;
			tempHead = tempHead.getNext();
		}
		tempHead = null;
		tempTail.setNext(null);
		tail = tempTail;
	}
}

