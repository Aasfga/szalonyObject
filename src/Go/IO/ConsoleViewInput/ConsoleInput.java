package Go.IO.ConsoleViewInput;

import Go.IO.Input;
import Go.State;

import java.util.Scanner;

public class ConsoleInput implements Input
{
	Scanner scanner = new Scanner(System.in);

	@Override
	public State.Move getMove()
	{
		return new State.Move(scanner.nextInt(), scanner.nextInt());
	}
}
