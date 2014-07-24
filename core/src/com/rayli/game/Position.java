package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Position extends Component {

	Vector2 position;

	public Position() {
		// TODO Auto-generated constructor stub
		position = new Vector2();
	}
	
	public void set(float x, float y) {
		position.set(x, y);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub

	}

}
