package xiang.yi.wang.simpleframework.myimple;

import xiang.yi.wang.simpleframework.Graphics;
import xiang.yi.wang.simpleframework.Pixmap;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;

public class MyAndroidGraphics implements Graphics {
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Paint textPaint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();
	Rect boundRect = new Rect();

	public MyAndroidGraphics(Bitmap frameBuffer) {
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
		this.textPaint = new Paint();
		this.textPaint.setColor(Color.BLACK);
		this.textPaint.setTypeface(Typeface.SERIF);
		this.textPaint.setTextSize(30);
	}

	@Override
	public Pixmap newPixmap(String fileName, PixmapFormat format) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clean(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, 
				(color & 0xff00) >> 8, 
				(color & 0xff));

	}

	@Override
	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x1, y1, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWidth() {
		return frameBuffer.getWidth();
	}

	@Override
	public int getHeight() {
		return frameBuffer.getHeight();
	}
	
	@Override
	public void drawText(String text, float x, float y) {
		canvas.drawText(text, x, y, textPaint);
//		textPaint.getTextBounds(text, 0, text.length(), boundRect);
//		textPaint.setStyle(Style.STROKE);
//		canvas.drawRect(new Rect((int)x,(int)y-(boundRect.bottom - boundRect.top),(int)x+(boundRect.right - boundRect.left),(int)y), textPaint);;
	}

	public Paint getTextPaint(){
		return this.textPaint;
	}
}
