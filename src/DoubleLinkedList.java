public class DoubleLinkedList {
	private Node2 head;	
	private Node2 tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
	}

	public void Add(Score data) {
		Node2 newnode;
		newnode = new Node2(data); 
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
				Node2 temp=head;
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

	public int size()
	{
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			Node2 temp = head;
			while (temp != null)
			{
				count++;
				temp=temp.getNext();
			}
		}
		return count;   
	}

	public void display1()//at the beginnig
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node2 temp = head;
			while (temp != null)
			{
				System.out.println(temp.getData().getScore() + " " + temp.getData().getName());
				temp = temp.getNext();
			}
			System.out.println();
		}
	}	

	public void display2()//at the end
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node2 temp = tail;
			while (temp != null)
			{
				System.out.print(temp.getData() + " ");
				temp = temp.getPrev();
			}
			System.out.println();
		}
	}
}

