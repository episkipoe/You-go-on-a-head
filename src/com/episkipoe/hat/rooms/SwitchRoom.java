package com.episkipoe.hat.rooms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.draw.TextUtils;

/**
 *  A Runnable for {@link Main#switchRoom}
 *
 */
public class SwitchRoom implements Runnable {
	Class<? extends Room> destination;
	public SwitchRoom(Class<? extends Room> destination) {
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
	public void run() {
		for(Criterion criterion : getCriteria()) {
			if(!criterion.valid()) {
				Collection<String> msg = criterion.getFailureMessage();
				TextUtils.growl(msg);
				return;
			}
		}	
		try {
			Main.switchRoom(destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
