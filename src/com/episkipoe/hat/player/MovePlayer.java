package com.episkipoe.hat.player;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.MouseMode;
import com.episkipoe.hat.common.Point;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;

public class MovePlayer extends MouseMode {
	public MouseDownHandler mouseDownHandler() {
		return new MouseDownHandler() { 
			@Override
			public void onMouseDown(MouseDownEvent event) {
				Point loc = Main.getPointFromEvent(event);
				System.out.println("set: " + loc.x + ", " + loc.y);
				Main.player.setLocation(loc);
			}
		};	
	}
}
