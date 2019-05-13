public class Node2 {
	   
    private Score data;
    private Node2 prev; 
    private Node2 next;

   public Node2(Score data) {
     this.data = data;
     prev = null;
     next = null;
   }
	   
   public Score getData() {
     return data;
   }

   public void setData(Score data) {
     this.data = data;
   }

   public Node2 getNext() {
     return next; 
   }

   public void setNext(Node2 next) {
     this.next = next;
   }
   
   public Node2 getPrev() {
	 return prev; 
   }

   public void setPrev(Node2 prev) {
     this.prev = prev;
   }
}

