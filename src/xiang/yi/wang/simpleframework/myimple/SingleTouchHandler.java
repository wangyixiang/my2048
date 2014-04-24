package xiang.yi.wang.simpleframework.myimple;

import java.util.ArrayList;
import java.util.List;

import xiang.yi.wang.simpleframework.Input.TouchEvent;
import xiang.yi.wang.simpleframework.Pool;
import xiang.yi.wang.simpleframework.Pool.PoolObjectFactory;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SingleTouchHandler implements OnTouchListener {
	int lastX;
	int lastY;
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	
	public SingleTouchHandler(View view){
		PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
			public TouchEvent createObject(){
				return new TouchEvent();
			}
		};
		touchEventPool = new Pool<TouchEvent>(factory, 100);
		view.setOnTouchListener(this);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized (this) {
			TouchEvent touchEvent = touchEventPool.newObject();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				touchEvent.type = TouchEvent.TOUCH_DOWN;				
				break;
			case MotionEvent.ACTION_MOVE:
				touchEvent.type = TouchEvent.TOUCH_DRAGGED;
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				touchEvent.type = TouchEvent.TOUCH_UP;
				break;
			}
			
			touchEvent.x = lastX = (int)event.getX();
			touchEvent.y = lastY = (int)event.getY();
			touchEventsBuffer.add(touchEvent);
			return true;			
		}
	}

	public List<TouchEvent> getTouchEvents(){
		synchronized (this) {
			int len = touchEvents.size();
			for(int i = 0; i < len; i++){
				touchEventPool.free(touchEvents.get(i));
			}
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			return touchEvents;		
		}

	}
}
