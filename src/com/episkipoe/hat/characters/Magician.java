package com.episkipoe.hat.characters;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;

public class Magician extends ImageDrawable {

	public Magician() {
		setFilename("StickMan.png");
	}
	
	public void click() { 
		Main.room.getDialog().add(say("Hey!", 80));
	}

}
