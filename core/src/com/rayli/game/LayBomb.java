package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public class LayBomb extends Component {
	
	BombContainer bombContainer;
	Player player;

	public LayBomb(BombContainer bombContainer) {
		// TODO Auto-generated constructor stub
		this.bombContainer = bombContainer;
	}
	
	/* (non-Javadoc)
	 * @see com.mygdx.Scene2DTest.Component#initialze()
	 */
	@Override
	public void initialze() {
		// TODO Auto-generated method stub
		player = (Player) owner;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub

	}
	
	public void lay() {
		Bomb bomb = new Bomb(5, 250.0001f, 250.0001f);
		bomb.addComponent(new MapCoord(player.getComponent(MapCoord.class).col, player.getComponent(MapCoord.class).row));
		bomb.addComponent(new Position());
		bomb.addComponent(new BombRenderPhysics(player.getComponent(Movement.class).gameMap));
		this.bombContainer.addActor(bomb);
	}

}
