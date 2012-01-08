package com.episkipoe.hat.rooms.magic;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.characters.Magician;
import com.episkipoe.hat.client.Main;
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
	
		addRightDoor(MagicLivingRoom.class);
	}

	public List<String> getRequiredImages() { 
		return Arrays.asList("MagicRoom.png"); 
	}
	
	@Override 
	protected void onLoad() {
		if(!getBackground().equals("MagicRoom-Before.png")) return ;
		String firstLine[] = {"OK.", "You can do this."};
		getDialog().add(Arrays.asList(firstLine), magician);
		String secondLine[] = {"Pulling a rabbit from a hat.", "This should be easy"};
		getDialog().add(Arrays.asList(secondLine), magician);
		getDialog().add("Concentrate.", magician);
		DialogElement playerPop = magician.say("Abracabaca...");  
		playerPop.setPostAction(new IntroducePlayer());
		getDialog().add(playerPop);
		String lastLine[] = {"What was that?", "", "(Protip: If you get stuck click on the magician for advice)"};
		getDialog().add(Arrays.asList(lastLine), magician);
	}
	
	private class IntroducePlayer implements Runnable {
		@Override
		public void run() {
			Main.player.setFilename("TopHat.png");
			Main.player.setLocation(Main.getCenterPoint());
			Main.room.setBackground("MagicRoom.png");
			Main.setMouseMode(new MovePlayer());
			
			String playerIntro[] = {"I'm a magic hat.","You stay here.","I'll go on a head."};
			Main.room.addDrawable(Main.player.say(Arrays.asList(playerIntro), 50));
			GameStorage.saveGame();
		}
	}
	
}
