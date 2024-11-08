package edu.grinnell.csc207.wordle;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.ArrayUtils;



/**
 * The basic text based interface behind the wordle game.
 *
 * @author Myles Bohrer-Purnell
 * @author Sebastian Manza
 */
public class WordleUI {

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
   * @param winCon If the win was won or lost.
   * @param numGuess THe number of guesses so far.
   * @param finWord The correct word.
   */
  static void printResults(PrintWriter pen, WordleBoard board, boolean winCon, int numGuess,
      String finWord) {
    if (winCon) {
      pen.println("Congratulations!");
      pen.println("You guessed the word " + finWord + " in " + numGuess + " guesses!");
    } else {
      pen.println("You lose!");
      pen.println("The word was " + finWord);
    } // if/else
  } // printResults

  /**
   * Main function.
   * @param args The args
   * @throws Exception if file is not found or if file is not read properly.
   */
  public static void main(String[] args) throws Exception {
    String numGuesses = "";
    String[] validLen = new String[] {"2", "3", "4", "5", "6", "7", "8", "9",
                                      "10", "11", "12"};
    boolean winCon = false;
    Scanner eyes = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    String curWord = "";
    String wordLength = "";
    boolean validNumGuesses = false;
    int numGuessesInt = 0;
    // current guess of the user
    int curGuess = 0;

    // print starting instructions
    printInstructions(pen);

    /* Prompt user for length and number of guesses */
    do {
      pen.println("Enter the length of your word:");
      wordLength = eyes.nextLine();
      if (!ArrayUtils.arrayContainsCI(validLen, wordLength)) {
        pen.println("Incorrect word length. Please enter a number from 2-12");
      } // if
    } while (!ArrayUtils.arrayContainsCI(validLen, wordLength));

    WordList wordList = new WordList("words.txt", Integer.parseInt(wordLength));
    String finWord = wordList.getRandWord();
    while (!validNumGuesses) {
      try {
        pen.println("Enter the maximum limit of guesses:");
        numGuesses = eyes.nextLine();
        numGuessesInt = Integer.parseInt(numGuesses);
        validNumGuesses = true;
      } catch (NumberFormatException nfe) {
        pen.println("Incorrect Input: Please enter a number.");
      } // try/catch
    } // while
    WordleBoard currentBoard = new WordleBoard(wordList.wordLength(), numGuessesInt);

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
