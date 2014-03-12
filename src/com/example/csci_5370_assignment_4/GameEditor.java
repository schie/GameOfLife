package com.example.csci_5370_assignment_4;

import android.graphics.Point;

/**
 * Conway's Game of Life
 * 	Rules:
 * 		1-	Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 * 		2-	Any live cell with two or three live neighbours lives on to the next generation.
 * 		3-	Any live cell with more than three live neighbours dies, as if by overcrowding.
 * 		4-	Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 * @author dustinschie
 *
 */
public class GameEditor {

	private Cell[][] cells;
	private int width;
	private int height;
	
	public GameEditor(Cell[][] cells) {
		this.cells = cells;
		width = cells.length;
		height = cells[0].length;
	}
	
	public Cell[][] getNextIteration(){
		Cell[][] newCells = new Cell[width][height];
		
		for (int i = 0; i < cells.length; i++) 
			for (int j = 0; j < cells[0].length; j++) {	
				Cell cell = new Cell(cells[i][j]);
				
				
				int numNeigh = getNumOfNeighbors(i, j);
				//	Any live cell...
				if (cell.isAlive()) {
					// rule 1 - 3
					if (numNeigh < 2 || numNeigh > 3)
						cell.die();
					
				}
				//	rule 4 (dead cell)
				else if (numNeigh == 3) 
						cell.resurrect();
				
				newCells[i][j] = new Cell(cell);
			}
		
		//	redefine old cells with newCells
		for (int i = 0; i < newCells.length; i++) 
			for (int j = 0; j < newCells[0].length; j++) 
				cells[i][j] = new Cell(newCells[i][j]);
		return cells;
	}
	
	public int getNumOfNeighbors(int x, int y){
		int neighborsAlive = 0;
		//	get neighbor points
		Point[] dirs = {new Point(x, y - 1),	//	n
						new Point(x - 1, y - 1),//	nw
						new Point(x - 1, y),	//	w
						new Point(x - 1, y + 1),//	sw
						new Point(x, y + 1),	//	s
						new Point(x + 1, y + 1),//	se
						new Point(x + 1, y),	//	e
						new Point(x + 1, y - 1)	//	ne
						};
	
		//	get number of neighbors
		for (int i = 0; i < dirs.length; i++) {
			Point p = dirs[i];
			if (p.x >= 0 && p.x < cells.length && p.y >= 0 && p.y < cells[0].length)
				if (cells[p.x][p.y].isAlive())
					neighborsAlive++;
		}
		
		return neighborsAlive;
	}

}
