package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Component {
	public GameObject owner;
	public void initialze() {
	}
	public abstract void update(float delta);
	public abstract void draw(Batch batch, float parentAlpha);
}
