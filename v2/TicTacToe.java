/**
 * This is v2 of Tic Tac Toe in Java.
 * Although, it doesn't include the command-line version, it is the GUI version.
 * I've noticed the size of the window is different from system to system.
 * The current size looked comfortable on Debian linux.
 * */

public class TicTacToe {

   // ----- main method =========================
   public static void main( String[] args ) {
      java.awt.EventQueue.invokeLater( () -> {
         GWindow.newWindow().setVisible( Boolean.TRUE );
      });
   }// ---

}// =================================================================