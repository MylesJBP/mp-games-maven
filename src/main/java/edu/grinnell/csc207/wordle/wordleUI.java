package edu.grinnell.csc207.wordle;

import java.io.PrintWriter;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Scanner;

import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.wordle.wordleBoard;


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
  static void printResults(PrintWriter pen, wordleBoard board, boolean winCon, int numGuess, String finWord) {
    if (winCon) {
      pen.println("Congratulation");
      pen.println("You guessed the word " + finWord + " in " + numGuess + " guesses!");
    } else {
      pen.println("You lose!");
      pen.println("The word was " + finWord);
    } // if/else
  } // printResults

  public static void main(String[] args) {
    String wordLength;
    String numGuesses;
    boolean winCon = false;
    Scanner eyes = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    String curWord;
    String wordIndex;
    // word that is randomly/selectively chosen to solve
    String finWord;
    // current guess of the user
    int curGuess = 0;
    String customed;

    /* Prompt user for length and number of guesses */
    pen.println("Enter the length of your word:");
    wordLength = eyes.nextLine();
    int wordLengthInt = Integer.parseInt(wordLength);
    pen.println("Enter the maximum limit of guesses:");
    numGuesses = eyes.nextLine();
    pen.println("Would you like a random word or an indexed word (Y/N)?: ");
    customed = eyes.nextLine();
    // need to check for correct message
    if (customed.equals("Y")) {
      pen.println("What word index do you want?: ");
      wordIndex = eyes.nextLine();
      // read file index and get word, need to check to make sure correct size and everything
    } else {
      // get random one from file
    } // if/else
    int numGuessesInt = Integer.parseInt(numGuesses);
    wordleBoard currentBoard = new wordleBoard(wordLengthInt, numGuessesInt);

    // need to set finWord to a random word of length wordLength
    // from our list of words.
    // we could also allow the user to select a word number
    // I also know we will probably have to shift some of this to the Wordle file

    // print starting instructions
    printInstructions(pen);

    /* Repeatedly check the users most recent entry and print the result. */
    while(gamenotFinished) {
      currentBoard.printBoard(pen);
      while(true) {
        pen.println("Enter your guess:");
        curWord = eyes.nextLine();
        if (curWord.length() != wordLengthInt) {
          System.err.println("Word is incorrect length. Please enter a word of" + wordLengthInt + " characters.");
        } else if(!inList(curWord)) {
          System.err.println(curWord + "is not a valid word.");
        } else {
          break;
        } //if/else
      } //while
      currentBoard.add(curWord, finWord, numGuessesInt);
      curGuess += 1;
    } // whiles
    printResults(pen, currentBoard, winCon, numGuessesInt, finWord);
  } //main

} // class wordleUI
