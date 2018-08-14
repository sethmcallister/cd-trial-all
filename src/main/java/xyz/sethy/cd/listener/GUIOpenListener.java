package xyz.sethy.cd.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.ForgeSubscribe;
import xyz.sethy.cd.extended.ExtendedPlayer;

public class GUIOpenListener {
	@ForgeSubscribe
	public void onGuiOpen(final GuiOpenEvent event) {
		// Checks if the gui being opened is an instanceof a GuiGameOver (Respawn screen) is it's not it'll return 
		if (!(event.gui instanceof GuiGameOver))
			return;
		
		// Gets the extendedPlayer for the player on the game, and will reset both the exercise level, and water level the max they can, then sync the player
		ExtendedPlayer player = ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
		player.setCurrentExerciseLevel(player.getMaxExerciseLevel());
		player.setCurrentWaterLevel(player.getMaxWaterLevel());
		player.sync();
	}
}
