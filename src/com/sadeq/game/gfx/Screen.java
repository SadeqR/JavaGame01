package com.sadeq.game.gfx;

public class Screen {
	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH-1;
	
	public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
	public int[] colours = new int[MAP_WIDTH * MAP_WIDTH * 4];
	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	
	public SpriteSheet sheet;
	
	public Screen(int width,int height, SpriteSheet sheet) {
		this.width=width;
		this.height=height;
		this.sheet=sheet;
		
		for(int i=0; i<MAP_WIDTH*MAP_WIDTH; i++) {
			colours[i*4+0] = 0xff00ff;
			colours[i*4+0] = 0x00ffff;
			colours[i*4+0] = 0xffff00;
			colours[i*4+0] = 0xffffff;
		}
	}
	
	public void render(int[] pixels, int offset, int row) {
		
	}
}
