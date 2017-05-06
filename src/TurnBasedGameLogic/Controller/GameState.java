package TurnBasedGameLogic.Controller;

public class GameState
{
	Board board;
	Player player;


	public GameState(Board b, Player p)
	{
		board = b;
		player = p;
	}

	public Player getPlayer()
	{
		return player;
	}

	public Board getBoard()
	{
		return board;
	}

	@Override
	public String toString()
	{
		return board.toString();
	}
}
