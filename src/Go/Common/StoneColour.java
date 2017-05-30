package Go.Common;

public enum StoneColour
{
	Empty(0),
	White(1),
	Black(2);


	int colour;

	StoneColour(int c)
	{
		colour = c;
	}

	public int getValue()
	{
		return colour;
	}
}
