package com.episkipoe.hat.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.rooms.InventoryRoom;
import com.episkipoe.hat.rooms.Room;

public class InventoryImage extends ImageDrawable {
	public InventoryImage() { 
		categoryToItems = new HashMap<String, List<String> > ();
		setLocation(new Point(50,50));
		setFilename("Inventory.png");
	}

	Room previousRoom;
	@Override
	public void click() throws Exception {
		Main.switchRoom(InventoryRoom.class);
	}

	private Map<String, List<String> > categoryToItems;
	public void addItem(String filename, String category) {
		List<String> itemList = categoryToItems.get(category);
		if(itemList==null) {
			itemList = new ArrayList<String>();
		}
		itemList.add(filename);
	}
	
	public List<String> getItemsOfCategory(String category) {
		return categoryToItems.get(category);
	}

	public static class Pickup implements Runnable {
		String fileName;
		String category;
		public Pickup (String fileName, String category) {
			this.fileName = fileName;
			this.category = category;	
		}
		public Pickup (ImageDrawable object, String category) {
			this.fileName = object.getFilename();
			this.category = category;
		}	
		public void run() {
			if(category.equals("hats")) {
				Main.player.setFilename(fileName);
			}
			Main.inventory.addItem(fileName, category);
		}	
	}
	
}
