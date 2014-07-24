package com.rayli.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class BombContainer extends GameObject {

	public BombContainer() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.mygdx.Scene2DTest.GameObject#update(float)
	 */
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
		for (Actor actor : this.getChildren()) {
			Bomb bomb = (Bomb) actor;
			if (bomb.isExploded) {
				this.removeActor(bomb);
			}
		}
	}

}
