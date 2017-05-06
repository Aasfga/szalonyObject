package TurnBasedGameLogic.Module;

import TurnBasedGameLogic.Controller.Board;
import TurnBasedGameLogic.Controller.GameState;

public interface Game
{
	boolean isGood(GameState oldState, GameState newState);
	boolean isEnd(GameState state);
	Board getInitBoard();
	Board createBoard(String data);
	GameState makeActions(GameState state);
	String getString(GameState state);
}
