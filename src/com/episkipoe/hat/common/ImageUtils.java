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
		ImageElement image = Main.images.getImage(filename);
		if(image==null) return ;
		location = centerPointOnImage(location, image);
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
		ImageElement image = Main.images.getImage(filename);
		if(image==null) return ;
	    context.drawImage(image, 0, 0);
	}
	
	public static Point centerPointOnImage(Point point, ImageElement image) {
		float x = (float) (point.x - (image.getWidth()*0.5));
		float y = (float) (point.y - (image.getHeight()*0.5));
		return new Point(x,y);
	}
}
