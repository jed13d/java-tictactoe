/**
 * Computer.java
 * This class extends Player class to be a computer ai player of TicTacToe.
 *
 * @author Justin Dowell
 * */
import java.security.SecureRandom;

public class Computer extends Player
{
   /**
    * Flag for advanced (hard) difficulty.
    * */
   private boolean advDif;

   /**
    * Array for randomly choosing space.
    * */
   private int[] choices = new int[8];

   /**
    * Secure randome number object.
    * */
   private SecureRandom randNum;

   /**
    * Constructor
    * */
   Computer( SpaceStates playerToken )
   {
      super( playerToken );

      advDif = false;
      clearChoices();
      randNum = new SecureRandom();
   }

   /**
    * getMove
    *    The AI strategy is as follows:
    *    - if a winning move is available, take it.
    *    - if an opponent is threatening a winning play, block it.
    *    - normal difficulty
    *       - if the center is available, take it.
    *       - else choose from remaining spaces randomly.
    *    - advanced difficulty
    *       - choose the space with smallest chance of leading to loss.
    * */
   @Override
   public int getMove()
   {
      // variables
      int rv = 0;
      int choiceCtr = 0;
      boolean foundMove = false;

      /*
       * find possible winning position
       * since there's a small number of possibilities, each is checked with
       *  short-circuiting used to reduce calculation time
       * */
      if( TicTacToe.checkSpace( 1 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 2 ) == getToken()) && (TicTacToe.checkSpace( 3 ) == getToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getToken()) && (TicTacToe.checkSpace( 9 ) == getToken())) ||
             ((TicTacToe.checkSpace( 4 ) == getToken()) && (TicTacToe.checkSpace( 7 ) == getToken())) )
         {
            return 1;
         }
      }
      if( TicTacToe.checkSpace( 2 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getToken()) && (TicTacToe.checkSpace( 3 ) == getToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getToken()) && (TicTacToe.checkSpace( 8 ) == getToken())) )
         {
            return 2;
         }
      }
      if( TicTacToe.checkSpace( 3 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getToken()) && (TicTacToe.checkSpace( 2 ) == getToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getToken()) && (TicTacToe.checkSpace( 7 ) == getToken())) ||
             ((TicTacToe.checkSpace( 6 ) == getToken()) && (TicTacToe.checkSpace( 9 ) == getToken())) )
         {
            return 3;
         }
      }
      if( TicTacToe.checkSpace( 4 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getToken()) && (TicTacToe.checkSpace( 7 ) == getToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getToken()) && (TicTacToe.checkSpace( 6 ) == getToken())) )
         {
            return 4;
         }
      }
      if( TicTacToe.checkSpace( 5 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getToken()) && (TicTacToe.checkSpace( 9 ) == getToken())) ||
             ((TicTacToe.checkSpace( 4 ) == getToken()) && (TicTacToe.checkSpace( 6 ) == getToken())) ||
             ((TicTacToe.checkSpace( 2 ) == getToken()) && (TicTacToe.checkSpace( 8 ) == getToken())) ||
             ((TicTacToe.checkSpace( 3 ) == getToken()) && (TicTacToe.checkSpace( 7 ) == getToken())) )
         {
            return 5;
         }
      }
      if( TicTacToe.checkSpace( 6 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 3 ) == getToken()) && (TicTacToe.checkSpace( 9 ) == getToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getToken()) && (TicTacToe.checkSpace( 4 ) == getToken())) )
         {
            return 6;
         }
      }
      if( TicTacToe.checkSpace( 7 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getToken()) && (TicTacToe.checkSpace( 4 ) == getToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getToken()) && (TicTacToe.checkSpace( 3 ) == getToken())) ||
             ((TicTacToe.checkSpace( 8 ) == getToken()) && (TicTacToe.checkSpace( 9 ) == getToken())) )
         {
            return 7;
         }
      }
      if( TicTacToe.checkSpace( 8 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 7 ) == getToken()) && (TicTacToe.checkSpace( 9 ) == getToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getToken()) && (TicTacToe.checkSpace( 2 ) == getToken())) )
         {
            return 8;
         }
      }
      if( TicTacToe.checkSpace( 9 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getToken()) && (TicTacToe.checkSpace( 5 ) == getToken())) ||
             ((TicTacToe.checkSpace( 6 ) == getToken()) && (TicTacToe.checkSpace( 3 ) == getToken())) ||
             ((TicTacToe.checkSpace( 7 ) == getToken()) && (TicTacToe.checkSpace( 8 ) == getToken())) )
         {
            return 9;
         }
      }
      // done checking if *self has a winning move

      /*
       * now to check if other player has a winning move to be blocked
       * if multiple exist, the first found will be targeted
       * */
      if( TicTacToe.checkSpace( 1 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 2 ) == getOpToken()) && (TicTacToe.checkSpace( 3 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getOpToken()) && (TicTacToe.checkSpace( 9 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 4 ) == getOpToken()) && (TicTacToe.checkSpace( 7 ) == getOpToken())) )
         {
            return 1;
         }
      }
      if( TicTacToe.checkSpace( 2 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getOpToken()) && (TicTacToe.checkSpace( 3 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getOpToken()) && (TicTacToe.checkSpace( 8 ) == getOpToken())) )
         {
            return 2;
         }
      }
      if( TicTacToe.checkSpace( 3 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getOpToken()) && (TicTacToe.checkSpace( 2 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getOpToken()) && (TicTacToe.checkSpace( 7 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 6 ) == getOpToken()) && (TicTacToe.checkSpace( 9 ) == getOpToken())) )
         {
            return 3;
         }
      }
      if( TicTacToe.checkSpace( 4 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getOpToken()) && (TicTacToe.checkSpace( 7 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getOpToken()) && (TicTacToe.checkSpace( 6 ) == getOpToken())) )
         {
            return 4;
         }
      }
      if( TicTacToe.checkSpace( 5 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getOpToken()) && (TicTacToe.checkSpace( 9 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 4 ) == getOpToken()) && (TicTacToe.checkSpace( 6 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 2 ) == getOpToken()) && (TicTacToe.checkSpace( 8 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 3 ) == getOpToken()) && (TicTacToe.checkSpace( 7 ) == getOpToken())) )
         {
            return 5;
         }
      }
      if( TicTacToe.checkSpace( 6 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 3 ) == getOpToken()) && (TicTacToe.checkSpace( 9 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getOpToken()) && (TicTacToe.checkSpace( 4 ) == getOpToken())) )
         {
            return 6;
         }
      }
      if( TicTacToe.checkSpace( 7 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getOpToken()) && (TicTacToe.checkSpace( 4 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getOpToken()) && (TicTacToe.checkSpace( 3 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 8 ) == getOpToken()) && (TicTacToe.checkSpace( 9 ) == getOpToken())) )
         {
            return 7;
         }
      }
      if( TicTacToe.checkSpace( 8 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 7 ) == getOpToken()) && (TicTacToe.checkSpace( 9 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 5 ) == getOpToken()) && (TicTacToe.checkSpace( 2 ) == getOpToken())) )
         {
            return 8;
         }
      }
      if( TicTacToe.checkSpace( 9 ) == SpaceStates.EMPTY )
      {
         if( ((TicTacToe.checkSpace( 1 ) == getOpToken()) && (TicTacToe.checkSpace( 5 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 6 ) == getOpToken()) && (TicTacToe.checkSpace( 3 ) == getOpToken())) ||
             ((TicTacToe.checkSpace( 7 ) == getOpToken()) && (TicTacToe.checkSpace( 8 ) == getOpToken())) )
         {
            return 9;
         }
      }

      // normal difficulty
      if( !advDif )
      {
         // if center free, take it
         if( TicTacToe.checkSpace( 5 ) == SpaceStates.EMPTY )
         {
            return 5;
         }

         // else, check remaining empty spaces, add to list, choose one at random
         else
         {
            // find all empty spaces
            for( int i = 1; i <= 9; i++ )
            {
               if( TicTacToe.checkSpace( i ) == SpaceStates.EMPTY )
               {
                  choices[ choiceCtr++ ] = i;
               }
            }

            // choose randomly from empty spaces recorded
            rv = choices[ randNum.nextInt( choiceCtr ) ];
         }
      }  // end normal difficulty

      // advanced difficulty
      else
      {
      }  // end advanced difficulty

      return rv;

   }  // end getMove

   /**
    * clearChoices
    * sets all ints in choices to 0.
    * */
   private void clearChoices()
   {
      for( int i = 0; i < 8; i++ )
      {
         choices[i] = 0;
      }
   }  // end clearChoices

}  // end class