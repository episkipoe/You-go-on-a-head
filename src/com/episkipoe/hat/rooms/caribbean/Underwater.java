package com.episkipoe.hat.rooms.caribbean;

import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;

public class Underwater extends SlideshowRoom {
	public Underwater() { 
		addSlide("Underwater0.jpg");
		addSlide("Underwater1.jpg");
		addSlide("puffer.JPG");
		addSlide("ray.JPG");
		addSlide("Food.JPG");
		addSlide("Shark.JPG");
		addSlide("Underwater-SeaHorse.png");
		addSlide("Underwater-Turtle.png");
	}
	
	@Override
	protected Class<? extends Room> getExitRoom() {
		return GoDiveRoom.class;
	}

}
