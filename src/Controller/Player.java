package Controller;

import Client.Input;

public class Player
{
	String name;
	Input input;
	Judge judge;

	public Player(String n, Input i, Judge j)
	{
		name = n;
		input = i;
		judge = j;
	}

	GameState makeMove()
	{
		return judge.game.createState(input.getMove());
	}
}
