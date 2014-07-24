package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class MapRender extends Component {
	
	ShapeRenderer shapeRenderer;

	public MapRender() {
		// TODO Auto-generated constructor stub
		this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(1f, 1f, 1f, 1f);
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
		GameMap gameMap = (GameMap) owner;
		for (int i = 0; i < gameMap.rows; i++) {
			for (int j = 0; j <  gameMap.cols; j++) {
				Vector2 p = gameMap.mapCoords[i][j];
				switch (gameMap.mapTypes[i][j]) {
				case EMPTY:
					shapeRenderer.setColor(0, 0, 0, 1f);
					break;
				case SOFT:
					shapeRenderer.setColor(0, 1, 0, 1f);
					break;
				case HARD:
					shapeRenderer.setColor(1, 0, 0, 1f);
					break;
				}
				shapeRenderer.rect(p.x - gameMap.offsetx, p.y - gameMap.offsety, 
						gameMap.cellWidth, gameMap.cellHeight, gameMap.cellWidth / 2, gameMap.cellHeight / 2, gameMap.getRotation());
			}
		}
	}

}
