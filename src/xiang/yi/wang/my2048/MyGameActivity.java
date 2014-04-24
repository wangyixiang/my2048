package xiang.yi.wang.my2048;

import android.os.Bundle;
import xiang.yi.wang.simpleframework.Screen;
import xiang.yi.wang.simpleframework.Sound;
import xiang.yi.wang.simpleframework.myimple.MyAndroidAudio;
import xiang.yi.wang.simpleframework.myimple.MyAndroidGame;

public class MyGameActivity extends MyAndroidGame {
	public static class Assets {
		public static Sound soundMergeUp;
		public static Sound soundMergeDown;
		public static Sound soundMergeLeft;
		public static Sound soundMergeRight;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Assets.soundMergeUp = this.getAudio().newSound("wang(female).ogg");
		Assets.soundMergeDown = this.getAudio().newSound("ba(female).ogg");
		Assets.soundMergeLeft = this.getAudio().newSound("yang(female).ogg");
		Assets.soundMergeRight = this.getAudio().newSound("yu(female).ogg");
	}


	@Override
	public Screen getStartScreen(){
		return new MyMainScreenTextEdition(this);
	}
}
