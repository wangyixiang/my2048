package xiang.yi.wang.simpleframework.myimple;

import java.util.List;

import xiang.yi.wang.simpleframework.Input;
import android.content.Context;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyAndrioidInput implements Input {
	SingleTouchHandler touchHandler;
	public MyAndrioidInput(Context context, View view, float scaleX, float scaleY) {
		touchHandler = new SingleTouchHandler(view);
	}

	@Override
	public boolean isKeyPressed(int keyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTouchDown(int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTouchX(int pointer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTouchY(int pointer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccelX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccelY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccelZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KeyEvent> getKeyEvents() {
		return null;
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		return touchHandler.getTouchEvents();
	}

}
