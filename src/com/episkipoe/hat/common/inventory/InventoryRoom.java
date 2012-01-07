package com.episkipoe.hat.common.inventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.interact.TextClickable;
import com.episkipoe.hat.rooms.Room;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public class InventoryRoom extends Room {
	private Door exit;
	public InventoryRoom() { 
		exit = new Door();
		exit.setLocation(new Point(750, 550));
		addExit(exit);
		setBackground("InventoryBG.png");
	}
	
	static public class WearableDrawable extends ImageDrawable {
		public WearableDrawable(String fileName) { 
			setFilename(fileName);
		}

		@Override
		public void click() {
			Main.player.setFilename(getFilename());
		}
	}
	static public class InventoryDrawable extends ImageDrawable {
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

	InventoryCategory category=new Hats();
	public void setCategory(InventoryCategory category) {
		this.category = category;
	}
	
	public void loadItems() {
		Collection<String> list = Main.inventory.getItemsOfCategory(category);
		if(list==null || list.isEmpty()) return;
		
		int x=0;
		int y=0;
		int maxHeight=0; 

		for(String f : list) {
			ImageDrawable d = category.getInventoryDrawable(f);
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
		InventoryCategory category;
		private SwitchCategory(InventoryRoom room, InventoryCategory category) {
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
		for(InventoryCategory cat : InventoryCategoryFactory.getCategories()) {
			Point location = new Point(x, 20);
			SwitchCategory action = new SwitchCategory(this, cat);
			List<String> msg = Arrays.asList(cat.getPlural());
			TextClickable btn = new TextClickable(msg, location, action);
			if(cat.equals(this.category)) {
				btn.setBackgroundStyle("rgba(255,255,255,0.8)");
			} else {
				btn.setBackgroundStyle("rgba(128,128,128,0.8)");
			}
			addDrawable(btn);
			x+= (TextUtils.getTextWidth(msg)+10);
		}
	}

	@Override
	public Collection<String> getRequiredImages() { return Main.inventory.getAllItems(); }
	
	@Override
	public void onLoad() { 
		reload();
	}
	
	@Override
	public void onEnter() {
		reload();
	}
	
	public void reload() {
		clearDrawables();
		loadCategoryList();
		loadItems();	
	}

	public boolean showHud() { return false; }
}
