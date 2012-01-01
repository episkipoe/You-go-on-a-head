package com.episkipoe.hat.common.inventory;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;

public class Hats extends InventoryCategory {
	@Override public String getName() { return "Hat"; }
	@Override public void playerPickedUp(String fileName) {
		Main.player.setFilename(fileName);
	}
	@Override public ImageDrawable getInventoryDrawable(String fileName) {
		return new InventoryRoom.WearableDrawable(fileName);
	}
}
