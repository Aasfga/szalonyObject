package Go;

import Go.IO.View;
import Go.Logic.Board;
import Go.Logic.Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static Go.Common.StoneColour.Black;
import static Go.Common.StoneColour.White;

public interface Match
{
	void startGame();


	class LocalMatch implements Match, Runnable
	{
		Player players[] = new Player[2];
		View view;
		State state;
		Game game = Game.get();

		public LocalMatch(View v, Board board, Player... p)
		{
			if(p.length != 2)
				throw new IllegalArgumentException("Wrong number of players");
			view = v;
			Random random = new Random();

			int x = random.nextInt(2);

			players[0] = p[x];
			players[1] = p[1 - x];
			players[0].setColour(Black);
			players[1].setColour(White);
			state = new State(players[1], board, new ArrayList<>());
		}

		private Player getCurrentPlayer(Player p)
		{
			return p == players[0] ? players[1] : players[0];
		}

		private State.Move getCorrectMove(Player p)
		{
			State.Move move;
			while(true)
			{
				move = p.makeMove();
				Game.Result res = game.isCorrect(state, move);

				if(res.equals(Game.Result.Success))
					break;
				else
					view.setError(res);
			}

			return move;
		}


		@Override
		public void startGame()
		{
			new Thread(this).start();
		}

		@Override
		public void run()
		{
			do
			{
				view.setCurrentView(state);
				Player player = getCurrentPlayer(state.player);
				State.Move move = getCorrectMove(player);
				state = game.addMove(state, move);
				state = game.postMoveActions(state, move);
			}while(!game.isEnd(state));
		}
	}
}
