package GoGame.View;

/**
 * Created by lukasz on 06.05.2017.
 */
public enum PieceType {
    BLACK(1), WHITE(-1);

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}