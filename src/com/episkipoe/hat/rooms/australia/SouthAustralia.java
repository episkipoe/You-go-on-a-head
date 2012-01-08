package com.episkipoe.hat.rooms.australia;

import java.util.Arrays;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.Drawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.interact.BackgroundAction;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;
import com.episkipoe.hat.rooms.maps.Oceania;
import com.episkipoe.hat.tricks.IncreaseSkill;

public class SouthAustralia extends SlideshowRoom {
	public SouthAustralia() { 
		addSlide("Lifts.jpg");
		addSlide("BowlerFarmer.jpg");
		addSlide("LittlePirate.jpg");
	}
	protected void loadSlide() { 
		if(getBackground().equals("Lifts.jpg")) {
			TextUtils.growl(Arrays.asList("Now this is my kind of elevator!"));
		} else if(getBackground().equals("LittlePirate.jpg")) {
			String msg[] = {"You pick up some piratical pointers from the midget pirate","Remember:  go for their knees"};
			IncreaseSkill action = new IncreaseSkill("Plunderin.png", 10, Arrays.asList(msg));
			Drawable pirate = new BackgroundAction(new Point(194,230), new Point(285, 324), action);
			action.removeOnRun(pirate);
			addDrawable(pirate);
		} 
	}
	@Override
	protected Class<? extends Room> getExitRoom() {
		return Oceania.class;
	}

}
