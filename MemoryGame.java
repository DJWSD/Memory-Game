/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
import java.util.Random;

public class MemoryGame
{
  public static void main(String[] args) {

    int score = 0;
    int rounds = 0;
    boolean again = true;
    String[] memoryStrings = {"a", "b", "c"};

    //Creates the game board with 3 buttons and displays the string to remember in random places.
    MemoryGameGUI game = new MemoryGameGUI();
    game.createBoard(3, true);
  
      String[] randomOrder = {"", "", ""};

      Random r = new Random();

      for (int i = randomOrder.length-1; i >= 0; i--){
        int j = r.nextInt(i+1);

        String temp = memoryStrings[i];
        randomOrder[i] = memoryStrings[j];
        memoryStrings[i] = memoryStrings[j];
        memoryStrings[j] = temp;
      }
     
      // Determine if player's guess matches all elements of the random sequence after removing commas and spaces.
      // If it matches, increases score. Otherwise, try again.
      // Also gives the player the option to end the game, displaying their total correct out of # of rounds played.
      while(again){
        String guess = "";
        String answer = "";
        for (int k = 0; k < randomOrder.length; k++){
          answer += randomOrder[k];
        }
  
        guess = game.playSequence(randomOrder, 0.25);
        rounds++;
      
          guess = guess.replace(",", "");
          guess = guess.replace(" ", "");
          
          if(guess.equals(answer)){
            game.matched();
            score++;
            if(game.playAgain()){
            } else {
              again = false;
              game.showScore(score, rounds);
              game.quit();
            }
          } else {
            game.tryAgain();
            if(game.playAgain()){
            } else {
              again = false;
              game.showScore(score, rounds);
              game.quit();
            }
          }
      }
  }
}