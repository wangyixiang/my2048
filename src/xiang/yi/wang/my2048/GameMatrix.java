package xiang.yi.wang.my2048;

public interface GameMatrix {
	public final static int TO_RIGHT = 0;
	public final static int TO_UP = 1;
	public final static int TO_LEFT = 2;
	public final static int TO_BOTTOM = 3;
	public final static int TO_DOWN = 3;
	
	public int[][] merge_to(int side);
	public int[][] get_matrix();
	public int[][] restore_to_previous_matrix();
	
}
