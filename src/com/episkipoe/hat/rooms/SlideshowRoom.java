package com.episkipoe.hat.rooms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;

public abstract class SlideshowRoom extends Room {
	abstract protected Class<? extends Room> getExitRoom();

	final public void onLoad() {
		addDoors();
	}
	final public void onEnter() {
		currentSlide=0;
		setBackground(slides.get(currentSlide));
	}
	private List<String> slides = new ArrayList<String>();
	protected final void addSlide(String fileName) { 
		slides.add(fileName);
	}
	
	@Override
	public Collection<String> getRequiredImages() {
		return slides;
	}
	
	private int currentSlide=0;
	public void addDoors() {
		addDrawable(new NextButton());
		addDrawable(new BackButton());
	}
	
	private void next() {
		currentSlide++;
		if(currentSlide >= slides.size()) {
			Main.switchRoom(getExitRoom());
			return;
		}
		setBackground(slides.get(currentSlide));
	}
	private void back() {
		currentSlide--;
		if(currentSlide < 0) {
			Main.switchRoom(getExitRoom());
			return;
		}
		setBackground(slides.get(currentSlide));
	}
	private class NextButton extends ImageDrawable {
		private NextButton() { 
			setFilename("RightArrow.png");
			setLocation(new Point(630, 500));
		}
		@Override
		public void click() throws Exception { next(); }
	}
	
	private class BackButton extends ImageDrawable {
		private BackButton() { 
			setFilename("LeftArrow.png");
			setLocation(new Point(60, 500));
		}
		@Override
		public void click() throws Exception { back(); }
	}
}
