package com.episkipoe.hat.rooms;

import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Inventory;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public class InventoryRoom extends Room {
	private Door exit;
	public InventoryRoom() { 
		exit = new Door();
		exit.setLocation(new Point(750, 550));
		addClickable(exit);
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

	public void postDraw(Context2d context) {
		exit.draw(context);
	}

	public void onEnter() { 
		clearDrawables();
		int x=50;
		int y=50;
		Collection<String> hats = Main.inventory.getHats();
		int maxHeight=0; 
		addDrawable(new DialogElement("Hats", new Point(x, 10)));
		for(String f : hats) {
			WearableDrawable d = new WearableDrawable(f);
			d.setLocation(new Point(x, y));
			ImageElement img = d.getImageElement();
			x+=img.getWidth()+5;
			if(img.getHeight() > maxHeight) maxHeight = img.getHeight();
			addDrawable(d);
		}
		y+=maxHeight+5;
		for(String category : Inventory.getCategories()) {
			Collection<String> list = Main.inventory.getItemsOfCategory(category);
			if(list != null) {
				x=20;
				addDrawable(new DialogElement(category, new Point(x, y)));
				maxHeight=0; 
				for(String f : list) {
					InventoryDrawable i = new InventoryDrawable(f);
					i.setLocation(new Point(x, y));
					ImageElement img = i.getImageElement();
					x+=img.getWidth();
					if(img.getHeight() > maxHeight) maxHeight = img.getHeight();
					addDrawable(i);
				}
			}
			y+=maxHeight;
		}
	}

}
