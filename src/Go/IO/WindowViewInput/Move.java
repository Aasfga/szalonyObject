package Go.IO.WindowViewInput;

import Go.Common.StoneColour;
import Go.IO.Input;
import Go.State;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static Go.IO.WindowViewInput.Go.movecolour;
import static Go.IO.WindowViewInput.WindowView.judgeDidHisJob;

public class Move implements Input, Serializable
{
	static State.Move content = null;
	static volatile boolean getMove = false;
	static CountDownLatch latch = new CountDownLatch(1);

	public State.Move getMove(StoneColour colour)
	{
		if ( colour == StoneColour.Black) {
			movecolour = true;
		}
		else movecolour = false;
		getMove = true;
		judgeDidHisJob = true;
		try
		{
			latch.await();
		} catch(InterruptedException e)
		{
			e.printStackTrace();
		}

		latch = new CountDownLatch(1);
		State.Move ans = content;
		content = null;
		return ans;
	}

	public static synchronized void setMove(int x, int y)
	{
		if(!getMove)
			return;

		getMove = false;
		content = new State.Move(y, x);
		latch.countDown();
	}
}
