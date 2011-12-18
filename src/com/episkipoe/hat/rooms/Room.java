package com.episkipoe.hat.rooms;

import java.util.ArrayList;
import java.util.List;

import com.episkipoe.hat.common.Drawable;
import com.episkipoe.hat.common.ImageUtils;
import com.google.gwt.canvas.dom.client.Context2d;

public abstract class Room {
	public Room() { }
	
	private List<Drawable> drawables; 
	private List<Drawable> getDrawables() {
		if(drawables==null) drawables = new ArrayList<Drawable> ();
		return drawables;
	}
	public void addDrawable(Drawable d) {
		getDrawables().add(d);
	}
	protected String getBackground() { return null; }
	protected void preDraw(Context2d context) {}
	public final void draw(Context2d context) {
		if(getBackground() != null) ImageUtils.draw(context, getBackground());
		for(Drawable d: getDrawables()) d.draw(context);
	}
	protected void postDraw(Context2d context) {}
}
