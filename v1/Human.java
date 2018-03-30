/**
 * Human.java
 * This class extends Player class to be a human player of TicTacToe.
 *
 * @author Justin Dowell
 * */
import java.util.Scanner;

public class Human extends Player
{
   /**
    * Contructor
    * */
   Human( SpaceStates playerToken )
   {
      super( playerToken );
   }

   /**
    * getMove
    *    Questions player to input a valid choice, checks the choice, and
    * returns the valid choice, or continues to question until receives valid
    * choice. SpaceStates[][] gb
    * */
   @Override
   public int getMove()
   {
      // variables
      int rv = 0;
      Scanner input = new Scanner( System.in );
      boolean valid = false;

      // loop until valid input from player
      while( !valid )
      {
         System.out.print( " Select a space 1-9." );
         rv = input.nextInt();

         // check validity
         if( TicTacToe.checkSpace( rv ) == SpaceStates.EMPTY )
         {
            valid = true;
         }

      }  // end validator loop

      // return valid input
      return rv;

   } // end getMove

}  // end class