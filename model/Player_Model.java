
package model;

public abstract class Player_Model {

	public abstract boolean isHuman();

	public abstract void updateGame(Game game);

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[isHuman=" + isHuman() + "]";
	}
}
