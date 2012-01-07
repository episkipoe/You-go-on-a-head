package com.episkipoe.hat.common.inventory;

import java.util.Arrays;
import java.util.Collection;

public class Collectables extends InventoryCategory {

	@Override public String getName() { return "Collectable"; }

	public static Collection<String> getRums() {
		String rums[] = {"KrakenRum.png"};
		return Arrays.asList(rums);
	}
}
