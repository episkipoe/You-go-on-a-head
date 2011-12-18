package com.episkipoe.hat.common;

import com.episkipoe.hat.client.Main;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public class ImageUtils {
	
	/**
	 * Draw an image at the specified location
	 * @param filename
	 * @param location
	 */
	public static void draw(Context2d context, String filename, Point location) {
		ImageElement image;
		try {
			image = Main.images.getImage(filename);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} 
	    context.save();
	    context.translate(location.x, location.y);
	    context.drawImage(image, 0, 0);
	    context.restore();	
	}

	/**
	 * Draw an image at 0,0 (i.e. background)
	 * @param filename
	 */
	public static void draw(Context2d context, String filename) {
		ImageElement image;
		try {
			image = Main.images.getImage(filename);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} 
	    context.drawImage(image, 0, 0);
	}
	
}
