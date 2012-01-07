package com.episkipoe.hat.characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;

public class Magician extends ImageDrawable {

	public Magician() {
		setFilename("StickMan.png");
	}
	
	int timesClicked=0;
	public void click() { 
		if(!Main.inventory.contains("MagicHatBeer.png")) {
			String msg[] = {"I'm feeling a bit parched", "How 'bout you bring me a brew..."};
			Main.room.getDialog().say(this.say(Arrays.asList(msg), 40));
			return;
		}
		
		if(Main.player.wearing(Arrays.asList("SantaHat.png"))) {
			Main.room.getDialog().say(this.say(Arrays.asList("Merry Christmas!"), 40));
			return;
		}
		
		if(Main.player.wearing(Arrays.asList("PirateHat.png"))) {
			List<List<String>> sayings = new ArrayList<List<String>>();
			sayings.add(Arrays.asList("Avast ye matey!"));
			sayings.add(Arrays.asList("Have ye been to Tortuga?"));
			Main.room.getDialog().say(this.say(sayings));
			return;
		}
		
		if(Main.player.wearing(Arrays.asList("RedPartyHat.png"))) {
			List<List<String>> sayings = new ArrayList<List<String>>();
			sayings.add(Arrays.asList("Wow!  That's a nice hat."));
			Main.room.getDialog().say(this.say(sayings));
			return;	
		}
		
		if(Main.player.wearing(Arrays.asList("ChickenMask.png"))) {
			List<List<String>> sayings = new ArrayList<List<String>>();
			sayings.add(Arrays.asList("Aaaaaahhhhh!!!"));
			sayings.add(Arrays.asList("You're creepin' me out"));
			sayings.add(Arrays.asList("Get the cluck out of here"));
			Main.room.getDialog().say(this.say(sayings));
			return;
		}
		
		if(Main.player.wearing(Arrays.asList("CheBeret.png"))) {
				List<List<String>> sayings = new ArrayList<List<String>>();
			sayings.add(Arrays.asList("Vive la revolucion"));
			Main.room.getDialog().say(this.say(sayings));
			return;		
		}
		
		if(Main.player.wearing(Arrays.asList("sombrero.png"))) {
			List<List<String>> sayings = new ArrayList<List<String>>();
			sayings.add(Arrays.asList("Hola!"));	
			sayings.add(Arrays.asList("Como estas?"));	
			sayings.add(Arrays.asList("Me gusta tu sombrero"));	
			sayings.add(Arrays.asList("Will you play me a tune?"));	
			Main.room.getDialog().say(this.say(sayings));
			return;		
		}

		timesClicked++;
		if((timesClicked % 8) == 0) {
			Main.room.getDialog().say(say(Arrays.asList("Why do you keep poking me?"), 80));
		} else {
			List<List<String>> sayings = new ArrayList<List<String>>();
			sayings.add(Arrays.asList("Hey!"));
			sayings.add(Arrays.asList("Hey!"));

			if(!Main.inventory.contains("KrakenRum.png")) {
				sayings.add(Arrays.asList("I'll bet there's loads of booze in Vegas"));
			}
			if(!Main.inventory.contains("SantaHat.png")) {
				sayings.add(Arrays.asList("Have you been to the North Pole yet?"));
			}
			Main.room.getDialog().say(say(sayings));
		}
	}

}
