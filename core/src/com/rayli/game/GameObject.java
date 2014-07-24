package com.rayli.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GameObject extends Group implements Agent {
	
	private Map<Class<? extends Component>, Component> componentMap = new HashMap<Class<? extends Component>, Component>();

	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	
	public void addComponent(Component com) {
		com.owner = this;
		componentMap.put(com.getClass(), com);
		com.initialze();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> com) {
		return (T) componentMap.get(com);//com.cast(componentMap.get(com));
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Group#act(float)
	 */
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		for (Component com : componentMap.values()) {
			com.update(delta);
		}
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Group#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		for (Component com : componentMap.values()) {
			com.draw(batch, parentAlpha);
		}
		super.draw(batch, parentAlpha);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		return false;
	}

}
