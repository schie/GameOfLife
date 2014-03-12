package com.example.csci_5370_assignment_4;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class GameView extends View {

	private Paint 		paint = new Paint();
	private  int 		WIDTH = 55, 
						HEIGHT = 55;
	
	private Cell[][] 	cells = new Cell[WIDTH][HEIGHT];

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
			for(int x = 0; x < WIDTH; x++) 
				for(int y= 0 ; y < HEIGHT; y++)
					cells[x][y] = new Cell(x, y, 20, 20);
			Random rand = new Random();
			for(int i = 0; i < WIDTH*HEIGHT/2; i++) {
				int x = rand.nextInt(WIDTH);
				int y = rand.nextInt(HEIGHT);
				Cell cell = cells[x][y];
				if(cell.isAlive()) 
					i--;
				else 
					cell.resurrect();
			}
	}
	
	public Cell[][] getCells(){
		return cells;
	}

	public void setCells(Cell[][]cells){
		this.cells = cells;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for(Cell[] row : cells) 
			for(Cell cell : row) 
				cell.paint(paint, canvas);
	}
	

	
}
