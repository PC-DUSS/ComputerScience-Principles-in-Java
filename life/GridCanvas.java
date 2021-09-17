import java.awt.Canvas;

/**
 * 2D grid representation as the Canvas for GoL.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/09/17
 * @version 1.0
 * */
public class GridCanvas extends Canvas {
  private Cell[][] array;
  
  public GridCanvas(int rows, int columns, int size) {
    array = new Cell[rows][columns];
    // Now populate the array
    for (int lRow = 0; lRow < rows; lRow++) {
      int y = lRow * size;
      for (int lColumn = 0; lColumn < columns; lColumn++) {
	int x = lColumn * size;
	array[lRow][lColumn] = new Cell(x, y, size);
      }
    }
    
    // Set the canvas size in format (width, height)
    this.setSize(columns * size, rows * size);
  }
}
