package com.episkipoe.hat.rooms.mexico;

import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;

public class MayanRoom extends SlideshowRoom {
	public MayanRoom() { 
		addSlide("Mayan0.JPG");
		addSlide("Mayan1.JPG");
		addSlide("Waves.JPG");
		addSlide("NoShoes.JPG");
		addSlide("Lizard.JPG");
		addSlide("Mayan.jpg");
	}

	@Override
	protected Class<? extends Room> getExitRoom() {
		return MariachiRoom.class;
	}
}
