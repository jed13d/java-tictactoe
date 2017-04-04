/**
 * SpaceStates.java
 * This file is an enum type which defines the different states in
 * a single gamespace ... EMPTY, X, O.
 *
 * @author Justin Dowell
 * */

public enum SpaceStates
{
   // constants of enum type
   NULL( 0 ),
   EMPTY( 1 ),
   X( 2 ),
   O( 3 );

   // instance fields
   private final int someVal;

   // enum constructor
   SpaceStates( int arg )
   {
      this.someVal = arg;
   }

   // accessor
   public int getVal()
   {
      return someVal;
   }

}  // end enum SpaceStates