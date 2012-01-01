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
		if(Main.player.wearing(Arrays.asList("SantaHat.png"))) {
			Main.room.getDialog().add(say(Arrays.asList("Merry Christmas!"), 40));
			return;
		}
		if(Main.player.wearing(Arrays.asList("PirateHat.png"))) {
			Main.room.getDialog().add(say(Arrays.asList("Avast ye matey!"), 40));
			return;
		}
		if(Main.player.wearing(Arrays.asList("RedPartyHat.png"))) {
			Main.room.getDialog().add(say(Arrays.asList("Wow!  That's a nice hat."), 40));
			return;
		}
		if(Main.player.wearing(Arrays.asList("ChickenMask.png"))) {
			Main.room.getDialog().add(say(Arrays.asList("Aaaaahhhh!!!."), 40));
			return;		
		}
		if(timesClicked++ == 8) {
			Main.room.getDialog().add(say(Arrays.asList("Why do you keep poking me?"), 80));
		} else {
			Main.room.getDialog().add(say(Arrays.asList("Hey!"), 40));
		}
	}

}
