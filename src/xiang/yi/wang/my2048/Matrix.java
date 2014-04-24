package xiang.yi.wang.my2048;

import java.util.ArrayList;

public class Matrix implements GameMatrix {
	
	private int matrix[][];
	private int size;
	private ArrayList<int []> left = new ArrayList<int[]>(16);
	private static final int BACK_LIST_SIZE = 2*51200;
	private ArrayList<int [][]> previousMatrix = new ArrayList<int[][]>(BACK_LIST_SIZE);
	
	public boolean changed = false;
	
	public Matrix(int size){
		super();
		this.init(size);
	}

	private void init(int size) {
		this.size = size;
		this.matrix = new int[size][size];
		this.matrix[0][3] = 2;
		this.matrix[1][2] = 2;
		this.matrix[2][0] = 2;
	}
	
	private void initForTesting(){
		int x,y;
		for (y=0; y < this.size; y++){
			for (x = 0; x < this.size; x++){
				this.matrix[x][y] = powerOn2( y*this.size + x + 1);
			}
		}
	}

	private int powerOn2(int power){
		if(power == 0){
			return 1;
		} else if (power == 1) {
			return 2;
		} 
		return powerOn2(power - 1) * 2;
	}

	protected boolean set_matrix(int matrix[][]) {
		int x;
		for(x = 0; x < matrix.length; x++){
			if(matrix[x].length != matrix.length){
				return false;
			}
		}
		init(matrix.length);
		for ( x = 0; x < matrix.length; x++){
			System.arraycopy(matrix[x], 0, this.matrix[x], 0, matrix.length);
		}
		return true;
	}

	public int[][] get_matrix() {
		return this.matrix;
	}

	protected void rotate_clockwise() {
		this.matrix = this.rotate_clockwise(this.matrix);
	}

	@Override
	public int[][] merge_to(int side){
		int temp[][] = new int[this.size][this.size];

		for (int x = 0; x < this.matrix.length; x++){
			System.arraycopy(this.matrix[x], 0, temp[x], 0, this.matrix.length);
		}

		if ( side < 4 && side > -1){
			int pre_rotate_time = side;
			int after_rotate_time = 4 - side;
			for (; pre_rotate_time > 0; pre_rotate_time--){
				rotate_clockwise();
			}
			merge_to_right();
			if (after_rotate_time != 4){
				for(; after_rotate_time > 0; after_rotate_time--){
					rotate_clockwise();
				}
			}
		}
		
		if (this.previousMatrix.size() == 0) {
			this.previousMatrix.add(temp);
		} else {
			if (compare_matrix(this.matrix, temp)) {
				return this.matrix;
			}else{
				this.previousMatrix.add(temp);
			}
		}
		
		left.clear();
		for (int x = 0; x < this.size; x++){
			for(int y = 0; y < this.size; y++){
				if (this.matrix[x][y] == 0){
					left.add(new int[] {x,y});
				}
			}
		}
		if (left.size() > 0){
			int[] pos;
			pos = left.get((int)(Math.random()*left.size()));
			this.matrix[pos[0]][pos[1]] = 2;
		}
		return this.matrix;
	}
	
	private int[][] rotate_clockwise(int[][] matrix) {
		int result[][] = new int[this.size][this.size];
		int x, y;
		for (x = 0; x < this.size; x++) {
			for (y = 0; y < this.size; y++) {
				result[x][y] = matrix[this.size - 1 - y][x];
			}
		}
		return result;
	}

	/*
	 * it's very comfort to image when the matrix just moves in the java array's
	 * natural direction.
	 */
	private void merge_to_right(){
		int x;
		for (x = 0; x < this.matrix.length; x++){
			int temp[] = new int[this.matrix.length];
			int temp_pos = this.matrix.length - 1;
			int y;
			for(y = this.matrix.length - 1; y >= 0; y--){
				if (this.matrix[x][y] != 0){
					temp[temp_pos] = this.matrix[x][y];
					temp_pos--;
				}
			}
			if( temp_pos != 0){
				for (;temp_pos >0; temp_pos--){
					temp[temp_pos] = 0;
				}
			}
			boolean merged = false;
			for(y = this.matrix.length - 1; y >= 1; y--){
				if (temp[y] == 0 && temp[y - 1] == 0){
					break;
				} else if (temp[y] == temp[y - 1] &&  !merged){
					temp[y] *= 2;
					temp[y - 1] = 0;
					merged = true;
				} else if (temp[y] == 0) {
					temp[y] = temp[y - 1];
					temp[y - 1] = 0;
				}
			}
			this.matrix[x] = temp;
		}
	}
	
	private boolean compare_matrix(int[][] left, int[][] right){
		for (int x = 0; x < this.size; x++){
			for(int y = 0; y < this.size; y++){
				if ( left[x][y] != right[x][y]){
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public int[][] restore_to_previous_matrix() {
		// TODO Auto-generated method stub
		return null;
	}

}
