import java.util.Random;

public class SingleLinkedList
{
	Node head;
	
	public void insert (Cell data)
	{
		Node node = new Node();
		node.data = data;
		node.next = null;
		
		if (head == null)
		{
			head = node;
		}
		else
		{
			Node n = head;
			while(n.next != null)
			{
				n = n.next;
			}
			n.next = node;
		}
	}
	
	public void insertAtStart(Cell data)
	{
		Node node = new Node();
		node.data = data;
		node.next = null;
		node.next = head;
		head = node;
	}
	
	public void insertAt(int index, Cell data)
	{
		Node node = new Node();
		node.data = data;
		node.next = null;
		
		Node n = head;
		for (int i = 0; i < index-1; i++)
		{
			n = n.next;
		}
		node.next = n.next;
		n.next = node;
	}
	

	public void show()
	{
		Node node = head;
		
		while(node.next != null)
		{
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.print(node.data);
	}
	
	public int search (Cell input)
	{
		int count = 0;
		Node node = head;
		while (node != null)
		{
			if (node.data.equals(input) == true)
			{
				count++;
				node = node.next;
				
			}
			else
			{
				node = node.next;
			}
		}
		return count;
	}
	
	public Cell searchAt (int index)
	{
		Node n = head;
		for (int i = 0; i < index-1; i++)
		{
			n = n.next;
		}
		return n.data;
	}
	
	public int size ()
	{
		int size = 0;
		Node node = head;
		
		while(node != null)
		{
			size++;
			node = node.next;
		}
		return size;
	}
	
	public boolean delete (Cell input)
	{
		if(head == null)
		{
			System.out.println("SLL is empty");
			return false;
		}
		else if(head.data.equals(input) == true)
		{
			head = head.next;
			return true;
		}
		else 
		{
			Node previous = head;
			Node temp = previous.next;
		
		while (temp != null)
		{
			if(temp.data.equals(input) == true)
			{
				previous.next = temp.next;
				return true;
			}
			previous = temp;
			temp = temp.next;
		}
		System.out.println("Element" + input + "could NOT be found");
		return false;
		}
	}
	
	public void deleteAt(int index)
	{
		if(index==0)
		{
			head = head.next;
		}
		else
		{
			Node n = head;
			Node n1 = null;
			for(int i=0;i<index-1;i++)
			{
				n = n.next;
			}
			n1 = n.next;
			n.next = n1.next;
			//System.out.println("n1 " + n1.data);
			n1 = null;
		}
	}
	
	public int searchSnake (int row, int col)
	{
		Node node = head;
		while (node != null)
		{
			if (node.data.col == col && node.data.row == row)
			{
				return node.data.type;
			}
			else
			{
				node = node.next;
			}
		}
		return -1;
	}
	
	public void shift (SingleLinkedList sll, int type)
	{
		Node node = head.next;
		while (node != null)
		{
			if(node.next != null) {
				node.data.changeType(node.next.data.type);	
			}
			else {
				node.data.changeType(type);	
			}
			node = node.next;
		}
	
	}
	
}
