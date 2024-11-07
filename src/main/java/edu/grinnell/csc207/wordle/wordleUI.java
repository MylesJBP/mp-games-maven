package edu.grinnell.csc207.wordle;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.Matrix;

/**
 * The basic text based interface behind the wordle game.
 * @author Myles Bohrer-Purnell
 * @author Sebastian Manza
 */
public class wordleUI {

  /**
   * Print the instructions.
   *
   * @param pen
   *  The printwriter used to print the instructions.
   */
  public static void printInstructions(PrintWriter pen) {
    pen.println("""
                1. Enter a length of word between 5-7
                2. Enter a maximum number of guesses for a word.
                3. Enter your starting word:
                  If the letter is __, it is in the word and is in the correct spot.
                  If the letter is __, it is in the word, but in the incorrect spot.
                  Otherwise the letter is not in the word.
                4. With that knowledge, enter your next guess.
                """);
  } // printInstructions(PrintWriter)

  
  /**
   * Print the results of the game.
   *
   * @param pen
   *   What to use for printing.
   * @param board
   *   The game board at the end.
   */
  static void printResults(PrintWriter pen, Matrix<String> board) {
    pen.println(board.numResults());
  } // printResults
  
}
