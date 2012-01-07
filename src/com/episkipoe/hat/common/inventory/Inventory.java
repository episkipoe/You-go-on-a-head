package com.episkipoe.hat.common.inventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.GameStorage;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.Dialog;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.rooms.Room;
import com.google.gwt.dom.client.ImageElement;

public class Inventory extends ImageDrawable {
	private Map<InventoryCategory, Set<String> > categoryToItems;
	public Inventory() { 
		categoryToItems = new HashMap<InventoryCategory, Set<String> > ();
		categoryToItems.put(new Hats(), new HashSet<String>());
		categoryToItems.put(new Collectables(), new HashSet<String>());
		
		setLocation(new Point(50,50));
		setFilename("Inventory.png");
	}

	Room previousRoom;
	@Override
	public void click() throws Exception {
		Main.switchRoom(InventoryRoom.class);
	}

	public void addItem(String filename, InventoryCategory category) {
		Set<String> itemList = categoryToItems.get(category);
		if(itemList==null) {
			itemList = new HashSet<String>();
			categoryToItems.put(category, itemList);
		}
		itemList.add(filename);
	}
	
	public Collection<String> getItemsOfCategory(InventoryCategory category) {
		return categoryToItems.get(category);
	}
	
	public void pickup(String fileName, InventoryCategory category) {
		Main.inventory.addItem(fileName, category);
		String msg = "New " + category.getName();
		pickupNotify(msg);
		category.playerPickedUp(fileName);
		GameStorage.saveGame();		
	}
	
	public void pickupNotify(String msg) {
		ImageElement img = getImageElement();
		Point location = new Point(5, img.getHeight()+5);
		Main.room.addDrawable(new DialogElement(msg, location, 30));		
	}
	
	public static class Pickup implements Runnable {
		private String fileName;
		private InventoryCategory category;
		private String message;
		public Pickup (String fileName, InventoryCategory category, String message) {
			this.fileName = fileName;
			this.category = category;	
			this.message = message;
		}
		public Pickup (String fileName, InventoryCategory category) {
			this.fileName = fileName;
			this.category = category;	
		}
		public Pickup (ImageDrawable object, InventoryCategory category) {
			this.fileName = object.getFilename();
			this.category = category;
		}	
		public void setMessage(String message) {
			this.message = message;
		}
		public void run() {
			Main.inventory.pickup(fileName, category);
			if(message != null) {
				Main.room.addDrawable(new Dialog(new DialogElement(Arrays.asList(message), Main.getCenterPoint())));
			}
		}	
	}

	public void fromString(String inventory) {
		if(inventory==null) return;
		String[] stringList = inventory.split("_");
		if(stringList.length < 2) return;
		for(int i = 0 ; i < stringList.length ; i+=2) {
			String fileName = stringList[i+1];
			InventoryCategory category = InventoryCategoryFactory.getCategoryByName(stringList[i]);
			addItem(fileName, category);
		}
	}
	
	public String toString() {
		if(categoryToItems == null) return null;
		String inventoryString="";
		for(InventoryCategory category: InventoryCategoryFactory.getCategories()) {
			Collection<String> itemList = getItemsOfCategory(category);
			if(itemList == null) continue;
			for(String item: itemList) {
				inventoryString += category.getName()+"_"+item+"_";
			}
		}
		return inventoryString;
	}

	public Collection<String> getAllItems() {
		Set<String> items = new HashSet<String>();
		for (Entry<InventoryCategory, Set<String>> entry : categoryToItems.entrySet()) {
			if(entry.getValue() == null || entry.getValue().isEmpty()) continue;		
			items.addAll(entry.getValue());
		}
		return items;
	}
	public boolean contains(String item) {
		for (Entry<InventoryCategory, Set<String>> entry : categoryToItems.entrySet()) {
			if(entry.getValue() == null || entry.getValue().isEmpty()) continue;
			if(entry.getValue().contains(item)) return true;
		}
		return false;
	}
	public boolean contains(InventoryCategory category, String item) {
		if(!categoryToItems.containsKey(category)) return false;
		return categoryToItems.get(category).contains(item);
	}	
	public boolean containsAll(Collection<String> items) {
		for(String i: items) {
			if(!contains(i)) return false;
		}
		return true;
	}
	
	public boolean containsAny(InventoryCategory category, Collection<String> items) {
		for(String i: items) {
			if(contains(category, i)) return true;
			System.out.println(categoryToItems + " has no " + items);
		}
		return false;
	}
	public boolean containsAny(Collection<String> items) {
		for(String i: items) {
			if(!contains(i)) return true;
		}
		return false;
	}	
	
}
