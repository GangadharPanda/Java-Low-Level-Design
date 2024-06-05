package machinecoding.tictactoe.versionzero.ttt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

	private String name;
	private GameStatus status;
	private Board board;
	private HumanPlayer humanPlayer;
	private BotPlayer botPlayer;

	public void makeMove() {

	}

	public boolean checkWinner(Board board) {
		return false;
	}
}
