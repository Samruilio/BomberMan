package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.rayli.game.GameMap.Type;

public class Movement extends Component {

	Vector2 velocity;
	Direction direction;
	
	MapCoord mapCoord;
	GameMap gameMap;
	Position position;

	public enum Direction {
		UP, DOWN, RIGHT, LEFT, STOP;
	}

	public Movement(GameMap gameMap) {
		// TODO Auto-generated constructor stub
		direction = Direction.STOP;
		velocity = new Vector2(0, 0);
		this.gameMap = gameMap;
	}
	
	/* (non-Javadoc)
	 * @see com.mygdx.Scene2DTest.Component#initialze()
	 */
	@Override
	public void initialze() {
		// TODO Auto-generated method stub
		mapCoord = owner.getComponent(MapCoord.class);
		position = owner.getComponent(Position.class);
		position.set(gameMap.mapCoords[mapCoord.row][mapCoord.col].x, gameMap.mapCoords[mapCoord.row][mapCoord.col].y);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		switch (direction) {
		case UP:
			moveUp(delta);
			break;
		case DOWN:
			moveDown(delta);
			break;
		case RIGHT:
			moveRight(delta);
			break;
		case LEFT:
			moveLeft(delta);
			break;
		case STOP:
			break;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub

	}
	
	public void moveUp(float delta) {
		if (!isReachable(mapCoord.col, mapCoord.row + 1)) {
			direction = Direction.STOP;
			return;
		}
		position.position.y += velocity.y * delta;
		float dy = gameMap.mapCoords[mapCoord.row + 1][mapCoord.col].y;
		float sy = position.position.y;
		if (Math.abs(dy - sy) < Math.abs(velocity.y * delta)) {
			mapCoord.row += 1;
			direction = Direction.STOP;
		}
	}
	
	public void moveDown(float delta) {
		if (!isReachable(mapCoord.col, mapCoord.row - 1)) {
			direction = Direction.STOP;
			return;
		}
		position.position.y -= velocity.y * delta;
		float dy = gameMap.mapCoords[mapCoord.row - 1][mapCoord.col].y;
		float sy = position.position.y;
		if (Math.abs(dy - sy) < Math.abs(velocity.y * delta)) {
			mapCoord.row -= 1;
			direction = Direction.STOP;
		}
	}
	
	public void moveRight(float delta) {
		if (!isReachable(mapCoord.col + 1, mapCoord.row)) {
			direction = Direction.STOP;
			return;
		}
		position.position.x += velocity.x * delta;
		float dx = gameMap.mapCoords[mapCoord.row][mapCoord.col + 1].x;
		float sx = position.position.x;
		if (Math.abs(dx - sx) < Math.abs(velocity.x * delta)) {
			mapCoord.col += 1;
			direction = Direction.STOP;
		}
	}
	
	public void moveLeft(float delta) {
		if (!isReachable(mapCoord.col - 1, mapCoord.row)) {
			direction = Direction.STOP;
			return;
		}
		position.position.x -= velocity.x * delta;
		float dx = gameMap.mapCoords[mapCoord.row][mapCoord.col - 1].x;
		float sx = position.position.x;
		if (Math.abs(dx - sx) < Math.abs(velocity.x * delta)) {
			mapCoord.col -= 1;
			direction = Direction.STOP;
		}
	}
	
	public boolean isReachable(int col, int row) {
		if (col >= gameMap.cols || col < 0) {
			return false;
		}
		if (row >= gameMap.rows || row < 0) {
			return false;
		}
		if (gameMap.mapTypes[row][col] == Type.SOFT || gameMap.mapTypes[row][col] == Type.HARD) {
			return false;
		}
		return true;
	}

}
