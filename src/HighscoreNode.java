public class HighscoreNode {
	   
    private Score data;
    private HighscoreNode prev; 
    private HighscoreNode next;

   public HighscoreNode(Score data) {
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

   public HighscoreNode getNext() {
     return next; 
   }

   public void setNext(HighscoreNode next) {
     this.next = next;
   }
   
   public HighscoreNode getPrev() {
	 return prev; 
   }

   public void setPrev(HighscoreNode prev) {
     this.prev = prev;
   }
}

