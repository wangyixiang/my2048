package xiang.yi.wang.my2048;

import xiang.yi.wang.my2048.Matrix;
import xiang.yi.wang.simpleframework.myimple.MyAndroidGame;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.os.Build;

import xiang.yi.wang.my2048.R;

;

public class MainActivity extends MyAndroidGame {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private Matrix matrix;
		private TextView tv;

		public PlaceholderFragment() {
			matrix = new Matrix(5);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			int wang[][] = { { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 },
					{ 2, 2, 5, 4, 4 }, { 0, 0, 3, 0, 0 }, { 0, 0, 3, 0, 0 } };
			matrix.set_matrix(wang);
			tv = (TextView) rootView.findViewById(R.id.matrixtext);
			setTextView(matrix.get_matrix(), tv);
			Button rb = (Button) rootView.findViewById(R.id.button1);
			rb.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					matrix.rotate_clockwise();
					setTextView(matrix.get_matrix(), tv);
				}
			});
			return rootView;
		}

		private void setTextView(int matrix[][], TextView tv) {
			StringBuffer sb = new StringBuffer();
			int ms = matrix.length;
			int x, y;
			for (x = 0; x < ms; x++) {
				for (y = 0; y < ms; y++) {
					if (matrix[x][y] == 0) {
						sb.append("  ");
					} else {
						sb.append(matrix[x][y]);
					}
				}
				sb.append('\n');
			}
			tv.setText(sb.toString());
		}
	}

}
