package Go;

import Go.Common.StoneColour;
import Go.IO.Input;

public class Player
{
	String name;
	Input input;
	StoneColour colour;

	public Player(String n, Input i)
	{
		name = n;
		input = i;
	}

	public void setColour(StoneColour c)
	{
		colour = c;
	}

	public StoneColour getColour()
	{
		return colour;
	}

	public State.Move makeMove()
	{
		State.Move move = input.getMove(colour);
		move.player = this;
		return move;
	}
}
