package com.episkipoe.hat.common;

import com.google.gwt.canvas.dom.client.Context2d;

public class DialogElement implements Drawable {
	private String message;
	private Point location; 
	private int duration;
	Runnable postAction;
	
	public DialogElement(String message, Point location, int duration) {
		this.message=message;
		this.location=location; 
		this.duration=duration;	
	}
	public DialogElement(String message, Point location) {
		this.message=message;
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
		if(duration == 0 && postAction != null) postAction.run();
		context.fillText(message, location.x, location.y);
	}
}
