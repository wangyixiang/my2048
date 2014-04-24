package xiang.yi.wang.simpleframework.myimple;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import xiang.yi.wang.simpleframework.Audio;
import xiang.yi.wang.simpleframework.Music;
import xiang.yi.wang.simpleframework.Sound;

public class MyAndroidAudio implements Audio {
	AssetManager assetManager;
	SoundPool soundPool;
	
	public MyAndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		assetManager = activity.getAssets();
		soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}

	@Override
	public Music newMusic(String fileName) {
		AssetFileDescriptor afd = null;
		try {
			afd = assetManager.openFd(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("new Music " + fileName + " failed.");
		}
		
		return new MyAndroidMusic(afd);
	}

	@Override
	public Sound newSound(String fileName) {
		int soundID = 0;
		AssetFileDescriptor afd = null;
		try{
			afd = assetManager.openFd(fileName);
			soundID = soundPool.load(afd, 0);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("new Sound " + fileName + " failed.");
		}
		return new MyAndroidSound(soundID, this.soundPool);
	}

}
