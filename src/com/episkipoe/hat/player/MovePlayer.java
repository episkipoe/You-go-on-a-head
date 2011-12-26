package com.episkipoe.hat.player;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.MouseMode;
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
				try {
					Main.click(loc);
				} catch (Exception e) {
					e.printStackTrace();
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
