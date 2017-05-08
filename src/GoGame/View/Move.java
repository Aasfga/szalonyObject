package GoGame.View;

import TurnBasedGameLogic.Client.Input;

import java.util.concurrent.TimeUnit;

import static GoGame.View.Go.HEIGHT;
import static GoGame.View.Go.WIDTH;
import static GoGame.View.View.array;

/**
 * Created by lukasz on 08.05.2017.
 */
public class Move implements Input {
    static String content = null;
    static boolean getMove = false;

    public String getMove() {
        getMove = true;

        while(content == null)
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {}

            String ans = content;
        content = null;
        return ans;
    }
    static void setMove(int x, int y, boolean color) {
        if(!getMove)
            return;
        int index = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                array[y][x] = view.charA
                index++;
            }
        }
    }
}
