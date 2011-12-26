package com.episkipoe.hat.common.dialog;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.Drawable;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 *  A collection of {@link DialogElement}s
 *
 */
public class Dialog implements Drawable {
	public static final int DEFAULT_DURATION = 100;
	private Queue<DialogElement> elements;
	public Dialog() { 
		elements = new LinkedList<DialogElement>();
	}

	public void add(DialogElement element) { 
		elements.add(element); 
	}
	
	public void add(String message, Point location) {
		elements.add(new DialogElement(message, location));
	}

	public void say(String message, ImageDrawable speaker) {
		elements.add(speaker.say(message, DEFAULT_DURATION));
	}
	public void say(List<String> message, ImageDrawable speaker) {
		elements.add(speaker.say(message, DEFAULT_DURATION));
	}
	
	@Override
	public void draw(Context2d context) {
		if(elements.peek() == null) return;
		DialogElement front = elements.peek();
		front.draw(context);
		if(front.expired()) { elements.remove(); }
	}

	@Override
	public Point getLocation() { 
		if(elements.peek() == null) return null;
		return elements.peek().getLocation();
	}

}
