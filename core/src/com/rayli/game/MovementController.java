package com.rayli.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.rayli.game.Movement.Direction;

public class MovementController extends Component implements InputProcessor {
	
	Map<Integer, Boolean> keyMap;
	Movement movement;

	public MovementController() {
		// TODO Auto-generated constructor stub
		Gdx.input.setInputProcessor(this);
		keyMap = new HashMap<Integer, Boolean>();
		keyMap.put(Input.Keys.UP, false);
		keyMap.put(Input.Keys.DOWN, false);
		keyMap.put(Input.Keys.RIGHT, false);
		keyMap.put(Input.Keys.LEFT, false);
	}
	
	/* (non-Javadoc)
	 * @see com.mygdx.Scene2DTest.Component#initialze()
	 */
	@Override
	public void initialze() {
		// TODO Auto-generated method stub
		movement = owner.getComponent(Movement.class);
		movement.velocity.set(80.001f, 80.001f);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if (movement.direction == Direction.STOP) {
			if (keyMap.get(Input.Keys.UP)) {
				movement.direction = Direction.UP;
			}
			if (keyMap.get(Input.Keys.DOWN)) {
				movement.direction = Direction.DOWN;
			}
			if (keyMap.get(Input.Keys.RIGHT)) {
				movement.direction = Direction.RIGHT;
			}
			if (keyMap.get(Input.Keys.LEFT)) {
				movement.direction = Direction.LEFT;
			}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch (keycode) {
		case Input.Keys.UP:
			keyMap.put(Input.Keys.UP, true);
			break;
		case Input.Keys.DOWN:
			keyMap.put(Input.Keys.DOWN, true);
			break;
		case Input.Keys.RIGHT:
			keyMap.put(Input.Keys.RIGHT, true);
			break;
		case Input.Keys.LEFT:
			keyMap.put(Input.Keys.LEFT, true);
			break;
		case Input.Keys.SPACE:
			((Player) owner).getComponent(LayBomb.class).lay();
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		switch (keycode) {
		case Input.Keys.UP:
			keyMap.put(Input.Keys.UP, false);
			break;
		case Input.Keys.DOWN:
			keyMap.put(Input.Keys.DOWN, false);
			break;
		case Input.Keys.RIGHT:
			keyMap.put(Input.Keys.RIGHT, false);
			break;
		case Input.Keys.LEFT:
			keyMap.put(Input.Keys.LEFT, false);
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
