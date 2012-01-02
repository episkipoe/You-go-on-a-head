package com.episkipoe.hat.rooms.caribbean;

import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;

public class Underwater extends SlideshowRoom {
	public Underwater() { 
		addSlide("Underwater0.png");
		addSlide("Underwater-SeaHorse.png");
		addSlide("Underwater-Turtle.png");
	}
	
	@Override
	protected Class<? extends Room> getExitRoom() {
		return DominicaDiveShop.class;
	}

}
