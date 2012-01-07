package com.episkipoe.hat.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.rooms.Room;

public class Door extends ImageDrawable {
	private Class<? extends Room> destination;
	public Door(Point location, Class<? extends Room> destination, String fileName) {
		setLocation(location);
		setDestination(destination);
		setFilename(fileName);
	}
	public Door(Point location, Class<? extends Room> destination) {
		setLocation(location);
		setFilename("door.png");
		setDestination(destination);
	}
	public Door() {
		setFilename("door.png");
	}
	public void setDestination(Class<? extends Room> destination) {
		this.destination = destination;
	}
	List<Criterion> criteria;
	public List<Criterion> getCriteria() {
		if(criteria==null) criteria = new ArrayList<Criterion>();
		return criteria;
	}
	public void addCriterion(Criterion criterion) {
		getCriteria().add(criterion);
	}
	
	@Override
	public void click() throws Exception {
		for(Criterion criterion : getCriteria()) {
			if(!criterion.valid()) {
				Collection<String> msg = criterion.getFailureMessage();
				TextUtils.growl(msg);
				return;
			}
		}
		if(destination==null) {
			Main.goBack();
		} else {
			Main.switchRoom(destination);
		}
	}
	@Override
	public boolean continueProcessing() { return false; }

}
