package com.episkipoe.hat.rooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.inventory.InventoryItem;
import com.episkipoe.hat.common.inventory.Tricks;
import com.episkipoe.hat.rooms.missouri.Hospital;
import com.google.gwt.canvas.dom.client.Context2d;

public abstract class StoreRoom extends Room {
	protected abstract Class<? extends Room> getExit();
	protected abstract Collection<InventoryItem> getItemsForSale();
	protected abstract Collection<String> getCannotAffordMsg();
	protected abstract Collection<String> getItemStolenMsg();
	protected abstract Collection<String> getItemPurchasedMsg();
	
	public final List<String> getRequiredImages() { 	
		List<String> imgs = new ArrayList<String>();
		for(InventoryItem item : getItemsForSale()) {
			imgs.add(item.fileName);
		}
		return imgs;
	}
	
	public final void onLoad() {
		addExit(getExit());
		
		int x=Main.inventory.getImageElement().getWidth();
		int y=Main.inventory.getImageElement().getHeight();
		for(InventoryItem item : getItemsForSale()) {
			PurchaseDrawable d = new PurchaseDrawable(new Point(x, y), item);
			addDrawable(d);
			x+=d.getImageElement().getWidth()+20;			
		}

	}
	
	private class PurchaseDrawable extends ImageDrawable {
		InventoryItem item;
		DialogElement costLabel;
		private PurchaseDrawable(Point location, InventoryItem item) { 
			setLocation(location);
			setFilename(item.fileName);
			this.item=item;
		}
	
		public DialogElement getCostLabel() {
			float x = getLocation().x;
			float y = getLocation().y+30;
			String lbl = "$"+String.valueOf(item.cost);
			return new DialogElement(Arrays.asList(lbl), new Point(x, y));	
		}
		@Override
		public void postDraw(Context2d context) {
			if(costLabel==null) costLabel=getCostLabel();
			costLabel.draw(context);
		}

		@Override
		public void click() {
			String thiefHats[] = {"PirateHat.png"};
			if(Main.player.wearing(Arrays.asList(thiefHats))) {
				if(Main.player.skillCheck("Plunderin.png")) {
					TextUtils.growl(getItemStolenMsg());	
				} else {
					Main.switchRoom(Hospital.class);
					TextUtils.growl(Arrays.asList("Get out of here, you filthy pirate!"));
					return;
				}
			} else {
				if(Main.player.getMoney() >= item.cost) {
					Main.player.spendMoney(item.cost);
					TextUtils.growl(getItemPurchasedMsg());
				} else {
					TextUtils.growl(getCannotAffordMsg());
					return;
				}
			}
			Main.inventory.pickup(getFilename(), item.category);
			if(item.skillConveyed != null) {
				Main.inventory.pickup(item.skillConveyed, new Tricks());
			}
		}
	}		
}
