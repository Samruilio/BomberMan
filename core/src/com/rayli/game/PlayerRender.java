package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class PlayerRender extends Component {
	
	ShapeRenderer shapeRenderer;
	GameMap gameMap;
	MapCoord mapCoord;
	Position position;

	public PlayerRender(GameMap gameMap) {
		// TODO Auto-generated constructor stub
		this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(1f, 1f, 1f, 1f);
		this.gameMap = gameMap;
	}
	
	/* (non-Javadoc)
	 * @see com.mygdx.Scene2DTest.Component#initialze()
	 */
	@Override
	public void initialze() {
		// TODO Auto-generated method stub
		this.mapCoord = owner.getComponent(MapCoord.class);
		this.position = owner.getComponent(Position.class);
		position.set(gameMap.mapCoords[mapCoord.row][mapCoord.col].x, gameMap.mapCoords[mapCoord.row][mapCoord.col].y);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		shapeRenderer.setProjectionMatrix(owner.getStage().getCamera().combined);
		shapeRenderer.identity();
		shapeRenderer.circle(position.position.x, position.position.y, 10);
	}

}
