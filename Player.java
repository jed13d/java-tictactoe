/**
 * Player.java
 * This class is a base class for Human and Computer derived classes.
 *
 * @author Justin Dowell
 * */

public abstract class Player
{
   /**
    * The game token used by the player.
    * */
   private final SpaceStates gameToken;

   /**
    * Contructor
    * */
   public Player( SpaceStates playerToken )
   {
      this.gameToken = playerToken;
   }

   /**
    * getMove
    *    Abstract method to be overwritten by derived classes.  SpaceStates[][] gb
    * */
   public abstract int getMove();

   /**
    * getOpToken
    *    Returns other gameToken
    * */
   public SpaceStates getOpToken()
   {
      if( gameToken == SpaceStates.X )
         return SpaceStates.O;
      else
         return SpaceStates.X;
   }

   /**
    * getToken
    *    Returns the gameToken used by the player.
    * */
   public SpaceStates getToken()
   {
      return gameToken;
   }
}  // end class