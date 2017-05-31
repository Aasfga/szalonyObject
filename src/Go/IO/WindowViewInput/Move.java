package Go.IO.WindowViewInput;

import Go.IO.Input;
import Go.State;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static Go.IO.WindowViewInput.WindowView.judgeDidHisJob;

public class Move implements Input
{
	static State.Move content = null;
	static volatile boolean getMove = false;
	static CountDownLatch latch = new CountDownLatch(1);

	public State.Move getMove()
	{
		getMove = true;
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
		judgeDidHisJob = true;
		return ans;
	}

	static synchronized void setMove(int x, int y)
	{
		System.out.println("abcd");
		if(!getMove)
			return;

		getMove = false;
		content = new State.Move(y, x);
		latch.countDown();
	}
}
