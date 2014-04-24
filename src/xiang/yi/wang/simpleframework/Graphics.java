package xiang.yi.wang.simpleframework;

public interface Graphics {
	public static enum PixmapFormat {
		ARGB888, ARGB4444, RGB565
	}
	
	public Pixmap newPixmap(String fileName, PixmapFormat format);
	
	public void clean(int color);
	public void drawPixel(int x, int y, int color);
	public void drawLine(int x, int y, int x2, int y2, int color);
	public void drawRect(int x, int y, int width, int height, int color);
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, 
			int srcWidth, int srcHeight);
	public void drawPixmap(Pixmap pixmap, int x, int y);
	public void drawText(String text, float x, float y);
	public int getWidth();
	public int getHeight();


}
