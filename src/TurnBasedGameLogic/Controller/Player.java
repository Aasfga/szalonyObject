package TurnBasedGameLogic.Controller;

import TurnBasedGameLogic.Client.Input;

public class Player
{
	String name;
	Input input;
	Judge judge;
	PlayerId id;

	public Player(String n, Input i, Judge j)
	{
		name = n;
		input = i;
		judge = j;
	}

	public GameState makeMove()
	{
		return new GameState(judge.game.createBoard(input.getMove()), this);
	}

	public void setId(PlayerId id)
	{
		if(this.id == null)
			this.id = id;
	}

	public PlayerId getId()
	{
		return id;
	}
}
