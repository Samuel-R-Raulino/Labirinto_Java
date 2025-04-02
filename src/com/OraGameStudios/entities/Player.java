package com.OraGameStudios.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	public int x = 0;
	public int y = 0;
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 30, 30);
	}
	public void tick() {
		if(left) {
			x-=5;
		} 
		if(right) {
			x+=5;
		} 
		if(up) {
			y-=5;
		} 
		if(down) {
			y+=5;
		}
	}
}

