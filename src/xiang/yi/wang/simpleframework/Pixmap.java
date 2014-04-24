package xiang.yi.wang.simpleframework;

import xiang.yi.wang.simpleframework.Graphics.PixmapFormat;

public interface Pixmap {
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
