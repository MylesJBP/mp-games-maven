package edu.grinnell.csc207.wordle;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

/**
 * Creates the word list based off a larger list of all possible sized words.
 *
 * @author Myles Bohrer-Purnell
 * @author Sebastian Manza
 */

public class WordList {

  /** The underlying storage list of words. */
  String[] wordList = new String[180000];
  /** The size of the word list. */
  int size = 0;
  /** The length of the word to be guessed. */
  int wordLength;
  /** A randomly generated word. */
  String randWord;

  /**
   * Creates a new WordList object that stores a list of lengthWord length words.
   *
   * @param file The input file
   * @param lengthWord The length of the word
   * @throws FileNotFoundException if the file does not exist
   * @throws IOException if catches an error in reading
   */
  public WordList(String file, int lengthWord) throws FileNotFoundException, IOException {
    BufferedReader fileReader = new BufferedReader(new FileReader("words.txt"));
    this.wordLength = lengthWord;
    String word;
    try {
      for (int i = 0; (word = fileReader.readLine()) != null; i++) {
        if (word.length() == this.wordLength) {
          this.wordList[this.size] = word;
          this.size++;
        } // if
      } // for
    } catch (IOException e) {
      throw new IOException("Had some trouble reading input.");
    } // try/catch
    Random rand = new Random();
    int random = rand.nextInt(this.size);
    this.randWord = this.wordList[random];
    fileReader.close();
  } // WordList

  /**
   * Gets the word list.
   *
   * @return the array of words
   */
  public String[] getWordList() {
    return this.wordList;
  } // getWordList()

  /**
   * Returns a random word from the word list.
   *
   * @return a random word
   */
  public String getRandWord() {
    return this.randWord;
  } // getRandWord

  /**
   * Returns the length of the word list.
   *
   * @return the word list size
   */
  public int getWordListSize() {
    return this.size;
  } // getWordListSize

  /**
   * Returns whether a word is in the word list.
   *
   * @param word The word we are checking for
   * @return true if the word is in the list, else false
   */
  public boolean inList(String word) {
    for (String words : this.wordList) {
      if (word.equals(words)) {
        return true;
      } // if
    } // for
    return false;
  } // inList(word)

  /**
   * returns the length of the words in the list.
   *
   * @return an integer length of word
   */
  public int wordLength() {
    return this.wordLength;
  } // wordLength

} // class Wordle
