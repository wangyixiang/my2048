package xiang.yi.wang.simpleframework.myimple;

import xiang.yi.wang.simpleframework.Audio;
import xiang.yi.wang.simpleframework.FileIO;
import xiang.yi.wang.simpleframework.Game;
import xiang.yi.wang.simpleframework.Graphics;
import xiang.yi.wang.simpleframework.Input;
import xiang.yi.wang.simpleframework.Screen;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class MyAndroidGame extends Activity implements Game {
	MyFastRenderView renderView;
	MyAndroidGraphics graphics;
	Input input;
	Screen screen;
	Audio audio;
	WakeLock wakeLock;
	
	private boolean enableMusic = true;
	
	public boolean isEnableMusic() {
		return enableMusic;
	}

	public void enableMusic() {
		this.enableMusic = true;
	}

	public void disableMusic() {
		this.enableMusic = false;
	}

	private boolean enableSound = true;

	public boolean isEnableSound() {
		return enableSound;
	}

	public void enableSound(){
		this.enableSound = true;
	}
	
	public void disableSound() {
		this.enableSound = false;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		int frameBufferWidth = 480;
		int frameBufferHeight = 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, 
				frameBufferHeight, Config.RGB_565);
		
		float scaleX = (float) frameBufferWidth
				/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight
				/ getWindowManager().getDefaultDisplay().getHeight();
		
		this.renderView = new MyFastRenderView(this, frameBuffer);
		this.graphics = new MyAndroidGraphics(frameBuffer);
		this.input = new MyAndrioidInput(this, renderView, scaleX, scaleY);
		this.screen = getStartScreen();
		this.audio = new MyAndroidAudio(this);
		setContentView(renderView);
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "YXGame");
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	@Override
	public void onPause(){
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		if (isFinishing()){
			screen.dispose();
		}
	}
	
	@Override
	public Input getInput() {
		// TODO Auto-generated method stub
		return this.input;
	}

	@Override
	public FileIO getFileIO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics getGraphics() {
		return this.graphics;
	}

	@Override
	public Audio getAudio() {
		return this.audio;
	}

	@Override
	public void setScreen(Screen screen) {
		if (screen == null) {
			throw new IllegalArgumentException("null screen argument");
		}
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public Screen getStartScreen() {
		return null;
	}

}
