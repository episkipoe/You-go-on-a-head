package com.episkipoe.hat.common.dialog;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.Drawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.google.gwt.canvas.dom.client.Context2d;

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
	public void draw(Context2d context) {
		if(expired()) return; 
		duration--;
		if(duration == 0 && postAction != null) { postAction.run(); }
		TextUtils.drawText(context, message, location, "rgba(0,0,0,0.75)", "rgba(255,255,255,1)");
	}
}
