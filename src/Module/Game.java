package Module;

import Controller.GameState;

public interface Game
{
	boolean isGood(GameState oldState, GameState newState);
	boolean isEnd(GameState state);
	GameState getInitState();
	GameState createState(String data);
	GameState makeActions(GameState state);
}
