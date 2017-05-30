package Go.IO.WindowViewInput;

import Go.IO.Input;
import Go.State;
import java.util.concurrent.TimeUnit;

public class Move implements Input
{
	static State.Move content = null;
	static boolean getMove = false;

	public State.Move getMove()
	{
		getMove = true;

		while(content == null)
			try
			{
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e)
			{
			}

		State.Move ans = content;
		content = null;
		return ans;
	}

	static synchronized void setMove(int x, int y)
	{
		if(!getMove)
			return;

		getMove = false;
		content = new State.Move(y, x);
	}
}
