package edu.grinnell.csc207.wordle;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.*;

/**
 * Deals with the underlying storage of the wordle board.
 * @author Myles Bohrer-Purnell
 * @author Sebastian Manza
 */
public class wordleBoard {

  MatrixV0 board;

  /**
   * 
   * @param wordLength
   * @param numGuesses
   */
  public wordleBoard(int wordLength, int numGuesses) {
    this.board = new MatrixV0<>(wordLength, numGuesses);
  } //wordleBoard

  /**
   * Prints the current board state
   * @param pen
   */
  public void printBoard(PrintWriter pen) {
    Matrix.print(pen, this.board);
  } //printBoard

  public void add() {
    
  }
  
}
