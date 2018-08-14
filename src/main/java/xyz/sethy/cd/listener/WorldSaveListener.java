package xyz.sethy.cd.listener;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import xyz.sethy.cd.Main;

public class WorldSaveListener {
	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save event) {
		// Saves the ExceriseLearner to file when the world is being saved, usually when paused for leaving the world.
		Main.getInstance().getExerciseLearner().save();
	}
}
