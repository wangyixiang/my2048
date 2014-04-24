package xiang.yi.wang.simpleframework.myimple;

import java.io.IOException;

import xiang.yi.wang.simpleframework.Music;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MyAndroidMusic implements Music, OnCompletionListener {
	MediaPlayer mediaPlayer;
	boolean stopped = true;

	public MyAndroidMusic(AssetFileDescriptor assetDescriptor) {
		this.mediaPlayer = new MediaPlayer();
		try {
			this.mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
					assetDescriptor.getStartOffset(),
					assetDescriptor.getLength());
			this.mediaPlayer.prepare();
			this.mediaPlayer.setOnCompletionListener(this);
			this.stopped = false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Loading Music Failed.");
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		this.stopped = true;
	}

	@Override
	public void play() {
		if (this.mediaPlayer.isPlaying()) {
			return;
		}
		if (this.stopped) {
			try {
				this.mediaPlayer.prepare();
				this.stopped = false;
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		this.mediaPlayer.start();
	}

	@Override
	public void stop() {
		this.mediaPlayer.stop();
		this.stopped = true;
	}

	@Override
	public void pause() {
		this.mediaPlayer.pause();
	}

	@Override
	public void setLooping(boolean looping) {
		this.mediaPlayer.setLooping(looping);
	}

	@Override
	public void setVolume(float volume) {
		this.mediaPlayer.setVolume(volume, volume);
	}

	@Override
	public boolean isPlaying() {
		return this.mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStopped() {
		return this.stopped;
	}

	@Override
	public boolean isLooping() {
		return this.mediaPlayer.isLooping();
	}

	@Override
	public void dispose() {
		if (this.isPlaying()){
			this.stop();
		}
		this.mediaPlayer.release();
	}

}
