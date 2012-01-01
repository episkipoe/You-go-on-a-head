package com.episkipoe.hat.common.draw;

import java.util.Collection;

import com.episkipoe.hat.common.Point;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.TextMetrics;

public class TextUtils {
	public static String font = "bold 12px sans-serif";
	public static int font_height = 13;
	private static final float pad=4;
	
	public static double getTextWidth(Context2d context, Collection<String> message) {
		double width = 0.0;
		for(String line : message) {
			TextMetrics metrics = context.measureText(line);
			double curWidth = metrics.getWidth();
			if(curWidth > width) width = curWidth;
		}
		return width;
	}
	
	public static double getTextHeight(Context2d context, Collection<String> message) {
		return font_height*message.size();
	}

	/**
	 * @param context
	 * @param message  
	 * @param location  The upper-left location of the text
	 * @param backgroundStyle
	 * @param textStyle
	 */
	public static void drawText(Context2d context, Collection<String> message, Point location, String backgroundStyle, String textStyle) {
		double width = getTextWidth(context, message);
		double height = getTextHeight(context, message);
		context.setFont(font);
	
		context.setFillStyle(backgroundStyle);
		context.fillRect(location.x-pad, location.y-font_height-pad, width+pad*2, height+pad*2);
		
		context.setFillStyle(textStyle);
		float y = location.y;
		for(String line : message) {
			context.fillText(line, location.x, y);
			y+=font_height;
		}
	}

	/**
	 * 
	 * @param textLocation
	 * @param message
	 * @param point
	 * @return true if point overlaps with the message drawn at textLocation
	 */
	public static boolean overlaps(Context2d context, Point textLocation, Collection<String> message, Point point) {
		float x1 = textLocation.x-pad;
		if(point.x < x1) return false;
		double x2 = textLocation.x+getTextWidth(context, message)+pad;
		if(point.x > x2) return false;
		float y1 = textLocation.y-font_height-pad;
		if(point.y < y1) return false;
		double y2 =  textLocation.y+getTextHeight(context, message)+pad;
		if(point.y > y2) return false;
		return true;
	}
}