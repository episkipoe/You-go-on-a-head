package com.episkipoe.hat.common;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.rooms.Room;

public class Door extends ImageDrawable {
	private Class<? extends Room> destination;
	public Door(Point location, Class<? extends Room> destination, String fileName) {
		setLocation(location);
		setDestination(destination);
		setFilename(fileName);
	}
	public Door(Point location, Class<? extends Room> destination) {
		setLocation(location);
		setFilename("door.png");
		setDestination(destination);
	}
	public Door() {
		setFilename("door.png");
	}
	public void setDestination(Class<? extends Room> destination) {
		this.destination = destination;
	}
	
	@Override
	public void click() throws Exception {
		if(destination==null) {
			Main.goBack();
		} else {
			Main.switchRoom(destination);
		}
	}

}
