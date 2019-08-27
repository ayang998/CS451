
package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Game;

public class MoveLogic {

	public static boolean isValidMove(Game game, int startIndex, int endIndex) {
		return game == null ? false
				: isValidMove(game.getBoard(), game.isP1Turn(), startIndex, endIndex, game.getSkipIndex());
	}

	public static boolean isValidMove(Board board, boolean isP1Turn, int startIndex, int endIndex, int skipIndex) {

		if (board == null || !Board.isValidIndex(startIndex) || !Board.isValidIndex(endIndex)) {
			return false;
		} else if (startIndex == endIndex) {
			return false;
		} else if (Board.isValidIndex(skipIndex) && skipIndex != startIndex) {
			return false;
		}

		if (!validateIDs(board, isP1Turn, startIndex, endIndex)) {
			return false;
		} else if (!validateDistance(board, isP1Turn, startIndex, endIndex)) {
			return false;
		}

		return true;
	}

	private static boolean validateIDs(Board board, boolean isP1Turn, int startIndex, int endIndex) {

		if (board.get(endIndex) != Board.EMPTY) {
			return false;
		}

		int id = board.get(startIndex);
		if ((isP1Turn && id != Board.BLACK_CHECKER && id != Board.BLACK_KING)
				|| (!isP1Turn && id != Board.RED_CHECKER && id != Board.RED_KING)) {
			return false;
		}

		Point middle = Board.middle(startIndex, endIndex);
		int midID = board.get(Board.toIndex(middle));
		if (midID != Board.INVALID && ((!isP1Turn && midID != Board.BLACK_CHECKER && midID != Board.BLACK_KING)
				|| (isP1Turn && midID != Board.RED_CHECKER && midID != Board.RED_KING))) {
			return false;
		}

		return true;
	}

	private static boolean validateDistance(Board board, boolean isP1Turn, int startIndex, int endIndex) {

		Point start = Board.toPoint(startIndex);
		Point end = Board.toPoint(endIndex);
		int dx = end.x - start.x;
		int dy = end.y - start.y;
		if (Math.abs(dx) != Math.abs(dy) || Math.abs(dx) > 2 || dx == 0) {
			return false;
		}

		int id = board.get(startIndex);
		if ((id == Board.RED_CHECKER && dy > 0) || (id == Board.BLACK_CHECKER && dy < 0)) {
			return false;
		}

		Point middle = Board.middle(startIndex, endIndex);
		int midID = board.get(Board.toIndex(middle));
		if (midID < 0) {

			List<Point> checkers;
			if (isP1Turn) {
				checkers = board.find(Board.BLACK_CHECKER);
				checkers.addAll(board.find(Board.BLACK_KING));
			} else {
				checkers = board.find(Board.RED_CHECKER);
				checkers.addAll(board.find(Board.RED_KING));
			}

			for (Point p : checkers) {
				int index = Board.toIndex(p);
				if (!MoveGenerator.getSkips(board, index).isEmpty()) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isSafe(Board board, Point checker) {

		if (board == null || checker == null) {
			return true;
		}
		int index = Board.toIndex(checker);
		if (index < 0) {
			return true;
		}
		int id = board.get(index);
		if (id == Board.EMPTY) {
			return true;
		}

		boolean isBlack = (id == Board.BLACK_CHECKER || id == Board.BLACK_KING);
		List<Point> check = new ArrayList<>();
		MoveGenerator.addPoints(check, checker, Board.BLACK_KING, 1);
		for (Point p : check) {
			int start = Board.toIndex(p);
			int tid = board.get(start);

			if (tid == Board.EMPTY || tid == Board.INVALID) {
				continue;
			}

			boolean isWhite = (tid == Board.RED_CHECKER || tid == Board.RED_KING);
			if (isBlack && !isWhite) {
				continue;
			}
			boolean isKing = (tid == Board.BLACK_KING || tid == Board.BLACK_KING);

			int dx = (checker.x - p.x) * 2;
			int dy = (checker.y - p.y) * 2;
			if (!isKing && (isWhite ^ (dy < 0))) {
				continue;
			}
			int endIndex = Board.toIndex(new Point(p.x + dx, p.y + dy));
			if (MoveGenerator.isValidSkip(board, start, endIndex)) {
				return false;
			}
		}

		return true;
	}
}
