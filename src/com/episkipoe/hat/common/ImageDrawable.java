package com.episkipoe.hat.common;

import com.episkipoe.hat.client.Main;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public abstract class ImageDrawable implements Drawable {
	abstract public String getFilename();
	
	public ImageDrawable() { }
	
	private Point location=null;
	public final Point getLocation() {
		if(location==null) location = new Point();
		return location;
	}
	public final void setLocation(Point location) { this.location = location; }

	public ImageElement getImageElement() throws Exception { 
		return Main.images.getImage(getFilename());	
	}
	public final void draw(Context2d context) {
		ImageUtils.draw(context, getFilename(), getLocation());
	}	

	public boolean intersectsWith(Point point) {
		ImageElement img;
		try {
			img = getImageElement();
		} catch (Exception e) {
			return false;
		}
		double left = getLocation().x-img.getWidth()*0.5;
		if(point.x < left) return false;
		double right = getLocation().x+img.getWidth()*0.5;
		if(point.x > right) return false;
		double bottom = getLocation().y+img.getHeight()*0.5;
		if(point.y > bottom) return false;
		double top = getLocation().y-img.getHeight()*0.5;
		if(point.y < top) return false;
		return true;
	}
	
	public DialogElement say(String message) {
		return say(message, Dialog.DEFAULT_DURATION);
	}
	
	public DialogElement say(String message, int duration) {
		ImageElement img;
		try {
			img = getImageElement();
		} catch (Exception e) {
			return null ;
		}
		double x = getLocation().x+img.getWidth()*0.5;
		double y = getLocation().y-img.getHeight()*0.5;
		return new DialogElement(message, new Point(x,y), duration);
	}
	public void click() { }
}
