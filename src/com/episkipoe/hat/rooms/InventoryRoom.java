package com.episkipoe.hat.rooms;

import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.google.gwt.canvas.dom.client.Context2d;

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
		int x=20;
		int y=20;
		List<String> hats = Main.inventory.getItemsOfCategory("hats");
		if(hats != null) {
			for(String f : hats) {
				WearableDrawable d = new WearableDrawable(f);
				d.setLocation(new Point(x, y));
				x+=d.getImageElement().getWidth();
				addDrawable(d);
			}
		}
		/*
		List<String> list = Main.inventory.getItemsOfCategory("hats");
		if(hats != null) {
			for(String f : hats) {
				InventoryDrawable i = new InventoryDrawable(f);
				i.setLocation(new Point(x, y));
				x+=i.getImageElement().getWidth();
				addDrawable(i);
			}
		}
		*/
		
	}

}
