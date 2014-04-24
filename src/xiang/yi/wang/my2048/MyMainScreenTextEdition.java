package xiang.yi.wang.my2048;

import java.util.List;

import xiang.yi.wang.simpleframework.Game;
import xiang.yi.wang.simpleframework.Input;
import xiang.yi.wang.simpleframework.Input.TouchEvent;
import xiang.yi.wang.simpleframework.Screen;
import xiang.yi.wang.simpleframework.myimple.MyAndroidGraphics;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MyMainScreenTextEdition extends Screen {
	final static String TAG="MyMainScreenTextEdition";
	final float EFFECTIVE_TIME = 0.5f;
	private int effectiveDistance = 150;
	
	public int getEffectiveDistance() {
		return effectiveDistance;
	}

	public void setEffectiveDistance(int effectiveDistance) {
		this.effectiveDistance = effectiveDistance;
	}

	Paint textPaint;
	GameMatrix matrix;
	Rect boundRect = new Rect();
	Input input;
	int direction;
	float effectiveTime = 0;
	int xForward=0, yForward=0, xLast=0, yLast=0;
	boolean processed = false;
	boolean debug = false;
	String directionText="";

	public MyMainScreenTextEdition(Game game) {
		super(game);
		this.input = game.getInput();
		this.textPaint = ((MyAndroidGraphics)game.getGraphics()).getTextPaint();
		this.matrix = new Matrix(4);
	}

	@Override
	public void update(float deltaTime) {
		processGestureAndUpdateMatrix(deltaTime);
	}
	
	private void processGestureAndUpdateMatrix(float deltaTime){
		int x,y;
		this.effectiveTime = this.effectiveTime + deltaTime;
		List<TouchEvent> touchEvents = input.getTouchEvents();
		this.effectiveTime = this.effectiveTime + deltaTime;
		int len = touchEvents.size();
		for (int i=0; i< len; i++){
			x = touchEvents.get(i).x;
			y = touchEvents.get(i).y;
			if (touchEvents.get(i).type == TouchEvent.TOUCH_DOWN){
				this.xLast = x;
				this.yLast = y;
				continue;
			}
			this.xForward += x - this.xLast;
			this.yForward += y - this.yLast;
			this.xLast = x;
			this.yLast = y;
			if (touchEvents.get(i).type == TouchEvent.TOUCH_UP){
				this.processed = false;
				this.directionText = "";
				this.xForward = 0;
				this.yForward = 0;
				return;
			}
			if((this.effectiveTime >= this.EFFECTIVE_TIME) && !this.processed){
				if (Math.abs(this.xForward) >= Math.abs(this.yForward)){
					if (this.xForward <= -this.effectiveDistance){
						this.processed = true;
						this.direction = GameMatrix.TO_LEFT;
					} else if ( this.xForward >= this.effectiveDistance){
						this.processed = true;
						this.direction = GameMatrix.TO_RIGHT;
					}
				} else {
					if (this.yForward <= -this.effectiveDistance){
						this.processed = true;
						this.direction = GameMatrix.TO_UP;
					} else if ( this.yForward >= this.effectiveDistance){
						this.processed = true;
						this.direction = GameMatrix.TO_DOWN;
					}
				}
				if (this.processed) {
					switch (this.direction) {
					case GameMatrix.TO_UP:
						this.directionText = "TO_UP";
						this.matrix.merge_to(GameMatrix.TO_UP);
						MyGameActivity.Assets.soundMergeUp.play(100.0f);
						break;
					case GameMatrix.TO_DOWN:
						this.directionText = "TO_DOWN";
						this.matrix.merge_to(GameMatrix.TO_DOWN);
						MyGameActivity.Assets.soundMergeDown.play(100.0f);
						break;
					case GameMatrix.TO_LEFT:
						this.directionText = "TO_LEFT";
						this.matrix.merge_to(GameMatrix.TO_LEFT);
						MyGameActivity.Assets.soundMergeLeft.play(100.0f);
						break;
					case GameMatrix.TO_RIGHT:
						this.directionText = "TO_RIGHT";
						this.matrix.merge_to(GameMatrix.TO_RIGHT);
						MyGameActivity.Assets.soundMergeRight.play(100.0f);
						break;
					}
				}
			}
		}		
	}

	@Override
	public void present(float deltaTime) {
		drawBackground(deltaTime);
		drawMatrix(deltaTime);
		drawDebugInfo(deltaTime);
	}
	
	private void drawBackground(float deltaTime){
		game.getGraphics().clean(Color.WHITE);		
	}

	private void drawMatrix(float deltaTime){
		String digstr;
		this.textPaint.setColor(Color.RED);
		this.textPaint.setTextSize(30);
		int x,y;
		int[][] m = this.matrix.get_matrix();
		for(x=0; x < m.length; x++){
			for(y=0; y < m.length; y++){
				if (m[y][x] == 0){
					continue;
				}
				digstr = Integer.toString(m[y][x]);
				this.textPaint.getTextBounds(digstr, 0, digstr.length(), this.boundRect);
				game.getGraphics().drawText(Integer.toString(m[y][x]), 
						x * 120 + 60 - (this.boundRect.right - this.boundRect.left)/2, 
						y * 120 + 60 + (this.boundRect.bottom - this.boundRect.top)/2);
			}
		}		
	}
	
	private void drawDebugInfo(float deltaTime){
		if(debug){
			game.getGraphics().drawText(this.directionText, 100, 200);
		}		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		this.processed = false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
