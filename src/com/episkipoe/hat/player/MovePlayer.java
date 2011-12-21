package com.episkipoe.hat.player;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.ImageDrawable;
import com.episkipoe.hat.common.MouseMode;
import com.episkipoe.hat.common.Point;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;

public class MovePlayer extends MouseMode {
	public MouseDownHandler mouseDownHandler() {
		return new MouseDownHandler() { 
			@Override
			public void onMouseDown(MouseDownEvent event) {
				Point loc = Main.getPointFromEvent(event);
				for(ImageDrawable img : Main.room.getImagesAtPoint(loc)) {
					img.click();
				}
			}
		};	
	}
	public MouseMoveHandler mouseMoveHandler() {
		return new MouseMoveHandler() { 
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				Main.player.setLocation(Main.getPointFromEvent(event));
			}
		};	
	}
}
