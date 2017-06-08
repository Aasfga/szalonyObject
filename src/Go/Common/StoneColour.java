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

	public StoneColour other()
	{
		if(this.equals(White))
			return Black;
		else if(this.equals(Black))
			return White;
		throw new IllegalArgumentException();
	}
}
