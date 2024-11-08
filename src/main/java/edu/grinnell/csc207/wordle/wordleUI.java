package edu.grinnell.csc207.wordle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Scanner;

import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.wordle.wordleBoard;


/**
 * The basic text based interface behind the wordle game.
 * 
 * @author Myles Bohrer-Purnell
 * @author Sebastian Manza
 */
public class wordleUI {

  /**
   * Print the instructions.
   *
   * @param pen The printwriter used to print the instructions.
   */
  public static void printInstructions(PrintWriter pen) {
    pen.println("""
        1. Enter a length of word between 2-12 letters.
        2. Enter a maximum number of guesses for a word.
        3. Enter your starting word:
          If the letter has (G) after it, it is in the word and is in the correct spot.
          If the letter has (Y) after it, it is in the word, but in the incorrect spot.
          Otherwise the letter is not in the word.
          Note that yellows will display multiple times even if there is only one
          occurence of the letter in the word.
        4. With that knowledge, enter your next guess.
        """);
  } // printInstructions(PrintWriter)

  /**
   * Print the results of the game.
   *
   * @param pen What to use for printing.
   * @param board The game board at the end.
   */
  static void printResults(PrintWriter pen, wordleBoard board, boolean winCon, int numGuess,
      String finWord) {
    if (winCon) {
      pen.println("Congratulations!");
      pen.println("You guessed the word " + finWord + " in " + numGuess + " guesses!");
    } else {
      pen.println("You lose!");
      pen.println("The word was " + finWord);
    } // if/else
  } // printResults

  public static void main(String[] args) throws Exception {
    String numGuesses;
    boolean winCon = false;
    Scanner eyes = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    String curWord = "";
    // current guess of the user
    int curGuess = 0;

    // print starting instructions
    printInstructions(pen);

    /* Prompt user for length and number of guesses */
    pen.println("Enter the length of your word:");
    String wordLength = eyes.nextLine();
    WordList wordList = new WordList("words.txt", Integer.parseInt(wordLength));

    String finWord = wordList.getRandWord();
    pen.println("Enter the maximum limit of guesses:");

    numGuesses = eyes.nextLine();
    int numGuessesInt = Integer.parseInt(numGuesses);
    wordleBoard currentBoard = new wordleBoard(wordList.wordLength(), numGuessesInt);

    /* Repeatedly check the users most recent entry and print the result. */
    while ((!curWord.equals(finWord)) && (curGuess < numGuessesInt)) {
      currentBoard.printBoard(pen);
      while (true) {
        pen.println("Enter your guess:");
        curWord = eyes.nextLine();
        if (curWord.length() != wordList.wordLength()) {
          System.err.println("Word is incorrect length. Please enter a word of "
              + wordList.wordLength() + " characters.");
        } else if (!wordList.inList(curWord)) {
          System.err.println(curWord + " is not a valid word in the word list.");
        } else {
          break;
        } // if/else
      } // while
      currentBoard.add(curWord, finWord, curGuess);
      curGuess += 1;
    } // whiles
    if (curWord.equals(finWord)) {
      winCon = true;
    } // if
    currentBoard.printBoard(pen);
    printResults(pen, currentBoard, winCon, curGuess, finWord);
  } // main

} // class wordleUI
