package Go;

import Go.Common.StoneColour;
import Go.IO.Input;

import java.io.Serializable;

public class Player implements Serializable
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

	public void setInput(Input i)
	{
		this.input = i;
	}
}
