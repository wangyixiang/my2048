package xiang.yi.wang.simpleframework.myimple;

import android.media.SoundPool;
import xiang.yi.wang.simpleframework.Sound;

public class MyAndroidSound implements Sound {

	int soundID;
	SoundPool soundPool;

	public MyAndroidSound(int soundID, SoundPool soundPool) {
		this.soundID = soundID;
		this.soundPool = soundPool;
	}

	public void play(float volume) {
		this.soundPool.play(this.soundID, volume, volume, 0, 0, 1);
	}

	@Override
	public void dispose() {
		this.soundPool.unload(this.soundID);
	}

}
