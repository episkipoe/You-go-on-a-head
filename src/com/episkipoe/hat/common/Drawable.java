package com.episkipoe.hat.common;

import com.google.gwt.canvas.dom.client.Context2d;

public abstract class Drawable {
	abstract protected String getFilename();
	
	public Drawable() { }

	public int getRadius() { return 10; }
	
	private Point location=null;
	public final Point getLocation() {
		if(location==null) location = new Point();
		return location;
	}
	public final void setLocation(Point location) { this.location = location; }

	public final void draw(Context2d context) {
		ImageUtils.draw(context, getFilename(), getLocation());
	}	
	
	public boolean intersectsWith(Point point) {
		return getLocation().inRange(point, getRadius());
	}
}
