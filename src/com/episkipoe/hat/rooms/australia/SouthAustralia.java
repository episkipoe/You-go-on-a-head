package com.episkipoe.hat.rooms.australia;

import java.util.Arrays;

import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;
import com.episkipoe.hat.rooms.maps.Oceania;

public class SouthAustralia extends SlideshowRoom {
	public SouthAustralia() { 
		addSlide("Lifts.jpg");
		addSlide("BowlerFarmer.jpg");
		addSlide("little pirate.jpg");
	}
	protected void loadSlide() { 
		if(getBackground().equals("Lifts.jpg")) {
			TextUtils.growl(Arrays.asList("Now this is my kind of elevator!"));
		} 
	}
	@Override
	protected Class<? extends Room> getExitRoom() {
		return Oceania.class;
	}

}
