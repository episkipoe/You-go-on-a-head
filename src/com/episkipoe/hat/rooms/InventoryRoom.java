package com.episkipoe.hat.rooms;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.GameStorage;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.interact.TextClickable;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public class InventoryRoom extends Room {
	private Door exit;
	public InventoryRoom() { 
		exit = new Door();
		exit.setLocation(new Point(750, 550));
		setBackground("InventoryBG.png");
	}
	
	private class WearableDrawable extends ImageDrawable {
		public WearableDrawable(String fileName) { 
			setFilename(fileName);
		}

		@Override
		public void click() {
			Main.player.setFilename(getFilename());
		}
	}
	private class InventoryDrawable extends ImageDrawable {
		public InventoryDrawable(String fileName) { 
			setFilename(fileName);
		}

		@Override
		public void click() {
		}
	}

	@Override
	public void postDraw(Context2d context) {
		exit.draw(context);
	}

	String category="hats";
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void loadItems() {
		Collection<String> list = Main.inventory.getItemsOfCategory(category);
		if(list==null || list.isEmpty()) return;
		
		int x=0;
		int y=0;
		int maxHeight=0; 

		for(String f : list) {
			ImageDrawable d;
			if(category.equals("hats")) {
				d = new WearableDrawable(f);
			} else {
				d = new InventoryDrawable(f);
			}
			ImageElement img = d.getImageElement();
			if(img==null) continue;
			if(y==0) {
				y+=img.getHeight()*0.75;
			}
			if(x+img.getWidth() > Main.canvasWidth) {
				x = 0;
				y+=maxHeight+5;
				maxHeight=0;
			}
			x+=img.getWidth()*0.5;
			d.setLocation(new Point(x, y));
			x+=img.getWidth()*0.5+5;
			if(img.getHeight() > maxHeight) maxHeight = img.getHeight();
			addDrawable(d);
		}			
	}

	private class SwitchCategory implements Runnable {
		InventoryRoom room;
		String category;
		private SwitchCategory(InventoryRoom room, String category) {
			this.room = room;
			this.category = category;
		}
		@Override
		public void run() {
			room.setCategory(category);
			room.onLoad();
		} 
	}
	
	private void loadCategoryList() {
		int x = 10;
		for(String cat : GameStorage.getCategories()) {
			Point location = new Point(x, Main.canvasHeight-50);
			SwitchCategory action = new SwitchCategory(this, cat);
			TextClickable btn = new TextClickable(Arrays.asList(cat), location, action);
			if(cat.equals(this.category)) {
				btn.setBackgroundStyle("rgba(255,255,255,0.8)");
			} else {
				btn.setBackgroundStyle("rgba(128,128,128,0.8)");
			}
			addDrawable(btn);
			x+= 60;
		}
	}

	@Override
	public Collection<String> getRequiredImages() { return Main.inventory.getAllItems(); }
	
	@Override
	public void onLoad() { 
		clearDrawables();
		addDrawable(exit);
		loadCategoryList();
		loadItems();
	}

}
