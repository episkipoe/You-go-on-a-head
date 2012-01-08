package com.episkipoe.hat.common.inventory;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;

public class Hats extends InventoryCategory {
	@Override public String getName() { return "Hat"; }
	@Override public void playerPickedUp(String fileName) {
		Main.player.setFilename(fileName);
	}
	
	static private class WearableDrawable extends ImageDrawable {
		private WearableDrawable(String fileName) { 
			setFilename(fileName);
		}

		@Override
		public void click() {
			Main.player.setFilename(getFilename());
		}
	}	
	
	@Override public ImageDrawable getInventoryDrawable(String fileName) {
		return new WearableDrawable(fileName);
	}
	
	public static Collection<String> getPartyHats() {
		String hats[] = {"RedPartyHat.png", "MardiGrasHat0.png", "MardiGrasSpikeHat.png"};
		return Arrays.asList(hats);		
	}
}
