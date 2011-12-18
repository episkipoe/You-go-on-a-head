package com.episkipoe.hat.common;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class ImageLibrary {
	Map<String,ImageElement> images;
	public ImageLibrary() { 
		images = new HashMap<String,ImageElement>();
	}
	
	public ImageElement getImage(String filename) throws Exception {
		if(images.containsKey(filename))
			return images.get(filename);
		throw new Exception("Could not get image: " + filename + "\n\tValid images are: " + images.keySet());
	}
	
	public void loadImage(String filename) {
		Image img = new Image("images/"+filename);
		img.setTitle(filename);
		img.addLoadHandler(new LoadHandler() {
		      public void onLoad(LoadEvent event) {
		    	  Image img = (Image) event.getSource();
		    	  images.put(img.getTitle(), (ImageElement)img.getElement().cast());
		      }
		    });
		    img.setVisible(false);
		    RootPanel.get().add(img); // image must be on page to fire load
	}
	public void loadImages(String [] filenames) {
		for(String f: filenames) loadImage(f);
	}

}
