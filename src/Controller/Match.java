package Controller;

import Controller.State.GameState;

public abstract class Match implements Runnable
{
	Judge judge;

	public abstract GameState getState();
	public abstract void setState(GameState state);

	public void run()
	{
		do
		{
			judge.nextRound();
		}while(!judge.isEnd());
	}

	public void link(Judge j, Match m)
	{
		j.match = m;
		m.judge = j;
	}
}
