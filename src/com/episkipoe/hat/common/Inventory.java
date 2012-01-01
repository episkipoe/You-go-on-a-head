package com.episkipoe.hat.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.dialog.Dialog;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.rooms.InventoryRoom;
import com.episkipoe.hat.rooms.Room;

public class Inventory extends ImageDrawable {
	public Inventory() { 
		categoryToItems = new HashMap<String, Set<String> > ();
		setLocation(new Point(50,50));
		setFilename("Inventory.png");
	}

	Room previousRoom;
	@Override
	public void click() throws Exception {
		Main.switchRoom(InventoryRoom.class);
	}

	private Map<String, Set<String> > categoryToItems;
	public void addItem(String filename, String category) {
		Set<String> itemList = categoryToItems.get(category);
		if(itemList==null) {
			itemList = new HashSet<String>();
			categoryToItems.put(category, itemList);
		}
		itemList.add(filename);
	}
	
	public Collection<String> getItemsOfCategory(String category) {
		return categoryToItems.get(category);
	}

	public static class Buy implements Runnable {
		String fileName;
		String category;
		int price;
		public Buy (String fileName, String category, int price) {
			this.fileName = fileName;
			this.category = category;	
			this.price = price;
		}

		public void run() {
			if(Main.player.getMoney() < price ) {
				return;
			}
			Main.player.spendMoney(price);
			Main.inventory.addItem(fileName, category);
			GameStorage.saveGame();
		}			
	}
	
	public void pickup(String fileName, String category) {
		if(category.equals("hats")) {
			Main.player.setFilename(fileName);
		}
		Main.inventory.addItem(fileName, category);
		GameStorage.saveGame();		
	}
	
	public static class Pickup implements Runnable {
		private String fileName;
		private String category;
		private String message;
		public Pickup (String fileName, String category, String message) {
			this.fileName = fileName;
			this.category = category;	
			this.message = message;
		}
		public Pickup (String fileName, String category) {
			this.fileName = fileName;
			this.category = category;	
		}
		public Pickup (ImageDrawable object, String category) {
			this.fileName = object.getFilename();
			this.category = category;
		}	
		public void setMessage(String message) {
			this.message = message;
		}
		public void run() {
			Main.inventory.pickup(fileName, category);
			if(message != null) {
				Main.room.addDrawable(new Dialog(new DialogElement(message, Main.getCenterPoint())));
			}
		}	
	}

	public void fromString(String inventory) {
		if(inventory==null) return;
		String[] stringList = inventory.split("_");
		if(stringList.length < 2) return;
		for(int i = 0 ; i < stringList.length ; i+=2) {
			addItem(stringList[i+1], stringList[i]);
		}
	}
	
	public String toString() {
		if(categoryToItems == null) return null;
		String inventoryString="";
		for(String category: GameStorage.getCategories()) {
			Collection<String> itemList = getItemsOfCategory(category);
			if(itemList == null) continue;
			for(String item: itemList) {
				inventoryString += category+"_"+item+"_";
			}
		}
		return inventoryString;
	}

	public Collection<String> getAllItems() {
		Set<String> items = new HashSet<String>();
		for (Entry<String, Set<String>> entry : categoryToItems.entrySet()) {
			if(entry.getValue() == null || entry.getValue().isEmpty()) continue;		
			items.addAll(entry.getValue());
		}
		return items;
	}
	public boolean contains(String item) {
		for (Entry<String, Set<String>> entry : categoryToItems.entrySet()) {
			if(entry.getValue() == null || entry.getValue().isEmpty()) continue;
			if(entry.getValue().contains(item)) return true;
		}
		return false;
	}
	
}
