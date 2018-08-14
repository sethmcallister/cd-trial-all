package xyz.sethy.cd.listener;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import xyz.sethy.cd.extended.ExtendedPlayer;

public class PlayerInteractListener {
	private final Map<Integer, Float> waterInFood;
	private final Map<Integer, Float> energyInFood;
	private int waterUsages = 0;
	
	public PlayerInteractListener() {
		this.waterInFood = new HashMap<Integer, Float>();
		this.waterInFood.put(360, 0.1f);
		this.waterInFood.put(391, 0.08f);
		this.waterInFood.put(373, 2f);
		this.waterInFood.put(39, 0.09f);
		this.waterInFood.put(40, 0.09f);
		this.waterInFood.put(282, 1.7f);
		
		this.energyInFood = new HashMap<Integer, Float>();
		this.energyInFood.put(354, 1.03f);
		this.energyInFood.put(320, 0.09f);
		this.energyInFood.put(366, 1.0f);
		this.energyInFood.put(350, 1.02f);
	}
	
	@ForgeSubscribe
	public void onPlayerInteract(final PlayerInteractEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (event.getResult().equals(event.useItem)) {
			if (player.getItemInUseDuration() == 1) {
				ItemStack itemInUse = player.getItemInUse();
				if (itemInUse.itemID == 282) {
					if (isDrinkingWater()) {
						waterUsages = 1;
						return;
					}
					waterUsages = 0;
				}
				
				float waterToAdd = getWaterValue(itemInUse);
				float energyToAdd = getEnergyValue(itemInUse);
				
				ExtendedPlayer extendedPlayer = ExtendedPlayer.get(player);
				extendedPlayer.replenishWater(waterToAdd);
				extendedPlayer.replenishEnergy(energyToAdd);
			}
		}
	}
	
	private boolean isDrinkingWater() {
		return waterUsages == 0;
	}
	
	private float getWaterValue(final ItemStack itemStack) {
		int id = itemStack.itemID;
		if (this.waterInFood.containsKey(id))
			return this.waterInFood.get(id);
		return 0.0f;
	}
	
	private float getEnergyValue(final ItemStack itemStack) {
		int id = itemStack.itemID;
		if (this.energyInFood.containsKey(id))
			return this.energyInFood.get(id);
		
		return 0.0f;
	}
}
