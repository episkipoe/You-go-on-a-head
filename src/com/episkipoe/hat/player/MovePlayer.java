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
				Point point = Main.getPointFromEvent(event);
				Main.player.setLocation(point);
				if(Main.pointIsClickable(point)) {
					Main.player.setAlpha(0.1);
				} else {
					Main.player.setAlpha(1.0);
				}
			}
		};	
	}
}
