package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.rayli.game.GameMap.Type;
import com.rayli.game.Movement.Direction;

public class BombRenderPhysics extends Component {
	
	GameMap gameMap;
	Bomb bomb;
	MapCoord mapCoord;
	Position position;
	
	GameObject shockWaveUp;
	GameObject shockWaveDown;
	GameObject shockWaveRight;
	GameObject shockWaveLeft;
	
	MapCoord shockWaveUpCoord;
	MapCoord shockWaveDownCoord;
	MapCoord shockWaveRightCoord;
	MapCoord shockWaveLeftCoord;
	
	ShapeRenderer shapeRenderer;
	
	float elapsedTime;

	public BombRenderPhysics(GameMap gameMap) {
		// TODO Auto-generated constructor stub
		this.gameMap = gameMap;
		elapsedTime = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.mygdx.Scene2DTest.Component#initialze()
	 */
	@Override
	public void initialze() {
		// TODO Auto-generated method stub
		bomb = (Bomb) owner;
		mapCoord = owner.getComponent(MapCoord.class);
		position = owner.getComponent(Position.class);
		position.set(gameMap.mapCoords[mapCoord.row][mapCoord.col].x, gameMap.mapCoords[mapCoord.row][mapCoord.col].y);
		
		shockWaveUpCoord = new MapCoord(mapCoord.col, mapCoord.row);
		shockWaveDownCoord = new MapCoord(mapCoord.col, mapCoord.row);
		shockWaveRightCoord = new MapCoord(mapCoord.col, mapCoord.row);
		shockWaveLeftCoord = new MapCoord(mapCoord.col, mapCoord.row);
		
		shockWaveUp = new GameObject();
		shockWaveUp.addComponent(shockWaveUpCoord);
		shockWaveUp.addComponent(new Position());
		shockWaveUp.addComponent(new Movement(this.gameMap));
		
		shockWaveDown = new GameObject();
		shockWaveDown.addComponent(shockWaveDownCoord);
		shockWaveDown.addComponent(new Position());
		shockWaveDown.addComponent(new Movement(this.gameMap));
		
		shockWaveRight = new GameObject();
		shockWaveRight.addComponent(shockWaveRightCoord);
		shockWaveRight.addComponent(new Position());
		shockWaveRight.addComponent(new Movement(this.gameMap));
		
		shockWaveLeft = new GameObject();
		shockWaveLeft.addComponent(shockWaveLeftCoord);
		shockWaveLeft.addComponent(new Position());
		shockWaveLeft.addComponent(new Movement(this.gameMap));
		
		this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(1f, 0.79f, 0.06f, 1f);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		elapsedTime += delta;
		if (elapsedTime > 2 && !bomb.isExploded) {
			bomb.isExploding = true;
		}
		explosion();
		shockWaveUp.act(delta);
		shockWaveDown.act(delta);
		shockWaveRight.act(delta);
		shockWaveLeft.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		shapeRenderer.setProjectionMatrix(owner.getStage().getCamera().combined);
		shapeRenderer.identity();
		if (!bomb.isExploding) {
			shapeRenderer.circle(position.position.x, position.position.y, 15);
		} else {
			if (spreadUp()) {
				shapeRenderer.circle(shockWaveUp.getComponent(Position.class).position.x, shockWaveUp.getComponent(Position.class).position.y, 15);
			}
			if (spreadDown()) {
				shapeRenderer.circle(shockWaveDown.getComponent(Position.class).position.x, shockWaveDown.getComponent(Position.class).position.y, 15);
			}
			if (spreadRight()) {
				shapeRenderer.circle(shockWaveRight.getComponent(Position.class).position.x, shockWaveRight.getComponent(Position.class).position.y, 15);
			}
			if (spreadLeft()) {
				shapeRenderer.circle(shockWaveLeft.getComponent(Position.class).position.x, shockWaveLeft.getComponent(Position.class).position.y, 15);
			}
		}
	}
	
	public void explosion() {
		if (bomb.isExploding) {
			if (!spreadUp() && !spreadDown() && !spreadRight() && !spreadLeft()) {
				bomb.isExploded = true;
			}
		}
	}
	
	public boolean spreadUp() {
		if (shockWaveUp.getComponent(Movement.class).direction != Direction.STOP) {
			return true;
		}
		if (shockWaveUpCoord.row + 1 >= gameMap.rows) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveUpCoord.row + 1][shockWaveUpCoord.col] == Type.HARD) {
			return false;
		}
		if (Math.abs(shockWaveUpCoord.row + 1 - mapCoord.row) > bomb.explosionRange) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveUpCoord.row + 1][shockWaveUpCoord.col] == Type.SOFT) {
			gameMap.mapTypes[shockWaveUpCoord.row + 1][shockWaveUpCoord.col] = Type.EMPTY;
		}
		shockWaveUp.getComponent(Movement.class).direction = Direction.UP;
		shockWaveUp.getComponent(Movement.class).velocity = bomb.explosionVelocity;
		return true;
	}
	
	public boolean spreadDown() {
		if (shockWaveDown.getComponent(Movement.class).direction != Direction.STOP) {
			return true;
		}
		if (shockWaveDownCoord.row - 1 < 0) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveDownCoord.row - 1][shockWaveDownCoord.col] == Type.HARD) {
			return false;
		}
		if (Math.abs(shockWaveDownCoord.row - 1 - mapCoord.row) > bomb.explosionRange) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveDownCoord.row - 1][shockWaveDownCoord.col] == Type.SOFT) {
			gameMap.mapTypes[shockWaveDownCoord.row - 1][shockWaveDownCoord.col] = Type.EMPTY;
		}
		shockWaveDown.getComponent(Movement.class).direction = Direction.DOWN;
		shockWaveDown.getComponent(Movement.class).velocity = bomb.explosionVelocity;
		return true;
	}
	
	public boolean spreadRight() {
		if (shockWaveRight.getComponent(Movement.class).direction != Direction.STOP) {
			return true;
		}
		if (shockWaveRightCoord.col + 1 >= gameMap.cols) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveRightCoord.row][shockWaveRightCoord.col + 1] == Type.HARD) {
			return false;
		}
		if (Math.abs(shockWaveRightCoord.col + 1 - mapCoord.col) > bomb.explosionRange) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveRightCoord.row][shockWaveRightCoord.col + 1] == Type.SOFT) {
			gameMap.mapTypes[shockWaveRightCoord.row][shockWaveRightCoord.col + 1] = Type.EMPTY;
		}
		shockWaveRight.getComponent(Movement.class).direction = Direction.RIGHT;
		shockWaveRight.getComponent(Movement.class).velocity = bomb.explosionVelocity;
		return true;
	}
	
	public boolean spreadLeft() {
		if (shockWaveLeft.getComponent(Movement.class).direction != Direction.STOP) {
			return true;
		}
		if (shockWaveLeftCoord.col - 1 < 0) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveLeftCoord.row][shockWaveLeftCoord.col - 1] == Type.HARD) {
			return false;
		}
		if (Math.abs(shockWaveLeftCoord.col - 1 - mapCoord.col) > bomb.explosionRange) {
			return false;
		}
		if (gameMap.mapTypes[shockWaveLeftCoord.row][shockWaveLeftCoord.col - 1] == Type.SOFT) {
			gameMap.mapTypes[shockWaveLeftCoord.row][shockWaveLeftCoord.col - 1] = Type.EMPTY;
		}
		shockWaveLeft.getComponent(Movement.class).direction = Direction.LEFT;
		shockWaveLeft.getComponent(Movement.class).velocity = bomb.explosionVelocity;
		return true;
	}

}
