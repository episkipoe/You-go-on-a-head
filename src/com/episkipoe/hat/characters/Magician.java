package com.episkipoe.hat.characters;

import com.episkipoe.hat.common.ImageDrawable;

public class Magician extends ImageDrawable {

	@Override
	public String getFilename() {
		return "StickMan.png";
	}
	
	public void click() { 
		say("Hey", 80);
	}

}
