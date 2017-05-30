package Go.IO.WindowViewInput;

public enum PieceType {
    BLACK(1), WHITE(-1);
    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}