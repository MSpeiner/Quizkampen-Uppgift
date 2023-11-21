public class Protokoll {
    private static final int WAITING_FOR_PLAYER = 0;
    private static final int YOURTURN = 1;
    private int state = WAITING_FOR_PLAYER;


    public Protokoll() {
        while (state == WAITING_FOR_PLAYER) {
        }

        while (state == YOURTURN) {
        }
    }
}
