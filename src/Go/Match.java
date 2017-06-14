package Go;

import Go.Common.StoneColour;
import Go.IO.View;
import Go.Logic.Board;
import Go.Logic.Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Server.Client;
import Server.Remote;

import static Go.Common.StoneColour.Black;
import static Go.Common.StoneColour.White;

import static Go.IO.WindowViewInput.Go.movecolour;
import static Go.IO.WindowViewInput.Go.scoreblack;
import static Go.IO.WindowViewInput.Go.scorewhite;

import static Go.IO.WindowViewInput.WindowView.judgeDidHisJob;

import static Go.IO.WindowViewInput.Go.ended;

public interface Match
{
	void startGame();
	State getState();
	Player[] getPlayers();

	class RemoteMatch implements Match, Runnable {
        Player players[] = new Player[2];
        Player localPlayer = players[0];
        View view;
        State state;
        Game game = Game.get();
        Client c;

        public RemoteMatch(Client c, View v, Board board, Player local, Player... p)
        {
            if(p.length != 2)
                throw new IllegalArgumentException("Wrong number of players");
            view = v;
            this.c = c;
            localPlayer = local;
            System.out.println(local.colour.toString());
            if ( localPlayer.colour == StoneColour.Black) {
                movecolour = true;
            }
            else movecolour = false;

            players = p;
            state = new State(players[1], board, new ArrayList<Board>(), 0, 0);
        }

		@Override
		public void startGame() { new Thread(this).start(); }

        private Player getCurrentPlayer(Player p) { return p == players[0] ? players[1] : players[0]; }

		@Override
		public State getState() { return state; }

		@Override
		public Player[] getPlayers() { return players; }

        private State.Move getCorrectMove(Player p)
        {
            State.Move move;
            while(true)
            {
                move = p.makeMove();
                Game.Result res = game.isCorrect(state, move);

                if(res.equals(Game.Result.Success))
                    break;
                else
                    view.setError(res);
            }

            return move;
        }

        private void resolveState() {
            Remote.StateResponse s = this.c.resolveState();
            Board b = Game.get().getInitBoard(13);
            for(int i = 0; i < s.board.length; ++i) {
                for(int j = 0; j < s.board[i].length; ++j) {
                    switch(s.board[i][j]) {
                        case 0:
                            b.array[i][j].setColour(StoneColour.Empty);
                            break;
                        case 1:
                            b.array[i][j].setColour(StoneColour.White);
                            break;
                        case 2:
                            b.array[i][j].setColour(StoneColour.Black);
                            break;
                    }
                }
            }
            State res = getState();
            res.whiteCaptured = s.scoreblack;
            res.blackCaptured = s.scorewhite;
            res.board = b;
            res.history.add(b);
            if( s.player.equals("Black") ) {
                res.player = players[1];
            }
            else {
                res.player = players[0];
            }
            this.state = res;
        }

		@Override
		public void run() {
            State.Move move = null;

            do
            {
                resolveState();
                //System.out.println("White captured: " + state.getWhiteCaptured() + "  ##  Black captured: " + state.getBlackCaptured());
                scoreblack = state.getWhiteCaptured();
                scorewhite = state.getBlackCaptured();
                view.setCurrentView(state);
                Player player = getCurrentPlayer(state.player);
                if( player == localPlayer ) {
                    move = getCorrectMove(player);
                    c.sendMove(move);
                }
                try {Thread.sleep(100); } catch (Exception e) {}
            } while(!ended);
		}
	}


	class LocalMatch implements Match, Runnable
	{
		Player players[] = new Player[2];
		View view;
		State state;
		Game game = Game.get();

		public LocalMatch(View v, Board board, Player... p)
		{
			if(p.length != 2)
				throw new IllegalArgumentException("Wrong number of players");
			view = v;
			Random random = new Random();

			int x = random.nextInt(2);

			players[0] = p[x];
			players[1] = p[1 - x];
			players[0].setColour(Black);
			players[1].setColour(White);
			state = new State(players[1], board, new ArrayList<>(), 0, 0);
		}

		public LocalMatch(View v, State s, Player... p)
		{
			state = s;
			view = v;
			players = p;
		}

		private Player getCurrentPlayer(Player p)
		{
			return p == players[0] ? players[1] : players[0];
		}

		private State.Move getCorrectMove(Player p)
		{
			State.Move move;
			while(true)
			{
				move = p.makeMove();
				Game.Result res = game.isCorrect(state, move);

				if(res.equals(Game.Result.Success))
					break;
				else
					view.setError(res);
			}

			return move;
		}


		@Override
		public void startGame()
		{
			new Thread(this).start();
		}

		@Override
		public State getState()
		{
			return state;
		}

		@Override
		public Player[] getPlayers()
		{
			return players;
		}

		@Override
		public void run()
		{
			State.Move move;

			do
			{
				view.setCurrentView(state);
				Player player = getCurrentPlayer(state.player);
				move = getCorrectMove(player);
				state = game.addMove(state, move);
				state = game.postMoveActions(state, move);
				System.out.println("White captured: " + state.getWhiteCaptured() + "  ##  Black captured: " + state.getBlackCaptured());
			}while(!game.isEnd(state, move));
		}
	}
}