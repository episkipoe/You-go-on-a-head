package com.episkipoe.hat.rooms;

import java.util.ArrayList;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Dialog;
import com.episkipoe.hat.common.Drawable;
import com.episkipoe.hat.common.ImageDrawable;
import com.episkipoe.hat.common.ImageUtils;
import com.episkipoe.hat.common.Point;
import com.google.gwt.canvas.dom.client.Context2d;

public abstract class Room {
	public Room() { }

	/**
	 * 
	 * @return the list of image file names that should be found in the {@link ImageLibrary}
	 * 	this list may, but does not need to, include the background image 
	 *  or images associated with {@link Drawable}s loaded in the constructor
	 */
	public List<String> getRequiredImages() { return new ArrayList<String>(); }
	
	/**
	 * 
	 * @return the image in the main {@link ImageLibrary} to draw full-screen behind everything
	 */
	protected String getBackground() { return null; }

	/**
	 * called after all required images have been loaded
	 * 	The scene should start here.
	 */
	protected void roomLoaded() {}
	
	/**
	 * Called at the beginning of the Room's draw method (but after the background)
	 * @param context
	 */
	protected void preDraw(Context2d context) {}
	/**
	 * Called at the end of the Room's draw method
	 * @param context
	 */
	protected void postDraw(Context2d context) {}

	/*
	 * Final methods
	 */
	
	
	private List<Drawable> drawables; 
	private final List<Drawable> getDrawables() {
		if(drawables==null) drawables = new ArrayList<Drawable> ();
		return drawables;
	}
	public final void addDrawable(Drawable d) {
		getDrawables().add(d);
	}
	private Dialog dialog;
	public final Dialog getDialog() { 
		if(dialog == null) dialog = new Dialog();
		return dialog;
	}
	public final void draw(Context2d context) {
		if(getBackground() != null) ImageUtils.draw(context, getBackground());
		preDraw(context);
		for(Drawable d: getDrawables()) d.draw(context);
		postDraw(context);
		getDialog().draw(context);
	}

	/**
	 * 
	 * @param point
	 * @return  all of the {@link ImageDrawable}s that intersect with the point
	 */
	public final List<ImageDrawable> getImagesAtPoint(Point point) {
		List<ImageDrawable> images = new ArrayList<ImageDrawable>();
		for(Drawable d: getDrawables()) {
			if(d instanceof ImageDrawable) {
				ImageDrawable img = (ImageDrawable)d;
				if(img.intersectsWith(point)) images.add(img);
			}
		}
		return images;
	}
	
	public final List<String> getAllImages() {
		List<String> images = new ArrayList<String>();
		if(getBackground() != null) images.add(getBackground());
		
		for(Drawable d: getDrawables()) {
			if(d instanceof ImageDrawable) {
				ImageDrawable img = (ImageDrawable)d;	
				images.add(img.getFilename());
			}
		}
		
		List<String> required = getRequiredImages();
		if(required != null) images.addAll(required);
		
		return images;
	}
	public final void imageLoaded() {
		for(String img : getAllImages()) {
			if(!Main.images.contains(img)) return ;
		}
		roomLoaded();
	}
}
