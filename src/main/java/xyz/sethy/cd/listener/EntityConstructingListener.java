package xyz.sethy.cd.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import xyz.sethy.cd.extended.ExtendedPlayer;

public class EntityConstructingListener {
	
	@ForgeSubscribe
	public void onEntityConstructing(final EntityConstructing event) {
		// Checks if the entity being constructed if an EntityPlayer, and if the ExentedPlayer for that player is null, if so it creates a new one
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null) {
			ExtendedPlayer player = new ExtendedPlayer((EntityPlayer) event.entity);
		}
	}
}
