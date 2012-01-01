package com.episkipoe.hat.rooms.party;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.InventoryCategory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.CityMapRoom;

public class PartyStore extends Room {
	Door exit;
	public PartyStore() { 
		
	}
	public List<String> getRequiredImages() { 
		String images[] = {"RedPartyHat.png", "MardiGrasHat0.png"};
		return Arrays.asList(images);
	}

	@Override
	public void onLoad() {	
		exit = new Door();
		exit.setLocation(new Point(750, 550));
		exit.setDestination(CityMapRoom.class);
		addDrawable(exit);

		int x=Main.inventory.getImageElement().getWidth();
		int y=Main.inventory.getImageElement().getHeight();
		Hats hats = new Hats();
		PurchaseDrawable redHat = new PurchaseDrawable(new Point(x, y), "RedPartyHat.png", hats, 100);
		addDrawable(redHat);
		x+=redHat.getImageElement().getWidth()+20;
		
		PurchaseDrawable mardiGrasHat = new PurchaseDrawable(new Point(x, y), "MardiGrasHat0.png", hats, 50);
		addDrawable(mardiGrasHat);
	}
	
	private class PurchaseDrawable extends ImageDrawable {
		InventoryCategory category;
		int cost;
		private PurchaseDrawable(Point location, String fileName, InventoryCategory category, int cost) { 
			setLocation(location);
			setFilename(fileName);
			float x = location.x;
			float y = location.y+30;
			String lbl = "$"+String.valueOf(cost);
			addDrawable(new DialogElement(Arrays.asList(lbl), new Point(x, y)));
			this.category = category;
			this.cost = cost;
		}

		@Override
		public void click() {
			String thiefHats[] = {"PirateHat.png"};
			if(Main.player.wearing(Arrays.asList(thiefHats))) {
				String msg[] = {"Hey.  I don't want any trouble.", "Just take it and go, bro"};
				TextUtils.growl(Arrays.asList(msg));	
			} else {
				if(Main.player.getMoney() >= cost) {
					Main.player.spendMoney(cost);
					String msg[] = {"Thank you for your purchase", "Party hard, bro"};
					TextUtils.growl(Arrays.asList(msg));
				} else {
					String msg[] = {"You can't afford this.", "Come back when you got some more money, bro"};
					TextUtils.growl(Arrays.asList(msg));
					return;
				}
			}
			Main.inventory.pickup(getFilename(), category);
		}
	}	
}
