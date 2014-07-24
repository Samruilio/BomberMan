package com.rayli.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public class MapCoord extends Component {
	
	int col;
	int row;

	public MapCoord() {
		// TODO Auto-generated constructor stub
		this(0, 0);
	}
	
	public MapCoord(int col, int row) {
		// TODO Auto-generated constructor stub
		set(col, row);
	}
	
	public void set(int col, int row) {
		this.col = col;
		this.row = row;
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
