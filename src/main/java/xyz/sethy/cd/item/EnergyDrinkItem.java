package xyz.sethy.cd.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class EnergyDrinkItem extends Item {
	public EnergyDrinkItem() {
		// Calls the constructor method in the super class, with the argument of '8080', which is the itemID
		super(8080);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		// Sets the itemIcon to the icon found in the iconRegistry by the mod 'cd', and the name 'EnergyDrink'
		itemIcon = iconRegister.registerIcon("cd:EnergyDrink");
	}
}
