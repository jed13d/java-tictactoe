/**
* GBoard is the game board class.
* Most of the game management happens here.
* */
import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;

public class GBoard {

   // ----- Variables ==========================
   private final Player p1;
   private final Player p2;
   private Constants.GStatus gstatus;
   private Boolean p1turn;
   private Integer turn;

   private final EnumSet< Constants.Space > ALLSPACES;
   private final EnumMap< Constants.Space, Constants.Token > STATE;

   private Integer wx1, wx2, wy1, wy2;             // winning line coords

   // ----- Constructors =======================
   private GBoard( Boolean p1Human, Boolean p2Human ) {
      ALLSPACES = EnumSet.allOf(Constants.Space.class);
      STATE = new EnumMap<>(Constants.Space.class);
      p1 = Player.newPlayer(p1Human, Constants.Token.X);
      p2 = Player.newPlayer(p2Human, Constants.Token.O);
      p1turn = true;
      gstatus = Constants.GStatus.PLAY;
      turn = 1;
      wx1 = wx2 = wy1 = wy2 = 0;
      initBoard();
   }// ---

   // ----- Builder ============================
   public static GBoard newBoard( Boolean p1Human, Boolean p2Human ) {
      GBoard rv = new GBoard(p1Human, p2Human);
      return rv;
   }// ---

   // ----- Private Methods ====================
   private Boolean hasWinner(HashSet< Constants.Space > s) {
      Boolean rv = Boolean.FALSE;

      if(s.contains(Constants.Space.FIVE)) {
         if(s.contains(Constants.Space.ONE) && s.contains(Constants.Space.NINE)) {
            wx1 = wy1 = 5;
            wx2 = Constants.getPWid() - 5;
            wy2 = Constants.getPHgt() - 5;
            rv = Boolean.TRUE;
         } else if(s.contains(Constants.Space.TWO) && s.contains(Constants.Space.EIGHT)) {
            wx1 = wx2 = Constants.getPWid() / 2;
            wy1 = 5;
            wy2 = Constants.getPHgt() - 5;
            rv = Boolean.TRUE;
         } else if(s.contains(Constants.Space.THREE) && s.contains(Constants.Space.SEVEN)) {
            wx1 = wy2 = 5;
            wx2 = Constants.getPWid() - 5;
            wy1 = Constants.getPHgt() - 5;
            rv = Boolean.TRUE;
         } else if(s.contains(Constants.Space.FOUR) && s.contains(Constants.Space.SIX)) {
            wx1 = 5;
            wx2 = Constants.getPWid() - 5;
            wy1 = wy2 = Constants.getPHgt() / 2;
            rv = Boolean.TRUE;
         }
      }

      if(Objects.equals(rv, Boolean.FALSE) && s.contains(Constants.Space.ONE)) {
         if(s.contains(Constants.Space.TWO) && s.contains(Constants.Space.THREE)) {
            wx1 = 5;
            wx2 = Constants.getPWid() - 5;
            wy1 = wy2 = Constants.getPHgt() / 6;
            rv = Boolean.TRUE;
         } else if(s.contains(Constants.Space.FOUR) && s.contains(Constants.Space.SEVEN)) {
            wx1 = wx2 = Constants.getPWid() / 6;
            wy1 = 5;
            wy2 = Constants.getPHgt() - 5;
            rv = Boolean.TRUE;
         }
      }

      if(Objects.equals(rv, Boolean.FALSE) && s.contains(Constants.Space.NINE)) {
         if(s.contains(Constants.Space.THREE) && s.contains(Constants.Space.SIX)) {
            wx1 = wx2 = (Constants.getPWid() / 6) * 5;
            wy1 = 5;
            wy2 = Constants.getPHgt() - 5;
            rv = Boolean.TRUE;
         } else if(s.contains(Constants.Space.SEVEN) && s.contains(Constants.Space.EIGHT)) {
            wx1 = 5;
            wx2 = Constants.getPWid() - 5;
            wy1 = wy2 = (Constants.getPHgt() / 6) * 5;
            rv = Boolean.TRUE;
         }
      }

      return rv;
   }// ---

   private Constants.Space getSpaceFromCoords(Integer mx, Integer my) {
      // init vars
      Integer x = (mx / Constants.getVLineL()) + 1;
      Integer y = my / Constants.getHLineT();
      Integer i = (y * 3) + x;
      Constants.Space rv;

      // find space
      switch(i) {
         case 1:
            rv = Constants.Space.ONE;
            break;
         case 2:
            rv = Constants.Space.TWO;
            break;
         case 3:
            rv = Constants.Space.THREE;
            break;
         case 4:
            rv = Constants.Space.FOUR;
            break;
         case 5:
            rv = Constants.Space.FIVE;
            break;
         case 6:
            rv = Constants.Space.SIX;
            break;
         case 7:
            rv = Constants.Space.SEVEN;
            break;
         case 8:
            rv = Constants.Space.EIGHT;
            break;
         case 9:
            rv = Constants.Space.NINE;
            break;
         default:
            rv = null;
            break;

      }
      return rv;
   }// ---

   private void initBoard() {
      ALLSPACES.forEach( s -> {
         STATE.put(s, Constants.Token.E);
      });
   }// ---

   private void paintBoard(Graphics g) {
      g.setColor(Color.BLACK);
      g.drawLine(Constants.getVLineL(), 0, Constants.getVLineL(), Constants.getPHgt());
      g.drawLine(Constants.getVLineR(), 0, Constants.getVLineR(), Constants.getPHgt());
      g.drawLine(0, Constants.getHLineT(), Constants.getPWid(), Constants.getHLineT());
      g.drawLine(0, Constants.getHLineB(), Constants.getPWid(), Constants.getHLineB());
   }// ---

   private void paintOs(HashSet< Constants.Space > toFill, Graphics g) {
      // init vars
      Integer x, y, w, h;
      w = Constants.getVLineL() - 20;
      h = Constants.getHLineT() - 20;
      x = y = 0;

      // loop through toFill and draw each O
      for(Constants.Space s: toFill) {
         switch(s) {
            case ONE:
               x = y = 10;
               break;
            case TWO:
               x = Constants.getVLineL() + 10;
               y = 10;
               break;
            case THREE:
               x = Constants.getVLineR() + 10;
               y = 10;
               break;
            case FOUR:
               x = 10;
               y = Constants.getHLineT() + 10;
               break;
            case FIVE:
               x = Constants.getVLineL() + 10;
               y = Constants.getHLineT() + 10;
               break;
            case SIX:
               x = Constants.getVLineR() + 10;
               y = Constants.getHLineT() + 10;
               break;
            case SEVEN:
               x = 10;
               y = Constants.getHLineB() + 10;
               break;
            case EIGHT:
               x = Constants.getVLineL() + 10;
               y = Constants.getHLineB() + 10;
               break;
            case NINE:
               x = Constants.getVLineR() + 10;
               y = Constants.getHLineB() + 10;
               break;
         }
         g.drawOval(x, y, w, h);
      }
   }// ---

   private void paintXs(HashSet< Constants.Space > toFill, Graphics g) {
      // init vars
      Integer l1x1, l1y1, l1x2, l1y2, l2x1, l2y1, l2x2, l2y2;
      l1x1 = l1x2 = l1y1 = l1y2 = l2x1 = l2x2 = l2y1 = l2y2 = 0;

      // loop through toFill and draw each X
      for(Constants.Space s: toFill) {
         switch(s) {
            case ONE:
               l1x1 = l2x1 = 10;
               l1x2 = l2x2 = Constants.getVLineL() - 10;
               l1y1 = l2y2 = 10;
               l1y2 = l2y1 = Constants.getHLineT() - 10;
               break;
            case TWO:
               l1x1 = l2x1 = Constants.getVLineL() + 10;
               l1x2 = l2x2 = Constants.getVLineR() - 10;
               l1y1 = l2y2 = 10;
               l1y2 = l2y1 = Constants.getHLineT() - 10;
               break;
            case THREE:
               l1x1 = l2x1 = Constants.getVLineR() + 10;
               l1x2 = l2x2 = Constants.getPWid() - 10;
               l1y1 = l2y2 = 10;
               l1y2 = l2y1 = Constants.getHLineT() - 10;
               break;
            case FOUR:
               l1x1 = l2x1 = 10;
               l1x2 = l2x2 = Constants.getVLineL() - 10;
               l1y1 = l2y2 = Constants.getHLineT() + 10;
               l1y2 = l2y1 = Constants.getHLineB() - 10;
               break;
            case FIVE:
               l1x1 = l2x1 = Constants.getVLineL() + 10;
               l1x2 = l2x2 = Constants.getVLineR() - 10;
               l1y1 = l2y2 = Constants.getHLineT() + 10;
               l1y2 = l2y1 = Constants.getHLineB() - 10;
               break;
            case SIX:
               l1x1 = l2x1 = Constants.getVLineR() + 10;
               l1x2 = l2x2 = Constants.getPWid() - 10;
               l1y1 = l2y2 = Constants.getHLineT() + 10;
               l1y2 = l2y1 = Constants.getHLineB() - 10;
               break;
            case SEVEN:
               l1x1 = l2x1 = 10;
               l1x2 = l2x2 = Constants.getVLineL() - 10;
               l1y1 = l2y2 = Constants.getHLineB() + 10;
               l1y2 = l2y1 = Constants.getPHgt() - 10;
               break;
            case EIGHT:
               l1x1 = l2x1 = Constants.getVLineL() + 10;
               l1x2 = l2x2 = Constants.getVLineR() - 10;
               l1y1 = l2y2 = Constants.getHLineB() + 10;
               l1y2 = l2y1 = Constants.getPHgt() - 10;
               break;
            case NINE:
               l1x1 = l2x1 = Constants.getVLineR() + 10;
               l1x2 = l2x2 = Constants.getPWid() - 10;
               l1y1 = l2y2 = Constants.getHLineB() + 10;
               l1y2 = l2y1 = Constants.getPHgt() - 10;
               break;
         }
         g.drawLine(l1x1, l1y1, l1x2, l1y2);
         g.drawLine(l2x1, l2y1, l2x2, l2y2);
      }
   }// ---

   // ----- Public Methods =====================
   public Boolean activePisHuman() {
      return( p1turn ? p1.isHuman() : p2.isHuman() );
   }// ---

   public Boolean attemptComputerMove() {
      Boolean rv = Boolean.FALSE;

      // get active player and determine if AI
      Player activeP = (p1turn ? p1 : p2);
      if(!activeP.isHuman()) {

         // invoke AI's move and get choice
         Constants.Space aiMarking = activeP.aiMove(this);

         // make AI's move *** note, considering ai pulls move from a list of empty
         //                      spaces, it should never be null and should always be empty
         if(null != aiMarking && STATE.get(aiMarking) == Constants.Token.E) {
            STATE.put(aiMarking, (p1turn ? p1.getToken() : p2.getToken()));
            rv = Boolean.TRUE;
         }
      }

      return rv;
   }// ---

   public Boolean attemptHumanMove(Integer mx, Integer my) {
      Boolean rv = Boolean.FALSE;

      // change mouse coords to Space equivalent
      Constants.Space clicked = getSpaceFromCoords(mx, my);

      // if the space is empty, player acquire, change turn, and return true
      if(null != clicked && STATE.get(clicked) == Constants.Token.E) {
            STATE.put(clicked, (p1turn ? p1.getToken() : p2.getToken()));
            rv = Boolean.TRUE;
      }

      return rv;
   }// ---

   public Constants.GStatus getGStatus() {
      return gstatus;
   }// ---

   public HashSet< Constants.Space > getSpacesByToken(Constants.Token t) {
      HashSet< Constants.Space > rv = new HashSet<>();
      if(null != t) {
            ALLSPACES.forEach(s -> {
               if(STATE.get(s) == t) {
                  rv.add(s);
               }
            });
      }
      return rv;
   }// ---

   public void paint(Graphics g) {
      paintBoard(g);
      paintOs(getSpacesByToken(Constants.Token.O), g);
      paintXs(getSpacesByToken(Constants.Token.X), g);
      if(wx1 != 0) {
            g.drawLine(wx1, wy1, wx2, wy2);
      }
   }// ---

   /**
   * Checks for winner and updates status accordingly
   */
   public void update() {
      if(turn > 4) {
         if(hasWinner(getSpacesByToken(p1.getToken()))) {
            gstatus = Constants.GStatus.P1WIN;
            System.out.println("P1 Wins!");
         } else if(hasWinner(getSpacesByToken(p2.getToken()))) {
            gstatus = Constants.GStatus.P2WIN;
            System.out.println("P2 Wins!");
         } else if(turn == 9) {
            gstatus = Constants.GStatus.CAT;
            System.out.println("Draw!");
         }
      }
      p1turn = !p1turn;
      turn++;
   }// ---

}
