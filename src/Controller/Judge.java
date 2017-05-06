package Controller;

import Module.Game;

public class Judge
{
	Match match;
	Game game;

	public boolean isEnd()
	{
		return false;
	}

	public void nextRound()
	{
		Player crrPlayer;
		Player players[] = match.getPlayers();
		GameState crrState = match.getState();
		GameState newState;

		if(players[0] == match.getState().getPlayer())
			crrPlayer = players[1];
		else
			crrPlayer = players[0];


		do
		{
			newState = crrPlayer.makeMove();
		}while(!game.isGood(crrState, newState));

		crrState = game.makeActions(crrState);
		match.setState(crrState);
	}
}
