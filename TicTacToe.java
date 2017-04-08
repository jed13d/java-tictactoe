/**
 * TicTacToe.java
 * This class contains the main method for running the tic-tac-toe game,
 * thus combining all the other required classes together.
 *
 * @author Justin Dowell
 * */
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe
{
   /**
    * Flag for game loop.
    * */
   private static boolean gameEnd = false;

   /**
    * Counter for number of game turns passed.
    * (gameTurn % 2) is player2's turn.
    * */
   private static int gameTurn = 0;

   /**
    * Length of one side of gameBoard.
    * */
   private static final int GBSIZE = 3;

   /**
    * Players' choice for placing game token
    * */
   private static int playerMove = 0;

   /**
    * Array for holding game players in polymorphic manner.
    * */
   private static Player[] players = new Player[2];

   /**
    * Array for keeping gameBoard state.
    * */
   private static SpaceStates[][] gameState = new SpaceStates[GBSIZE][GBSIZE];

   /**
    * main function
    * command line arguments format:
    *    java TicTacToe [-c [1|2] [-a]]
    * */
   public static void main( String[] args )
   {
      // initialize players
      if( args.length == 0 )
      {
         players[0] = new Human( SpaceStates.O );
         players[1] = new Human( SpaceStates.X );
      }
      else if( args.length == 1 )
      {
         if( args[0].equals( "-c" ) )
         {
            players[0] = new Computer( SpaceStates.O );
            players[1] = new Computer( SpaceStates.X );
         }
         else
            gameEnd = true;
      }
      else if( args.length == 2 )
      {
         if( args[0].equals( "-c" ) && args[1].equals( "1" ) )
         {
            players[0] = new Human( SpaceStates.O );
            players[1] = new Computer( SpaceStates.X );
         }
         else if( args[0].equals( "-c" ) && args[1].equals( "2" ) )
         {
            players[0] = new Computer( SpaceStates.O );
            players[1] = new Human( SpaceStates.X );
         }
         else
            gameEnd = true;
      }
      else
      {
      }

      // initialize gameBoard
      clearGameBoard();

      // print game board
      printGameBoard();

      while( !gameEnd )
      {
         // increment gameTurn;
         gameTurn++;

         // get player move
         playerMove = players[ (gameTurn % 2) ].getMove();

         // set player's move
         setMove( playerMove, players[ (gameTurn % 2) ].getToken() );

         // print game board
         printGameBoard();

         // set gameEnd
         if( checkForWinner() == players[0].getToken() )
         {
            System.out.println( "Player 2 wins!" );
            gameEnd = true;
         }
         else if( checkForWinner() == players[1].getToken() )
         {
            System.out.println( "Player 1 wins!" );
            gameEnd = true;
         }
         else if(( gameTurn == 9 ) && ( checkForWinner() == SpaceStates.NULL ))
         {
            System.out.println( "Game is a tie." );
            gameEnd = true;
         }
      }

   }  // end main

   /**
    * checkForWinner
    * This method checks for any variation of 3 in a row.
    * @return EMPTY if no winner is discovered.
    * @return X_or_O for the winner discovered.
    * */
   private static SpaceStates checkForWinner()
   {
      /*    some pseudo-code
         if gameTurn < 5, there cannot be a winner
         check each position for a non-empty position
         when all positions checked, done
         when non-empty position found, begin recursive search at position
      */
      // if gameTurn < 5, there cannot be a winner
      if( gameTurn < 5 )
      {
         return SpaceStates.NULL;
      }

      /*
       * since there are only 8 ways to win with a 3x3 game board,
       *  this area will check for each one.
       * short-circuiting is used to reduce calculation time
       * */
      else
      {
         // top row
         if(( checkSpace( 1 ) == checkSpace( 2 ) ) &&
            ( checkSpace( 1 ) == checkSpace( 3 ) ))
         {
            return checkSpace( 1 );
         }
         // middle row
         else if(( checkSpace( 4 ) == checkSpace( 5 ) ) &&
                 ( checkSpace( 4 ) == checkSpace( 6 ) ))
         {
            return checkSpace( 4 );
         }
         // bottom row
         else if(( checkSpace( 7 ) == checkSpace( 8 ) ) &&
                 ( checkSpace( 7 ) == checkSpace( 9 ) ))
         {
            return checkSpace( 7 );
         }
         // first column
         else if(( checkSpace( 1 ) == checkSpace( 4 ) ) &&
                 ( checkSpace( 1 ) == checkSpace( 7 ) ))
         {
            return checkSpace( 1 );
         }
         // second column
         else if(( checkSpace( 2 ) == checkSpace( 5 ) ) &&
                 ( checkSpace( 2 ) == checkSpace( 8 ) ))
         {
            return checkSpace( 2 );
         }
         // third column
         else if(( checkSpace( 3 ) == checkSpace( 6 ) ) &&
                 ( checkSpace( 3 ) == checkSpace( 9 ) ))
         {
            return checkSpace( 3 );
         }
         // first diagonal
         else if(( checkSpace( 1 ) == checkSpace( 5 ) ) &&
                 ( checkSpace( 1 ) == checkSpace( 9 ) ))
         {
            return checkSpace( 1 );
         }
         // second diagonal
         else if(( checkSpace( 3 ) == checkSpace( 5 ) ) &&
                 ( checkSpace( 3 ) == checkSpace( 7 ) ))
         {
            return checkSpace( 3 );
         }
         // no winner found
         else
         {
            return SpaceStates.NULL;
         }
      }  // end checking possible winners

   } // end checkForWinner

   /**
    * checkSpace
    * @param pos Position of the game board to check.
    * @return SpaceStates The contents of the position.
    * */
   public static SpaceStates checkSpace( int pos )
   {
      // value returned found through switch
      switch( pos )
      {
         case 1:
            return gameState[0][0];
         case 2:
            return gameState[0][1];
         case 3:
            return gameState[0][2];
         case 4:
            return gameState[1][0];
         case 5:
            return gameState[1][1];
         case 6:
            return gameState[1][2];
         case 7:
            return gameState[2][0];
         case 8:
            return gameState[2][1];
         case 9:
            return gameState[2][2];
         default :
            return SpaceStates.NULL;
      }  // end switch

   } // end checkSpace

   /**
    * clearGameBoard
    * This method puts each position of the game board into an EMPTY state.
    * */
   private static void clearGameBoard()
   {
      // make game board clear state
      for( int i = 0; i < 3; i++ )
      {
         for( int j = 0; j < 3; j++ )
         {
            gameState[i][j] = SpaceStates.EMPTY;
         }
      }
   }  // end clearGameBoard

   /**
    * getGameTurn
    * @return gameTurn int
    * */
   public static int getGameTurn()
   {
      return gameTurn;
   }  // end getGameTurn

   /**
    * getSpaceString
    * This method returns the string equivalent of the state in a space.
    * Used by printGameBoard.
    * */
   private static String getSpaceString( SpaceStates state )
   {
      // string return value
      String rv;

      // conditionally set rv
      if( state == SpaceStates.X )
         rv = "X";
      else if( state == SpaceStates.O )
         rv = "O";
      else
         rv = " ";

      // return the return value
      return rv;
   }  // end getSpaceString

   /**
    * printGameBoard
    * This method outputs the current gameBoard state.
    * */
   private static void printGameBoard()
   {
      System.out.println();
      System.out.println( " " + getSpaceString( gameState[0][0] ) + "|" + getSpaceString( gameState[0][1] ) + "|" + getSpaceString( gameState[0][2] ) );
      System.out.println( " -----" );
      System.out.println( " " + getSpaceString( gameState[1][0] ) + "|" + getSpaceString( gameState[1][1] ) + "|" + getSpaceString( gameState[1][2] ) );
      System.out.println( " -----" );
      System.out.println( " " + getSpaceString( gameState[2][0] ) + "|" + getSpaceString( gameState[2][1] ) + "|" + getSpaceString( gameState[2][2] ) );
      System.out.println();
   }  // end printGameBoard

   /**
    * setMove
    * This method completes the player's choice for movement.
    * */
   private static void setMove( int move, SpaceStates token )
   {
      // error checking already completed, so just completing move
      switch( move )
      {
            case 1:
               gameState[0][0] = token;
               break;
            case 2:
               gameState[0][1] = token;
               break;
            case 3:
               gameState[0][2] = token;
               break;
            case 4:
               gameState[1][0] = token;
               break;
            case 5:
               gameState[1][1] = token;
               break;
            case 6:
               gameState[1][2] = token;
               break;
            case 7:
               gameState[2][0] = token;
               break;
            case 8:
               gameState[2][1] = token;
               break;
            case 9:
               gameState[2][2] = token;
               break;
            default:
               break;
      }  // end switch

   }  // end setMove

}  // end class