package Go.IO.ConsoleViewInput;

import Go.Common.StoneColour;
import Go.IO.Input;
import Go.State;

import java.util.Scanner;

public class ConsoleInput implements Input
{
	Scanner scanner = new Scanner(System.in);

	@Override
	public State.Move getMove(StoneColour colour)
	{
		return new State.Move(scanner.nextInt(), scanner.nextInt());
	}
}
