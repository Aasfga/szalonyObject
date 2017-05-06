package TurnBasedGameLogic.Controller;

import TurnBasedGameLogic.Client.View;

public abstract class Match implements Runnable
{
	Judge judge;
	Player players[] = new Player[2];
	View view;

	public abstract GameState getState();
	public abstract void setState(GameState state);

	Match(Player f, Player s, View v)
	{
		players[0] = f;
		players[1] = s;
		view = v;
	}

	public void run()
	{
		do
		{
			judge.nextRound();
			view.setCurrentView(judge.game.getString(getState()));
		}while(!judge.isEnd());
	}

	public static void link(Judge j, Match m)
	{
		j.match = m;
		m.judge = j;
	}

	public Player[] getPlayers()
	{
		return players;
	}

}
