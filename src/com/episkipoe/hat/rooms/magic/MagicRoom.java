package com.episkipoe.hat.rooms.magic;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.characters.Magician;
import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.GameStorage;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.player.MovePlayer;
import com.episkipoe.hat.rooms.Room;


public class MagicRoom extends Room {
	Magician magician;
	public MagicRoom() throws Exception { 
		setBackground("MagicRoom-Before.png");
		magician = new Magician();
		magician.setLocation(new Point(400, 450));
		addDrawable(magician);
	
		addDrawable(new Door(new Point(600, 500), MagicLivingRoom.class));
	}

	public List<String> getRequiredImages() { 
		return Arrays.asList("MagicRoom.png"); 
	}
	
	@Override 
	protected void onLoad() {
		if(!getBackground().equals("MagicRoom-Before.png")) return ;
		String firstLine[] = {"OK.", "You can do this."};
		getDialog().say(Arrays.asList(firstLine), magician);
		getDialog().say("Concentrate", magician);
		getDialog().say("Rabbit.  Hat.  This is magic 101.", magician);
		DialogElement playerPop = magician.say("Abracabaca...");  
		playerPop.setPostAction(new IntroducePlayer());
		getDialog().add(playerPop);
		getDialog().say("What was that?", magician);
	}
	
	private class IntroducePlayer implements Runnable {
		@Override
		public void run() {
			Main.player.setFilename("TopHat.png");
			Main.player.setLocation(Main.getCenterPoint());
			Main.room.setBackground("MagicRoom.png");
			Main.setMouseMode(new MovePlayer());
			
			String playerIntro[] = {"I'm a magic hat.","You stay here.","I'll go on a head."};
			Main.room.getDialog().say(Arrays.asList(playerIntro), Main.player);
			GameStorage.saveGame();
		}
	}
	
}
