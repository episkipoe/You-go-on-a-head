package com.episkipoe.hat.characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;

public class Magician extends ImageDrawable {

	public Magician() {
		setFilename("StickMan.png");
	}
	
	int timesClicked=0;
	public void click() { 
		if(!Main.inventory.contains("MagicHatBeer.png")) {
			String msg[] = {"I'm feeling a bit parched", "Can you bring me a brew?"};
			Main.room.getDialog().add(say(Arrays.asList(msg), 40));
			return;
		}
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
			Random rnd = new Random();
			List<List<String>> sayings = new ArrayList<List<String>>();
			sayings.add(Arrays.asList("Hey!"));
			sayings.add(Arrays.asList("Hey!"));

			if(!Main.inventory.contains("SantaHat.png")) {
				sayings.add(Arrays.asList("Have you been to the North Pole yet?"));
			}
			int msgIdx = rnd.nextInt(sayings.size());
			Main.room.getDialog().add(say(sayings.get(msgIdx), 40));
		}
	}

}
