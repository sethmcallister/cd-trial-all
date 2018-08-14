package xyz.sethy.cd.handler;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import xyz.sethy.cd.extended.ExtendedPlayer;

public class TickHandler implements ITickHandler {
	private long lastHealTime = System.currentTimeMillis();

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.SERVER))) {
			onTick();
		}
	}
	
	private void onTick() {
		if ((System.currentTimeMillis() - lastHealTime) < TimeUnit.SECONDS.toMillis(1l)) {
			return;
		}
		this.lastHealTime = System.currentTimeMillis();
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
		ExtendedPlayer extendedPlayer = ExtendedPlayer.get(player);
		extendedPlayer.setCurrentExerciseLevel(extendedPlayer.getCurrentExerciseLevel() + 0.7f);
	}

	@Override
	public EnumSet<TickType> ticks() {
		return null;
	}

	@Override
	public String getLabel() {
		return null;
	}

}
