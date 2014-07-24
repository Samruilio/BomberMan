package com.rayli.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameMap extends GameObject {
	
	int cols;
	int rows;
	float cellWidth;
	float cellHeight;
	float offsetx;
	float offsety;
	Vector2[][] mapCoords;
	Type[][] mapTypes;
	
	public enum Type {
		EMPTY, SOFT, HARD;
	}

	public GameMap() {
		// TODO Auto-generated constructor stub
		this(0, 0, 0, 0);
	}
	
	public GameMap(int cols, int rows, float cellWidth, float cellHeight) {
		// TODO Auto-generated constructor stub
		set(cols, rows, cellWidth, cellHeight);
	}
	
	public void set(int cols, int rows, float cellWidth, float cellHeight) {
		this.cols = cols;
		this.rows = rows;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.offsetx = cellWidth / 2;
		this.offsety = cellHeight / 2;
		mapCoords = new Vector2[rows][cols];
		mapTypes = new Type[rows][cols];
		initMap();
	}
	
	public void initMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Vector2 p = new Vector2(j * cellWidth + offsetx, i * cellHeight + offsety);
				mapCoords[i][j] = p;
				mapTypes[i][j] = Type.values()[MathUtils.random(0, 2)];
			}
		}
	}

}
