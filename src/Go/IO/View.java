package Go.IO;


import Go.Logic.Game;
import Go.State;

public interface View
{
	void setCurrentView(State state);

	void setError(Game.Result res);
}