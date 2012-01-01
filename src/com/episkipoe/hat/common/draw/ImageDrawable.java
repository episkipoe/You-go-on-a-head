package com.episkipoe.hat.common.draw;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.Dialog;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.dialog.Speaker;
import com.episkipoe.hat.common.interact.Clickable;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public abstract class ImageDrawable implements Drawable, Clickable, Speaker {
	public ImageDrawable() { }
	
	/*
	 * Final methods 
	 */
	
	/**
	 * the name of the current image file.  must be a valid entry in the {@link ImageLibrary}
	 */
	private String fileName;
	public final String getFilename() { return fileName; }
	public final void setFilename(String fileName) { this.fileName = fileName; }

	/**
	 * the current x,y position of the drawable on the canvas
	 */
	private Point location=null;
	public final Point getLocation() {
		if(location==null) location = new Point();
		return location;
	}
	public final void setLocation(Point location) { this.location = location; }
	
	private double alpha=1.0;
	public final void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public final ImageElement getImageElement() { 
		return Main.images.getImage(getFilename());	
	}
	
	public final void draw(Context2d context) {
		if(alpha<1.0) context.setGlobalAlpha(alpha);
		ImageUtils.draw(context, getFilename(), getLocation());
		if(alpha<1.0) context.setGlobalAlpha(1.0);
	}	

	public final boolean intersectsWith(Point point) {
		ImageElement img = getImageElement();
		if(img==null) return false;
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
	
	public final DialogElement say(String message) {
		return say(Arrays.asList(message), Dialog.DEFAULT_DURATION);
	}
	
	@Override
	public final DialogElement say(List<String> message, int duration) {
		ImageElement img = getImageElement();
		if(img==null) {
			System.out.println("WARNING:  Cannot say: " + message + " img is null");
			return null;
		}
		double x = getLocation().x+img.getWidth()*0.5;
		double y = getLocation().y-img.getHeight()*0.5;
		return new DialogElement(message, new Point(x,y), duration);
	}
}
