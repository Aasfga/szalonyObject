package GoGame.View;

import TurnBasedGameLogic.Client.Input;

import java.util.concurrent.TimeUnit;

import static GoGame.View.Go.HEIGHT;
import static GoGame.View.Go.WIDTH;
import static GoGame.View.View.array;

/**
 * Created by lukasz on 08.05.2017.
 */
public class Move implements Input
{
	static String content = null;
	static boolean getMove = false;

	public String getMove()
	{
		getMove = true;

		while(content == null)
			try
			{
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e)
			{
			}

		String ans = content;
		content = null;
		return ans;
	}

	static void setMove(int x, int y, int color)
	{
		if(!getMove)
			return;

		getMove = false;
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < Go.HEIGHT; i++)
		{
			for(int j = 0; j < Go.WIDTH; j++)

				if(x == j && i == y)
					builder.append((char)color);
				else
					builder.append(View.array[i][j]);
		}

		content = builder.toString();
	}
}
