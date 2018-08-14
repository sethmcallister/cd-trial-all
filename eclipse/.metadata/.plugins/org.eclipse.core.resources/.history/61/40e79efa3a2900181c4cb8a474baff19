package xyz.sethy.cd.listener;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import xyz.sethy.cd.Main;

public class WorldSaveListener {
	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save event) {
		Main.getInstance().getExerciseLearner().save();
	}
}
