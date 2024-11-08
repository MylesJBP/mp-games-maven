package edu.grinnell.csc207.wordle;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

/**
 * Deals with the underlying storage of the wordle board.
 *
 * @author Myles Bohrer-Purnell
 * @author Sebastian Manza
 */
public class WordleBoard {

  /** The game board matrix. */
  MatrixV0 board;

  /**
   * Creates the wordBoard as a matrix.
   * @param wordLength
   * @param numGuesses
   */
  public WordleBoard(int wordLength, int numGuesses) {
    this.board = new MatrixV0<String>(wordLength, numGuesses, " ");
  } // wordleBoard

  /**
   * Prints the current board state.
   *
   * @param pen
   */
  public void printBoard(PrintWriter pen) {
    Matrix.print(pen, this.board);
  } // printBoard

  /**
   * Adds the letters to the board with the correct correctness modifyers.
   * @param curGuess // the guess to be checked
   * @param finWord // the word that is trying to be guessed
   * @param moveNum // the current guess number
   */
  public void add(String curGuess, String finWord, int moveNum) {
    String[] resultLetters = new String[finWord.length()];

    /* Find the yellow letters */
    for (int i = 0; i < finWord.length(); i++) {
      for (int j = 0; j < finWord.length(); j++) {
        if (curGuess.charAt(i) == finWord.charAt(j)) {
          resultLetters[i] = curGuess.charAt(i) + "(Y)";
          break;
        } else {
          resultLetters[i] = curGuess.charAt(i) + "";
        } // if/else
      } // for
      if (curGuess.charAt(i) == finWord.charAt(i)) {
        resultLetters[i] = curGuess.charAt(i) + "(G)";
      } // if
    } // for
    for (int i = 0; i < curGuess.length(); i++) {
      this.board.set(moveNum, i, resultLetters[i]);
    } // for
  } // add
} // WordleBoards
