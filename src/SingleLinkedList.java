import java.util.Random;

public class SingleLinkedList {
	private Node head;
	public SingleLinkedList() {
		head=null;
	}
	public void add(Object dataToAdd) {
		Node newnode= new Node(dataToAdd);
		if(head==null) 
			head=newnode;
		else {
			Node temp=head;
			while(temp.getLink()!=null)
				temp= temp.getLink();
			temp.setLink(newnode);
		}
	}
	public String display() {
		String output="";
		Node temp = head;
		while(temp !=null) {
			output+=temp.getData()+" ";
			temp=temp.getLink();
		}
		return output;
	}
	public void remove(Object dataToRemove) {
		if(head==null)
			System.out.println("Linked list is empty");
		else {
			while((Integer)head.getData()==(Integer)dataToRemove)
				head = head.getLink();
			Node temp=head;//temp = next
			Node pre=null;
			while(temp!=null) {
				if((Integer)temp.getData()==(Integer)dataToRemove) {
					pre.setLink(temp.getLink());
					temp=pre;
				}
				else 
					pre=temp;
				temp = temp.getLink();
			}
		}
	}
	public int size() {
		int counter=0;
		if(head==null)
			return 0;
		else {
			Node temp=head;
			while(temp!=null) {
				counter++;
				temp=temp.getLink();
			}
			return counter;
		}
	}
	public void random(){
		Random rnd = new Random();
	    String letter = "ACGT";
	    for (int i = 0; i < 3; i++) {
	    	add(letter.charAt(rnd.nextInt(letter.length())));
	        System.out.println(letter.charAt(rnd.nextInt(letter.length())));
	    }
	}
	public boolean search(Object data) {
		boolean ret=false;
		if(head==null)
			return false;
		else {
			Node temp=head;
			while(temp!=null) {
				if((int)temp.getData()==(int)data) {
					ret=true;
					break;
				}
				temp = temp.getLink();
			}
		}
		return ret;
	}
}