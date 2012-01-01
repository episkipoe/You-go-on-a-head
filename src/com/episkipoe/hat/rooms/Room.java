package com.episkipoe.hat.rooms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.Dialog;
import com.episkipoe.hat.common.draw.Drawable;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.ImageUtils;
import com.episkipoe.hat.common.interact.Clickable;
import com.google.gwt.canvas.dom.client.Context2d;

public abstract class Room {
	public Room() { }

	/**
	 * 
	 * @return the list of image file names that should be found in the {@link ImageLibrary}
	 * 	this list may, but does not need to, include the background image 
	 *  or images associated with {@link Drawable}s loaded in the constructor
	 */
	public Collection<String> getRequiredImages() { return new ArrayList<String>(); }
	
	/**
	 * 
	 *  the image in the main {@link ImageLibrary} to draw full-screen behind everything
	 */
	private String background;
	public final void setBackground(String background) { this.background = background; }
	protected final String getBackground() { return background; }

	/**
	 * called after all required images have been loaded
	 * 	The scene should start here.
	 */
	protected void onLoad() {}
	/**
	 * called every time the user clicks to enter the room
	 * 	Note:  not all images are guaranteed to be loaded at this point
	 */
	public void onEnter() { }
	
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
	public final void clearDrawables() { getDrawables().clear(); }
	
	private List<Clickable> clickables; 
	private final List<Clickable> getClickables() {
		if(clickables==null) clickables= new ArrayList<Clickable> ();
		return clickables;
	}
	public final void addClickable(Clickable c) {
		getClickables().add(c);
	}	
	
	private Dialog dialog;
	public final Dialog getDialog() { 
		if(dialog == null) dialog = new Dialog();
		return dialog;
	}
	public final void draw(Context2d context) {
		if(getBackground() != null) ImageUtils.draw(context, getBackground());
		preDraw(context);
		List<Drawable> itemsToPrune=new ArrayList<Drawable>();
		for(Drawable d: getDrawables()) {
			d.draw(context);
			if(d instanceof Dialog) {
				Dialog dialog = (Dialog) d;
				if(dialog.isEmpty()) {
					itemsToPrune.add(d);
				}
			}
		}
		getDrawables().removeAll(itemsToPrune);
		postDraw(context);
		getDialog().draw(context);
	}

	/**
	 * 
	 * @param point
	 * @throws Exception 
	 */
	public final void click(Point point) throws Exception {
		for(Drawable d: getDrawables()) {
			if(d instanceof Clickable) {
				Clickable target = (Clickable)d;
				if(target.intersectsWith(point)) target.click();
			}
		}
		for(Clickable c: getClickables()) {
			if(c.intersectsWith(point)) c.click();
		}
	}
	
	public final boolean pointIsClickable(Point point) {
		for(Drawable d: getDrawables()) {
			if(d instanceof Clickable) {
				Clickable target = (Clickable)d;
				if(target.intersectsWith(point)) return true;
			}
		}
		for(Clickable c: getClickables()) {
			if(c.intersectsWith(point)) return true;
		}		
		return false;
	}
	
	public final Collection<String> getAllImages() {
		List<String> images = new ArrayList<String>();
		if(getBackground() != null) images.add(getBackground());
		
		for(Drawable d: getDrawables()) {
			if(d instanceof ImageDrawable) {
				ImageDrawable img = (ImageDrawable)d;	
				images.add(img.getFilename());
			}
		}
		
		Collection<String> required = getRequiredImages();
		if(required != null) images.addAll(required);
		
		return images;
	}
	public final void imageLoaded() {
		for(String img : getAllImages()) {
			if(!Main.images.contains(img)) return ;
		}
		onLoad();
	}


}
