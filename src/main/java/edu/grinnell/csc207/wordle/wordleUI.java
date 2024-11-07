package edu.grinnell.csc207.wordle;

import java.io.PrintWriter;
import java.util.Scanner;
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

  public static void main(String[] args) {
    String wordLength;
    String numGuesses;
    Scanner eyes = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    String curWord;

    /* Prompt user for length and number of guesses */
    pen.println("Enter the length of your word:");
    wordLength = eyes.nextLine();
    int wordLengthInt = Integer.parseInt(wordLength);
    pen.println("Enter the maximum limit of guesses:");
    numGuesses = eyes.nextLine();
    int numGuessesInt = Integer.parseInt(numGuesses);
    wordleBoard currentBoard = new wordleBoard(wordLength, numGuesses);

    printInstructions(pen);

    /* Repeatedly check the users most recent entry and print the result. */
    while(gamenotFinished) {
      currentBoard.printBoard(pen);
      while(true) {
        pen.println("Enter your guess:");
        curWord = eyes.nextLine();
        if (curWord.length() != wordLengthInt) {
          System.err.println("Word is incorrect length. Please enter a word of" + wordLengthInt + " characters.");
        } else if(!curWord.inList()) {
          System.err.println(curWord + "is not a valid word.");
        } else {
          break;
        } //if/else
      } //while
      currentBoard.add(curWord);
    } // while

    printResults(pen);
  } //main

} // class wordleUI
