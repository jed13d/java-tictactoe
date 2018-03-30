import java.awt.Dimension;

public final class Constants {

    private static final Integer W_HEIGHT = 355;
    private static final Integer W_WIDTH = 411;
    private static final Dimension WINDOW_D = new Dimension(W_WIDTH, W_HEIGHT);

    private static final Integer P_HEIGHT = 300;
    private static final Integer P_WIDTH = 400;
    private static final Dimension PANEL_D = new Dimension(P_WIDTH, P_HEIGHT);

    private static final Integer VLINE_L = P_WIDTH / 3;
    private static final Integer VLINE_R = VLINE_L * 2;

    private static final Integer HLINE_T = P_HEIGHT / 3;
    private static final Integer HLINE_B = HLINE_T * 2;

    private static final Integer AI_THINK_TIMER = 1000;

    public static enum Token {
        X, O, E;
    }// ---

    public static enum Space {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
    }// ---

    public static enum GStatus {
        P1WIN, P2WIN, CAT, PLAY;
    }// ---

    public static Integer getWHgt() {
        return W_HEIGHT;
    }// ---

    public static Integer getWWid() {
        return W_WIDTH;
    }// ---

    public static Dimension getWDim() {
        return WINDOW_D;
    }// ---

    public static Integer getPHgt() {
        return P_HEIGHT;
    }// ---

    public static Integer getPWid() {
        return P_WIDTH;
    }// ---

    public static Dimension getPDim() {
        return PANEL_D;
    }// ---

    public static Integer getVLineL() {
        return VLINE_L;
    }// ---

    public static Integer getVLineR() {
        return VLINE_R;
    }// ---

    public static Integer getHLineT() {
        return HLINE_T;
    }// ---

    public static Integer getHLineB() {
        return HLINE_B;
    }// ---

    public static Integer getAI_Timer() {
        return AI_THINK_TIMER;
    }// ---

    private Constants() {}

}
