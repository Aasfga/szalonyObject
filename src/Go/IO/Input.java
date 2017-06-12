package Go.IO;


import Go.Common.StoneColour;
import Go.State;

public interface Input
{
	State.Move getMove(StoneColour colour);

}

