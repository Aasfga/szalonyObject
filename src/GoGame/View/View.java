package GoGame.View;

import GoGame.Logic.GoGame;
import TurnBasedGameLogic.Controller.GameState;
import TurnBasedGameLogic.Controller.Judge;
import TurnBasedGameLogic.Controller.Match;
import TurnBasedGameLogic.Controller.Player;
import javafx.application.Application;

import static GoGame.View.Go.HEIGHT;
import static GoGame.View.Go.WIDTH;
import static GoGame.View.Go.createContent;

public class View  implements TurnBasedGameLogic.Client.View{

    static char[][] array = new char[20][20];

    public void setCurrentView( String view) {
        int index = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                array[y][x] = view.charAt(index);
                index++;
            }
        }

        Go.scene.setRoot(createContent());
    }

    private static void setView(int size) {
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
        setView(12);
    }

    public static class LocalMatch extends Match
    {
        GameState gs;


        public LocalMatch(Player f, Player s, View v, Judge j)
        {
            super(f, s, v);
            judge = j;
            gs = new GameState(judge.getGame().getInitBoard(), players[0]);
        }

        @Override
        public GameState getState()
        {
            return gs;
        }

        @Override
        public void setState(GameState state)
        {
            gs = state;
        }
    }
}
