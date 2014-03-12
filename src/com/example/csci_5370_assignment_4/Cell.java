package com.example.csci_5370_assignment_4;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.RectF;

public class Cell {

	private Rect outlineRect;
	private RectF circle;
	
	private boolean isAlive;
	
	private int 	WIDTH = 50, 
					HEIGHT = 50,
					x, y;
	private int cellColor, 
				defaultColor = 0xffffffff;
	
	public Cell(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		Random rnd = new Random();
		this.cellColor = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
		WIDTH = width;
		HEIGHT = height;
		outlineRect = new Rect(	x * WIDTH + 1, 
								y * HEIGHT + 1,
								x * WIDTH + WIDTH, 
								y * HEIGHT + HEIGHT);
		circle = new RectF(	x * WIDTH + 1, 
							y * HEIGHT + 1, 
							x * WIDTH + WIDTH - 1, 
							y * HEIGHT + HEIGHT - 1);
	}

	public Cell(Cell cell){
		isAlive = cell.isAlive();
		WIDTH = cell.getWidth();
		HEIGHT = cell.getHeight();
		x = cell.getX();
		y = cell.getY();
		outlineRect = new Rect(	x * WIDTH + 1, 
								y * HEIGHT + 1,
								x * WIDTH + WIDTH, 
								y * HEIGHT + HEIGHT);
		circle = new RectF(	x * WIDTH + 1, 
							y * HEIGHT + 1, 
							x * WIDTH + WIDTH - 1, 
							y * HEIGHT + HEIGHT - 1);
		Random rnd = new Random();
		this.cellColor = Color.argb(255, rnd.nextInt(245) + 10, rnd.nextInt(245) + 10, rnd.nextInt(245) + 10);
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}
	
	public void resurrect(int occupantColor) {
		isAlive = true;
		this.cellColor = occupantColor;
	}
	
	public void resurrect() {
		isAlive = true;
	}
	
	public void die() {
		isAlive = false;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void paint(Paint paint, Canvas canvas) {
		paint.setColor(0xff000000);
		paint.setStyle(Style.STROKE);
		canvas.drawRect(outlineRect, paint);
		
		paint.setStyle(Style.FILL);
		if (isAlive) {
			paint.setColor(cellColor);
			canvas.drawOval(circle, paint);
		}
		
		
		
	}
	
}
