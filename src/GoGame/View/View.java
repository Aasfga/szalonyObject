package GoGame.View;

import GoGame.Logic.GoGame;
import TurnBasedGameLogic.Controller.GameState;
import TurnBasedGameLogic.Controller.Judge;
import TurnBasedGameLogic.Controller.Match;
import TurnBasedGameLogic.Controller.Player;
import javafx.application.Application;

import static GoGame.View.Go.*;



public class View  implements TurnBasedGameLogic.Client.View{

    static char[][] array = new char[20][20];
    static int lastx = 19;
    static int lasty = 19;

    public static boolean judgeDidHisJob = false;

    public synchronized void setCurrentView(String viewToSet) {
        int index = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                array[y][x] = viewToSet.charAt(index);
                index++;
            }
        }
        judgeDidHisJob = true;
        //        Go.scene.setRoot(createContent());
    }

    private static void startingView(int size) {
        HEIGHT = size;
        WIDTH = size;
        new Thread(() -> Application.launch(Go.class)).start();
    }

    public static void main(String[] args) throws InterruptedException {

        Judge judge = new Judge();
        judge.setGame(GoGame.getGoGame());
        Player f = new Player("A", new Move(), judge);
        Player s = new Player("B", new Move(), judge);
        Match match = new LocalMatch(f, s, new View(), judge);
        Match.link(judge, match);
        new Thread(match).start();
        startingView(12);
    }

    public static class LocalMatch extends Match
    {
        GameState gameState;


        public LocalMatch(Player f, Player s, View v, Judge j)
        {
            super(f, s, v);
            judge = j;
            gameState = new GameState(judge.getGame().getInitBoard(), players[0]);
        }

        @Override
        public GameState getState()
        {
            return gameState;
        }

        @Override
        public void setState(GameState state)
        {
            gameState = state;
        }
    }
}
