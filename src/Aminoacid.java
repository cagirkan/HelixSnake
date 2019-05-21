import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Aminoacid {
	AminoacidNode head;	

	public Aminoacid() {

	}

	public Aminoacid(String filePath) throws NumberFormatException, IOException {
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st; 
		while ((st = br.readLine()) != null) 
		{ 
			String category = null;
			String item = null;
			String stArr[] = st.split(",");

			for (int i = 0; i < stArr.length; i++)
				if(i == 0)
				{
					category = stArr[i];
					addAminoAcid(category);
				}
				else if (i == 1)
					continue;
				else
				{
					item = stArr[i];
					String codonArr[] = item.split("-");
					addCodon(category, codonArr[0], Integer.parseInt(codonArr[1]));
				}

		}
		br.close();
	}

	public void addAminoAcid(String dataToAdd) {
		AminoacidNode temp;
		if (head == null) {
			temp = new AminoacidNode(); temp.AminoAcidName = dataToAdd; 
			head = temp;
		}
		else {		     
			temp = head;
			while (temp.down != null)
				temp = temp.down;
			AminoacidNode newnode = new AminoacidNode(); newnode.AminoAcidName = dataToAdd;
			temp.down = newnode;
		}
	}

	public void addCodon(String Category, String Item, int CodonScore) {
		if (head == null)    
			System.out.println("Add a Category before Item");
		else {
			AminoacidNode tempCategory = head;
			while (tempCategory != null)
			{	    	 
				if (Category.equals(tempCategory.AminoAcidName)) 
				{
					CodonNode rightTemp = tempCategory.right; 
					if (rightTemp == null) {
						rightTemp = new CodonNode(Item, CodonScore);
						tempCategory.right = rightTemp;
					}
					else {				 
						while (rightTemp.next != null)
							rightTemp = rightTemp.next;
						CodonNode newItem = new CodonNode(Item, CodonScore);
						rightTemp.next = newItem;
					}			          
				}
				tempCategory = tempCategory.down;
			}
		}
	}

	public int sizeAminoAcid()
	{
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			AminoacidNode tempCategory = head;
			while (tempCategory != null)
			{
				count++;
				tempCategory = tempCategory.down;
			}
		}
		return count;   
	}

	public int sizeCodon()
	{
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			AminoacidNode tempCategory = head;
			while (tempCategory != null)
			{
				CodonNode tempItem = tempCategory.right;
				while (tempItem != null)
				{
					count++;
					tempItem = tempItem.next;
				}
				tempCategory = tempCategory.down;
			}
		}
		return count;   
	}

	public void display()
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			AminoacidNode tempCategory = head;
			while (tempCategory != null)
			{
				System.out.print(tempCategory.AminoAcidName + " --> ");
				CodonNode tempItem = tempCategory.right;
				while (tempItem != null)
				{
					System.out.print(tempItem.codonName + " ");
					tempItem = tempItem.next;
				}
				tempCategory = tempCategory.down;
				System.out.println();
			}
		}
	}

	public int searchCodonScore (String codon)
	{
		int Score = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			AminoacidNode tempCategory = head;
			while (tempCategory != null)
			{
				CodonNode tempItem = tempCategory.right;
				while (tempItem != null)
				{
					if (tempItem.codonName.equals(codon))
					{
						Score = tempItem.codonScore;
						break;
					}
					tempItem = tempItem.next;
				}
				tempCategory = tempCategory.down;
			}
		}  
		return Score;
	}

}
