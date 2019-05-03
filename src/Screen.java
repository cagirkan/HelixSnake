
public class Screen {

	private int x;
	private int y;
	private Object[][] screenArr;
	
	public Screen(int x, int y) {
		this.x = x;
		this.y = y;
		screenArr = new Object[x][y];
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public Object[][] getScreenArr() {
		return screenArr;
	}

	public void create() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (i == 0 || i == x-1 || j == 0 || j == y-1) 
					screenArr[i][j] = '#';
				else
					screenArr[i][j] = ' ';
			}
		}
	}
	
	public void print() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(screenArr[i][j]);
			}
			System.out.println();
		}
	}
	
	public void setScreenArr(Object[][] screenArr) {
		this.screenArr = screenArr;
	}
}
