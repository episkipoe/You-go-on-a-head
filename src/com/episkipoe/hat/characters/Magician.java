package com.episkipoe.hat.characters;

import java.util.Arrays;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;

public class Magician extends ImageDrawable {

	public Magician() {
		setFilename("StickMan.png");
	}
	
	int timesClicked=0;
	public void click() { 
		if(timesClicked++ == 8) {
			Main.room.getDialog().add(say(Arrays.asList("Why do you keep poking me?"), 80));
		} else {
			Main.room.getDialog().add(say(Arrays.asList("Hey!"), 40));
		}
	}

}
