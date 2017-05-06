package Controller;

public abstract class Match implements Runnable
{
	Judge judge;
	Player players[] = new Player[2];

	public abstract GameState getState();
	public abstract void setState(GameState state);

	Match(Player f, Player s)
	{
		players[0] = f;
		players[1] = s;
	}

	public void run()
	{
		do
		{
			judge.nextRound();
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
