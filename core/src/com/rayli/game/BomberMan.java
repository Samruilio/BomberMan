package com.rayli.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BomberMan extends Game {

	Stage stage;
	BitmapFont font;

	/**
	 * Initializes all of the in-game objects
	 */
	@Override
	public void create() {
		stage = new Stage(new StretchViewport(800, 480));
		
		font = new BitmapFont();
		
		GameMap gameMap = new GameMap(26, 16, 30, 30);
		gameMap.addComponent(new MapRender());
		
		BombContainer bombContainer = new BombContainer();
		gameMap.addActor(bombContainer);
		
		Player player = new Player();
		player.addComponent(new Position());
		player.addComponent(new MapCoord(0, 0));
		player.addComponent(new Movement(gameMap));
		player.addComponent(new PlayerRender(gameMap));
		player.addComponent(new MovementController());
		player.addComponent(new LayBomb(bombContainer));
		gameMap.addActor(player);
		
		stage.addActor(gameMap);
		//stage.addActor(player);
	}

	/**
	 * Game loop.
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		stage.getBatch().begin();
		font.draw(stage.getBatch(), ""+Gdx.graphics.getFramesPerSecond(), 0, 470);
		stage.getBatch().end();
	}

	/**
	 * Update the camera if the game screen is resized.
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
}