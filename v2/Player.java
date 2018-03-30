/**
 * Instead of working with inheritence, I decided to include both human and
 * AI in a single class since the game is a simple one.
 * */
import java.util.ArrayList;
import java.util.EnumSet;

public class Player {

   // ----- Variables ===========================
   private final Constants.Token T;
   private final Boolean H;

   // ----- Constructors ========================
   private Player( Boolean h, Constants.Token t ) {
      H = h;
      T = t;
   }// ---

   // ----- Builders ============================
   public static Player newPlayer( Boolean human, Constants.Token token ) {
      Player rv = new Player( human, token );

      return rv;
   }// ---

   // ----- Private Methods =====================
   private Constants.Space findWinningMove( EnumSet< Constants.Space > e, EnumSet< Constants.Space > p ) {
      Constants.Space[] rv = new Constants.Space[1];
      rv[0] = null;

      e.forEach( es -> {
         switch( es ) {
            case ONE:
               if( (p.contains(Constants.Space.TWO) && p.contains(Constants.Space.THREE))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.NINE))
                  || (p.contains(Constants.Space.FOUR) && p.contains(Constants.Space.SEVEN)) ) {
                  rv[0] = es;
                  }
                  break;
            case TWO:
               if( (p.contains(Constants.Space.THREE) && p.contains(Constants.Space.ONE))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.EIGHT)) ) {
                  rv[0] = es;
                  }
                  break;
            case THREE:
               if( (p.contains(Constants.Space.TWO) && p.contains(Constants.Space.ONE))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.SEVEN))
                  || (p.contains(Constants.Space.SIX) && p.contains(Constants.Space.NINE)) ) {
                  rv[0] = es;
                  }
                  break;
            case FOUR:
               if( (p.contains(Constants.Space.SEVEN) && p.contains(Constants.Space.ONE))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.SIX)) ) {
                  rv[0] = es;
                  }
                  break;
            case FIVE:
               if( (p.contains(Constants.Space.ONE) && p.contains(Constants.Space.NINE))
                  || (p.contains(Constants.Space.TWO) && p.contains(Constants.Space.EIGHT))
                  || (p.contains(Constants.Space.THREE) && p.contains(Constants.Space.SEVEN))
                  || (p.contains(Constants.Space.FOUR) && p.contains(Constants.Space.SIX)) ) {
                  rv[0] = es;
                  }
                  break;
            case SIX:
               if( (p.contains(Constants.Space.THREE) && p.contains(Constants.Space.NINE))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.FOUR)) ) {
                  rv[0] = es;
                  }
                  break;
            case SEVEN:
               if( (p.contains(Constants.Space.ONE) && p.contains(Constants.Space.FOUR))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.THREE))
                  || (p.contains(Constants.Space.EIGHT) && p.contains(Constants.Space.NINE)) ) {
                  rv[0] = es;
                  }
                  break;
            case EIGHT:
               if( (p.contains(Constants.Space.SEVEN) && p.contains(Constants.Space.NINE))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.TWO)) ) {
                  rv[0] = es;
                  }
                  break;
            case NINE:
               if( (p.contains(Constants.Space.SEVEN) && p.contains(Constants.Space.EIGHT))
                  || (p.contains(Constants.Space.FIVE) && p.contains(Constants.Space.ONE))
                  || (p.contains(Constants.Space.THREE) && p.contains(Constants.Space.SIX)) ) {
                  rv[0] = es;
                  }
                  break;
         }
      });

      return rv[0];
   }// ---

   // ----- Public Methods ======================
   /**
    * AI for Computer Player
    *  1. Take available winning move.
    *  2. Block first discovered opponent winning move.
    *  3. Take center, FIVE, if available.
    *  4. Take random available spot.
    *
    * @param b the parent game baord
    * @return Space of selected move
    */
   public Constants.Space aiMove( GBoard b ) {
      Constants.Space rv = null;

      // gather all empties for selection
      EnumSet< Constants.Space > empties = EnumSet.copyOf( b.getSpacesByToken(Constants.Token.E) );

      // find self winning position, null if none, if there's enough tokens in play
      if( empties.size() < 7 ) {
         rv = findWinningMove( empties, EnumSet.copyOf(b.getSpacesByToken(T)) );
         // if self doesn't having winning move, check to block winning move
         if( null == rv ) {
            rv = findWinningMove( empties, EnumSet.copyOf(b.getSpacesByToken( (T == Constants.Token.X ? Constants.Token.O : Constants.Token.X)) ) );
         }
      }

      // if still nothing found, take center if available
      if( null == rv && empties.contains(Constants.Space.FIVE) ) {
         rv = Constants.Space.FIVE;
      }

      // finally, choose randomly from the available spaces
      if( null == rv ) {
         ArrayList< Constants.Space > eArray = new ArrayList<>( empties );
         java.util.Collections.shuffle( eArray );
         rv = eArray.get( 0 );
      }

      return rv;
   }// ---

   public Boolean isHuman() {
      return H;
   }// ---

   public Constants.Token getToken() {
      return T;
   }// ---

}// =================================================================