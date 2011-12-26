package com.episkipoe.hat.common.dialog;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.Drawable;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.TextMetrics;

public class DialogElement implements Drawable {
	private List<String> message;
	private Point location; 
	private int duration;
	Runnable postAction;
	
	public DialogElement(List<String> message, Point location, int duration) {
		this.message=message;
		this.location=location; 
		this.duration=duration;	
	}
	public DialogElement(String message, Point location, int duration) {
		this.message=Arrays.asList(message);
		this.location=location; 
		this.duration=duration;	
	}
	public DialogElement(String message, Point location) {
		this.message=Arrays.asList(message);
		this.location=location; 
		this.duration=-1;	
	}
	
	public void setPostAction(Runnable postAction) {
		this.postAction = postAction;
	}
	
	boolean expired() {
		return duration==0;
	}

	@Override
	public Point getLocation() { return location; }
	
	@Override
	public void draw(Context2d context) {
		if(expired()) return; 
		duration--;
		if(duration == 0 && postAction != null) { postAction.run(); }
		double width = 0.0;
		for(String line : message) {
			TextMetrics metrics = context.measureText(line);
			double curWidth = metrics.getWidth();
			if(curWidth > width) width = curWidth;
		}
		final int font_height = 12;
		context.setFont("bold 12px sans-serif");
	
		context.setFillStyle("rgba(0,0,0,0.75)");
		double height = font_height*message.size();
		float pad=4;
		context.fillRect(location.x-pad, location.y-font_height-pad, width+pad*2, height+pad*2);
		
		context.setFillStyle("rgba(255,255,255,1)");
		float y = location.y;
		for(String line : message) {
			context.fillText(line, location.x, y);
			y+=font_height;
		}
	}
}
