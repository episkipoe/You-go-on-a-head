package com.episkipoe.hat.common.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public class Tricks extends InventoryCategory {

	@Override public String getName() { return "Trick"; }

	static public Collection<String> getTricks() {
		List<String> tricks = new ArrayList<String>();
		tricks.add("Plunderin.png");
		tricks.add("Party.png");
		return tricks;
	}

	private class SkillDrawable extends ImageDrawable {
		DialogElement skillLabel;
		public SkillDrawable(String fileName) { 
			setFilename(fileName);
		}		
		public DialogElement getSkillLabel() {
			ImageElement img = getImageElement();
			System.out.println("x: " + getLocation().x + "width: " + img.getWidth());
			float x = (float) (getLocation().x+img.getWidth()*0.5+5);
			float y = getLocation().y;
			String lbl = Integer.toString(Main.player.getSkillLevel(getFilename()));
			return new DialogElement(Arrays.asList(lbl), new Point(x, y));	
		}	
		@Override
		public void postDraw(Context2d context) {
			if(skillLabel==null) skillLabel = getSkillLabel();
			skillLabel.draw(context);
		}
		@Override public void click() { }
	}

	@Override public ImageDrawable getInventoryDrawable(String fileName) {
		return new SkillDrawable(fileName);
	}

	static public int getDefaultValue(String filename) {
		if(filename.equals("Party.png")) return 10;
		return 50;
	}
}
