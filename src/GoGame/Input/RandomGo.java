package GoGame.Input;

import GoGame.Logic.GoBoard;
import GoGame.Logic.GoGame;
import TurnBasedGameLogic.Client.Input;
import TurnBasedGameLogic.Controller.Board;
import TurnBasedGameLogic.Controller.GameState;
import TurnBasedGameLogic.Controller.Match;
import TurnBasedGameLogic.Controller.Player;

import java.util.Random;

public class RandomGo implements Input
{
	Match match;

	@Override
	public String getMove()
	{
		GoBoard b = new GoBoard((GoBoard) match.getState().getBoard());
		Random rand = new Random();
		Player p = match.getPlayers()[0] == match.getState().getPlayer() ? match.getPlayers()[1] : match.getPlayers()[0];
		int x;
		int y;
		do
		{
			x = rand.nextInt(b.getSize());
			y = rand.nextInt(b.getSize());
		}while(b.getField(y, x) != 0);

		b.setField((GoBoard.Colour) p.getId(), y, x);

		return GoGame.getGoGame().getString(new GameState(b, p));
	}

	RandomGo(Match m)
	{
		match = m;
	}
}
