package edu.grinnell.csc207.wordle;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

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
    this.board = new MatrixV0<String>(wordLength, numGuesses, " ");
  } //wordleBoard

  /**
   * Prints the current board state
   * @param pen
   */
  public void printBoard(PrintWriter pen) {
    Matrix.print(pen, this.board);
  } //printBoard


  public void add(String curWord, String finWord, int moveNum) {
    char[] notLets = new char[curWord.length()];
    String[] resultLets = new String[curWord.length()];
    int curNot = 0;
    for (int iter = 1; iter < 3; iter++) {
      for (int i = 0; i < curWord.length(); i++) {
        // first iteration through letter, for green letters
        if (iter == 1) {
          // if the letters are equal, than make green
          if (curWord.charAt(i) == finWord.charAt(i)) {
            resultLets[i] = curWord.charAt(i) + "-G";
          } else {
            // if not, than add to a list for yellow checking
            notLets[curNot] = curWord.charAt(i);
          } // if/else
        // second iteration through letters, for yellow letters
        } else {
          // if it has not already been found as equal
          if (resultLets[i] != curWord.charAt(i) + "-G") {
            // check if the letter has been found, not green
            for (int inc = 0; inc < notLets.length; inc++) {
              if (curWord.charAt(i) == notLets[inc]) {
                resultLets[i] = curWord.charAt(i) + "-Y";
                notLets[inc] = ' '; // change in some way, removes the element from the list.
                break;
              } else if (inc == notLets.length-1) {
                // else, just add the word as is
                resultLets[i] = "" + curWord.charAt(i);
                break;
              } // if/else
            } // for
          } // if
        } // if/else
      } // for
    } // for
    // set the elements into the board for that guess row
    for (int i = 0; i < curWord.length(); i++) {
      this.board.set(moveNum, i, resultLets[i]);
    } // for
  } // add
} // wordleBoards
