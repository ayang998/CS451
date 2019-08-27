
package model;

import java.awt.Point;
import java.util.List;

import logic.MoveGenerator;
import logic.MoveLogic;

public class Game {

	private Board board;

	private boolean isP1Turn;

	private int skipIndex;

	public Game() {
		restart_game();
	}

	public Game(String state) {
		setGameState(state);
	}

	public Game(Board board, boolean isP1Turn, int skipIndex) {
		this.board = (board == null) ? new Board() : board;
		this.isP1Turn = isP1Turn;
		this.skipIndex = skipIndex;
	}

	public Game copy() {
		Game gameTest = new Game();
		gameTest.board = board.copy();
		gameTest.isP1Turn = isP1Turn;
		gameTest.skipIndex = skipIndex;
		return gameTest;
	}

	public void restart_game() {
		this.board = new Board();
		this.skipIndex = -1;
		this.isP1Turn = true;

	}

	public boolean move(Point start, Point end) {
		if (start == null || end == null) {
			return false;
		}
		return move(Board.toIndex(start), Board.toIndex(end));
	}

	public boolean move(int startIndex, int endIndex) {

		if (!MoveLogic.isValidMove(this, startIndex, endIndex)) {
			return false;
		}

		Point middle = Board.middle(startIndex, endIndex);
		int midIndex = Board.toIndex(middle);
		this.board.set(endIndex, board.get(startIndex));
		this.board.set(midIndex, Board.EMPTY);
		this.board.set(startIndex, Board.EMPTY);

		Point end = Board.toPoint(endIndex);
		int id = board.get(endIndex);
		boolean switchTurn = false;
		if (end.y == 0 && id == Board.RED_CHECKER) {
			this.board.set(endIndex, Board.RED_KING);
			switchTurn = true;
		} else if (end.y == 7 && id == Board.BLACK_CHECKER) {
			this.board.set(endIndex, Board.BLACK_KING);
			switchTurn = true;
		}

		boolean midValid = Board.isValidIndex(midIndex);
		if (midValid) {
			this.skipIndex = endIndex;
		}
		if (!midValid || MoveGenerator.getSkips(board.copy(), endIndex).isEmpty()) {
			switchTurn = true;
		}
		if (switchTurn) {
			this.isP1Turn = !isP1Turn;
			this.skipIndex = -1;
		}

		return true;
	}

	public Board getBoard() {
		return board.copy();
	}

	public boolean isGameOver() {

		List<Point> black = board.find(Board.BLACK_CHECKER);
		black.addAll(board.find(Board.BLACK_KING));
		if (black.isEmpty()) {
			return true;
		}
		List<Point> red = board.find(Board.RED_CHECKER);
		red.addAll(board.find(Board.RED_KING));
		if (red.isEmpty()) {
			return true;
		}

		List<Point> test = isP1Turn ? black : red;
		for (Point p : test) {
			int i = Board.toIndex(p);
			if (!MoveGenerator.getMoves(board, i).isEmpty() || !MoveGenerator.getSkips(board, i).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public boolean isP1Turn() {
		return isP1Turn;
	}

	public void setP1Turn(boolean isP1Turn) {
		this.isP1Turn = isP1Turn;
	}

	public int getSkipIndex() {
		return skipIndex;
	}

	public String getGameState() {

		String state = "";
		for (int i = 0; i < 32; i++) {
			state += "" + board.get(i);
		}

		state += (isP1Turn ? "1" : "0");
		state += skipIndex;

		return state;
	}

	public void setGameState(String state) {

		restart_game();

		if (state == null || state.isEmpty()) {
			return;
		}

		int n = state.length();
		for (int i = 0; i < 32 && i < n; i++) {
			try {
				int id = Integer.parseInt("" + state.charAt(i));
				this.board.set(i, id);
			} catch (NumberFormatException e) {
			}
		}

		if (n > 32) {
			this.isP1Turn = (state.charAt(32) == '1');
		}
		if (n > 33) {
			try {
				this.skipIndex = Integer.parseInt(state.substring(33));
			} catch (NumberFormatException e) {
				this.skipIndex = -1;
			}
		}
	}
}
