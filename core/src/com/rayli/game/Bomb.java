package com.rayli.game;

import com.badlogic.gdx.math.Vector2;

public class Bomb extends GameObject {
	
	Vector2 explosionVelocity;
	int explosionRange;
	boolean isExploding;
	boolean isExploded;

	public Bomb() {
		// TODO Auto-generated constructor stub
		this(0, 0, 0);
	}
	
	public Bomb(int expRange, float vx, float vy) {
		// TODO Auto-generated constructor stub
		this.set(expRange, vx, vy);
	}
	
	public void set(int expRange, float vx, float vy) {
		explosionVelocity = new Vector2();
		this.explosionRange = expRange;
		this.explosionVelocity.set(vx, vy);
		isExploded = false;
	}

}
